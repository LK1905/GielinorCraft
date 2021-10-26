package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.block.GcFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcBlocks {

	private static boolean isInitialised;
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gielinorcraft.MODID);
	
	public static final RegistryObject<Block> FURNACE = BLOCKS.register("furnace", () -> new GcFurnaceBlock());
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		BLOCKS.register(bus);
		isInitialised = true;
	}
}
