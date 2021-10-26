package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.item.crafting.AlloyRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcRecipeSerializer {

	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Gielinorcraft.MODID);

	public static final RegistryObject<IRecipeSerializer<?>> ALLOY = RECIPE_SERIALIZERS.register("alloy",
			() -> new AlloyRecipe.Serializer());
}
