package lk1905.gielinorcraft.item.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import lk1905.gielinorcraft.init.GcBlocks;
import lk1905.gielinorcraft.init.GcRecipeSerializer;
import lk1905.gielinorcraft.init.GcRecipeType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
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
	public ItemStack getToastSymbol() {
		return new ItemStack(GcBlocks.FURNACE.get());
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return GcRecipeSerializer.ALLOY.get();
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<AlloyRecipe>{

		@Override
		public AlloyRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String s = GsonHelper.getAsString(json, "group", "");
			JsonElement element_1 = GsonHelper.isArrayNode(json, "ingriedient_1") ? GsonHelper.getAsJsonArray(json, "ingriedient_1") : GsonHelper.getAsJsonObject(json, "ingriedient_1");
			JsonElement element_2 = GsonHelper.isArrayNode(json, "ingriedient_2") ? GsonHelper.getAsJsonArray(json, "ingriedient_2") : GsonHelper.getAsJsonObject(json, "ingriedient_2");
			
			Ingredient ingredient_1 = Ingredient.fromJson(element_1);
			Ingredient ingredient_2 = Ingredient.fromJson(element_2);
			
			ItemStack stack;
			
			if(!json.has("result")) {
				throw new JsonSyntaxException("Missing result, expected to find a string or object");
			}
			
			if(json.get("result").isJsonObject()) {
				stack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
			}else {
				String s1 = GsonHelper.getAsString(json, "result");
				ResourceLocation location = new ResourceLocation(s1);
				stack = new ItemStack(ForgeRegistries.ITEMS.getValue(location));
			}
			
			float f = GsonHelper.getAsFloat(json, "experience", 0.0F);
			int i = GsonHelper.getAsInt(json, "smeltingTime", 200);
			
			return new AlloyRecipe(recipeId, s, ingredient_1, ingredient_2, stack, f, i);
		}

		@Override
		public AlloyRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			String s = buffer.readUtf(32767);
			Ingredient ingredient_1 = Ingredient.fromNetwork(buffer);
			Ingredient ingredient_2 = Ingredient.fromNetwork(buffer);
			ItemStack stack = buffer.readItem();
			float f = buffer.readFloat();
			int i = buffer.readVarInt();
			
			return new AlloyRecipe(recipeId, s, ingredient_1, ingredient_2, stack, f, i); 
		}

		@Override
		public void toNetwork(FriendlyByteBuf buffer, AlloyRecipe recipe) {
			buffer.writeUtf(recipe.group);
			recipe.ingredient_1.toNetwork(buffer);
			recipe.ingredient_2.toNetwork(buffer);
			buffer.writeItem(recipe.result);
			buffer.writeFloat(recipe.experience);
			buffer.writeVarInt(recipe.cookingTime);
		}
		
	}
}
