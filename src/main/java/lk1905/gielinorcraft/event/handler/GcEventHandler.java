package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.event.AttackStyleEvent;
import lk1905.gielinorcraft.event.LevelUpEvent;
import lk1905.gielinorcraft.event.XPGainEvent;
import lk1905.gielinorcraft.event.stat.AccuracyEvent;
import lk1905.gielinorcraft.event.stat.DefenceEvent;
import lk1905.gielinorcraft.event.stat.MagicEvent;
import lk1905.gielinorcraft.event.stat.MeleeEvent;
import lk1905.gielinorcraft.event.stat.RangedEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import lk1905.gielinorcraft.network.attackstyle.ChangeStylePacket;
import lk1905.gielinorcraft.network.skill.LevelUpPacket;
import lk1905.gielinorcraft.network.skill.XPGainPacket;
import lk1905.gielinorcraft.network.stat.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**A class for all custom mod events.*/
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GcEventHandler {

	@SubscribeEvent
	public static void onXPGain(XPGainEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new XPGainPacket(event.getSkillId(), event.getXPGained()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onLevelUp(LevelUpEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
		ISkills cap = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		if(!player.world.isRemote) {
			PacketHandler.sendTo(new LevelUpPacket(event.getSkillId(), event.getNewLevel()), player);
			PacketHandler.sendTo(new StringPacket("Congratulations, you have advanced a level in " + cap.getName(event.getSkillId())
			+ "! You are now level " + event.getNewLevel() + "."), player);
		
			if(event.getNewLevel() == 99) {
				PacketHandler.sendToAllPlayers(new StringPacket(player.getName() + " has reached level 99 in " + cap.getName(event.getSkillId()) + "!"), null);
			}
		}
	}
	
	@SubscribeEvent
	public static void onAttackStyleChange(AttackStyleEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new ChangeStylePacket(event.getSlot(), event.getStyle()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onAccuracyChange(AccuracyEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new AccuracyPacket(event.getSlot(), event.getStat()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onDefenceChange(DefenceEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new DefencePacket(event.getSlot(), event.getStat()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onMeleeStrengthChange(MeleeEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new MeleeStrengthPacket(event.getStat()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onRangedStrengthChange(RangedEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new RangedStrengthPacket(event.getStat()), (ServerPlayerEntity) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onMagicStrengthChange(MagicEvent event) {
		if(!event.getEntity().world.isRemote) {
			PacketHandler.sendTo(new MagicStrengthPacket(event.getStat()), (ServerPlayerEntity) event.getEntity());
		}
	}
}
