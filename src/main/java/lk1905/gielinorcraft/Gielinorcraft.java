package lk1905.gielinorcraft;

import org.apache.logging.log4j.Logger;

import lk1905.gielinorcraft.capability.CapabilityHandler;
import lk1905.gielinorcraft.client.ClientEventHandler;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Gielinorcraft.MODID)
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gielinorcraft {

	public static Logger logger;
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
	@SubscribeEvent
	public static void onCommonSetup(final FMLCommonSetupEvent event) {
		CapabilityHandler.register();
		PacketHandler.register();
	}
	
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
}
