package lk1905.gielinorcraft.events;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.events.LevelUpEvent;
import lk1905.gielinorcraft.api.events.XPGainEvent;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.network.LevelUpPacket;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
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
	
	@SubscribeEvent
	public static void onLevelUp(LevelUpEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
		PacketHandler.sendTo(new LevelUpPacket(event.getSkillId(), event.getNewLevel()), player);
		
		ISkills cap = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		PacketHandler.sendTo(new StringPacket("Congratulations, you have advanced a level in " + cap.getName(event.getSkillId())
				+ "! You are now level " + event.getNewLevel() + "."), player);
		
		if(event.getNewLevel() == 99) {
			PacketHandler.sendToAllPlayers(new StringPacket(player.getName() + " has reached level 99 in " + cap.getName(event.getSkillId()) + "!"), null);
		}
	}
}
