package lk1905.gielinorcraft.events;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.events.XPGainEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.XPGainPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillEventHandler {

	@SubscribeEvent
	public static void onXPGain(XPGainEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
		PacketHandler.sendTo(new XPGainPacket(event.getSkillId(), event.getXPGained()), player);
	}
}
