package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.item.crafting.AlloyRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GcRecipeSerializer {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Gielinorcraft.MODID);

	public static final RegistryObject<RecipeSerializer<?>> ALLOY = RECIPE_SERIALIZERS.register("alloy",
			() -> new AlloyRecipe.Serializer());
}
