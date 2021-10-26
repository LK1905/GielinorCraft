package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import net.minecraftforge.event.world.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**For events that don't really fit in the other handler classes.*/
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MiscEventHandler {

	//Because it's annoying.
	@SubscribeEvent
	public static void onFarmlandTrample(FarmlandTrampleEvent event) {
		event.setCanceled(true);
	}
}
