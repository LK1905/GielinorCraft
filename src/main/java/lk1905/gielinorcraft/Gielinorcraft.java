package lk1905.gielinorcraft;

import org.apache.logging.log4j.Logger;

import lk1905.gielinorcraft.capability.CapabilityHandler;
import lk1905.gielinorcraft.client.ClientEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Gielinorcraft.MODID)
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gielinorcraft {

	public static Logger logger;
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(CapabilityHandler::onCommonSetup);
	}
	
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
}
