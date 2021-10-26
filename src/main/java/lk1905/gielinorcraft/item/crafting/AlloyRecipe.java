package lk1905.gielinorcraft.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import lk1905.gielinorcraft.init.GcBlocks;
import lk1905.gielinorcraft.init.GcRecipeSerializer;
import lk1905.gielinorcraft.init.GcRecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AlloyRecipe extends AbstractCookingRecipe{

	private final Ingredient ingredient_1;
	private final Ingredient ingredient_2;
	
	public AlloyRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredient_1, Ingredient ingredient_2,
			ItemStack resultIn, float experienceIn, int cookTimeIn) {
		super(GcRecipeType.ALLOY, idIn, groupIn, ingredient_1, resultIn, experienceIn, cookTimeIn);
	
		this.ingredient_1 = ingredient_1;
		this.ingredient_2 = ingredient_2;
	}
	
	@Override
	public ItemStack getIcon() {
		return new ItemStack(GcBlocks.FURNACE.get());
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return GcRecipeSerializer.ALLOY.get();
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlloyRecipe>{

		@Override
		public AlloyRecipe read(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getString(json, "group", "");
			JsonElement element_1 = JSONUtils.isJsonArray(json, "ingriedient_1") ? JSONUtils.getJsonArray(json, "ingriedient_1") : JSONUtils.getJsonObject(json, "ingriedient_1");
			JsonElement element_2 = JSONUtils.isJsonArray(json, "ingriedient_2") ? JSONUtils.getJsonArray(json, "ingriedient_2") : JSONUtils.getJsonObject(json, "ingriedient_2");
			
			Ingredient ingredient_1 = Ingredient.deserialize(element_1);
			Ingredient ingredient_2 = Ingredient.deserialize(element_2);
			
			ItemStack stack;
			
			if(!json.has("result")) {
				throw new JsonSyntaxException("Missing result, expected to find a string or object");
			}
			
			if(json.get("result").isJsonObject()) {
				stack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			}else {
				String s1 = JSONUtils.getString(json, "result");
				ResourceLocation location = new ResourceLocation(s1);
				stack = new ItemStack(ForgeRegistries.ITEMS.getValue(location));
			}
			
			float f = JSONUtils.getFloat(json, "experience", 0.0F);
			int i = JSONUtils.getInt(json, "smeltingTime", 200);
			
			return new AlloyRecipe(recipeId, s, ingredient_1, ingredient_2, stack, f, i);
		}

		@Override
		public AlloyRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String s = buffer.readString(32767);
			Ingredient ingredient_1 = Ingredient.read(buffer);
			Ingredient ingredient_2 = Ingredient.read(buffer);
			ItemStack stack = buffer.readItemStack();
			float f = buffer.readFloat();
			int i = buffer.readVarInt();
			
			return new AlloyRecipe(recipeId, s, ingredient_1, ingredient_2, stack, f, i); 
		}

		@Override
		public void write(PacketBuffer buffer, AlloyRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.ingredient_1.write(buffer);
			recipe.ingredient_2.write(buffer);
			buffer.writeItemStack(recipe.result);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.cookTime);
		}
		
	}
}
