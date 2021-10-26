package lk1905.gielinorcraft.init;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.inventory.container.GcFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GcContainerType {

	private static boolean isInitialised;
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Gielinorcraft.MODID);

	public static final RegistryObject<ContainerType<GcFurnaceContainer>> FURNACE = CONTAINERS.register("furnace",
			() -> IForgeContainerType.create(GcFurnaceContainer::new));
	
	public static void initialise(final IEventBus bus) {
		if(isInitialised) {
			throw new IllegalStateException("Already initialised");
		}
		CONTAINERS.register(bus);
		isInitialised = true;
	}
}