package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcItems {

	private static boolean isInitialised;
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Gielinorcraft.MODID);
	
	public static final RegistryObject<BlockItem> FURNACE = ITEMS.register("furnace", () -> new BlockItem(GcBlocks.FURNACE.get(), (new Item.Properties()).tab(GcCreativeTabs.TAB_DECORATIONS)));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(GcCreativeTabs.TAB_MISC)));
	
	public static final RegistryObject<DaggerItem> IRON_DAGGER = ITEMS.register("iron_dagger", () -> new DaggerItem(Tiers.IRON, (new Item.Properties()).tab(GcCreativeTabs.TAB_COMBAT)));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		ITEMS.register(bus);
		isInitialised = true;
	}
}
