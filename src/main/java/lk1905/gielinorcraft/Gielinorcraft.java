package lk1905.gielinorcraft;

import com.mojang.blaze3d.platform.ScreenManager;

import lk1905.gielinorcraft.client.gui.screen.container.AlloyFurnaceScreen;
import lk1905.gielinorcraft.event.handler.CapabilityHandler;
import lk1905.gielinorcraft.event.handler.ClientEventHandler;
import lk1905.gielinorcraft.init.GcBlocks;
import lk1905.gielinorcraft.init.GcMenuType;
import lk1905.gielinorcraft.init.GcItems;
import lk1905.gielinorcraft.init.GcBlockEntityType;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Gielinorcraft.MODID)
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gielinorcraft {
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {	

		final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		GcItems.initialise(bus);
		GcBlocks.initialise(bus);
		GcBlockEntityType.initialise(bus);
		GcMenuType.initialise(bus);
	}
	
	@SubscribeEvent
	public static void onCommonSetup(final FMLCommonSetupEvent event) {
		PacketHandler.register();
		//event.enqueueWork(() -> ScreenManager.registerFactory(GcMenuType.FURNACE.get(), AlloyFurnaceScreen::new));
	}
	
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
}
