package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.tileentity.GcFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcTileEntityType {

	private static boolean isInitialised;
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Gielinorcraft.MODID);

	public static final RegistryObject<TileEntityType<GcFurnaceTileEntity>> FURNACE = TILE_ENTITIES.register("furnace",
			() -> TileEntityType.Builder.create(GcFurnaceTileEntity::new, GcBlocks.FURNACE.get()).build(null));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		TILE_ENTITIES.register(bus);
		isInitialised = true;
	}
}