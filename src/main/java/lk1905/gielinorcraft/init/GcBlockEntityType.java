package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.block.entity.AlloyFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcBlockEntityType {

	private static boolean isInitialised;
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Gielinorcraft.MODID);

	public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> FURNACE = BLOCK_ENTITIES.register("furnace",
			() -> BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new, GcBlocks.FURNACE.get()).build(null));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		BLOCK_ENTITIES.register(bus);
		isInitialised = true;
	}
}