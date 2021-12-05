package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.inventory.container.AlloyFurnaceMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GcMenuType {

	private static boolean isInitialised;
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Gielinorcraft.MODID);

	public static final RegistryObject<MenuType<AbstractContainerMenu>> FURNACE = CONTAINERS.register("furnace",
			() -> new MenuType<>(AlloyFurnaceMenu::new));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		CONTAINERS.register(bus);
		isInitialised = true;
	}
}