package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.item.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcItems {

	private static boolean isInitialised;
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Gielinorcraft.MODID);
	
	public static final RegistryObject<GcDaggerItem> IRON_DAGGER = ITEMS.register("iron_dagger", () -> new GcDaggerItem(ItemTier.IRON, 5, 3, 4, (new Item.Properties()).group(ItemGroup.COMBAT)));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		ITEMS.register(bus);
		isInitialised = true;
	}
}
