package lk1905.gielinorcraft.events;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.combat.IAttackStyles;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.api.stats.IStats;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.stats.StatCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CombatEventHandler {

	@SubscribeEvent
	public static void entityDamage(LivingDamageEvent event) {
		if(!(event.getSource().getTrueSource() instanceof LivingEntity)) {
			return;
		}
		
		LivingEntity source = (LivingEntity) event.getSource().getTrueSource();
		LivingEntity receiver = event.getEntityLiving();
		
		ISkills sourceSkills = source.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		ISkills receiverSkills = receiver.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		IStats sourceStats = source.getCapability(StatCapability.STAT_CAP).orElse(null);
		IStats receiverStats = receiver.getCapability(StatCapability.STAT_CAP).orElse(null);
		
		IAttackStyle sourceStyle = source.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		IAttackStyles sourceActive = sourceStyle.getActiveStyle();
		
		IAttackStyle receiverStyle = receiver.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		IAttackStyles receiverActive = receiverStyle.getActiveStyle();
		
		int attackBonus = 0;
		int defenceBonus = 0;
		int strengthBonus = 0;
		int rangedBonus = 0;

		double random = Math.random();
		
		if(sourceActive == AttackStyles.ACCURATE_STAB || sourceActive == AttackStyles.ACCURATE_SLASH || sourceActive == AttackStyles.ACCURATE_CRUSH) {
			attackBonus = 3;
		}else if(sourceActive == AttackStyles.AGGRESSIVE_STAB || sourceActive == AttackStyles.AGGRESSIVE_SLASH || sourceActive == AttackStyles.AGGRESSIVE_CRUSH) {
			strengthBonus = 3;
		}else if(sourceActive == AttackStyles.CONTROLLED_STAB || sourceActive == AttackStyles.CONTROLLED_SLASH || sourceActive == AttackStyles.CONTROLLED_CRUSH) {
			attackBonus = 1;
			strengthBonus = 1;
		}else if(sourceActive == AttackStyles.RANGED_ACCURATE) {
			rangedBonus = 3;
		}
		
		if(receiverActive == AttackStyles.DEFENSIVE_STAB || receiverActive == AttackStyles.DEFENSIVE_SLASH || receiverActive == AttackStyles.DEFENSIVE_CRUSH) {
			defenceBonus = 3;
		}else if(receiverActive == AttackStyles.CONTROLLED_STAB || receiverActive == AttackStyles.CONTROLLED_SLASH || receiverActive == AttackStyles.CONTROLLED_CRUSH) {
			defenceBonus = 1;
		}
		
		int attack = sourceSkills.getLevel(0) + attackBonus;
		int defence = receiverSkills.getLevel(1) + defenceBonus;
		int strength = sourceSkills.getLevel(2) + strengthBonus;
		int ranged = sourceSkills.getLevel(4) + rangedBonus;
		
		int accuracyRoll = attack;
		int defenceRoll = defence;
		
		int damage = 1;
		
		for(int i = 0; i < 5; i++) {
			if(sourceActive.getStyleId() == i && i < 3) {
				accuracyRoll = attack * (sourceStats.getAccuracy(i) + 64);
				defenceRoll = defence * (receiverStats.getDefence(i) + 64);
				damage = (int) Math.floor(1.3 + (strength / 10) + (sourceStats.getMeleeStrength() / 80) + ((strength * sourceStats.getMeleeStrength()) / 640));
			}else if(sourceActive.getStyleId() == i && i == 4) {
				accuracyRoll = ranged * (sourceStats.getAccuracy(4) + 64);
				defenceRoll = defence * (receiverStats.getDefence(4) + 64);
				damage = (int) Math.floor(1.3 + (ranged / 10) + (sourceStats.getRangedStrength() / 80) + ((ranged * sourceStats.getRangedStrength()) / 640));
			}
		}
		
		double accuracy;
		
		if(accuracyRoll > defenceRoll) {
			accuracy = ((double) 1.0 - ((defenceRoll + 2.0) / (2.0 * (accuracyRoll + 1.0))));
		}else {
			accuracy = ((double) accuracyRoll / (2.0 * (defenceRoll + 1.0)));
		}
		
		if(event.getSource().getTrueSource() == source) {
			if(random < accuracy) {
				event.setAmount(damage);
				/*if(!source.getEntity().world.isRemote && source instanceof ServerPlayerEntity) {
					PacketHandler.sendTo(new StringPacket("Hit = " + damage + " damage! Random = " + random), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Accuracy = " + accuracy + ", Strength = " + strength + ", Strength style bonus = " + strengthBonus), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Accuracy roll = " + accuracyRoll + ", Defence roll = " + defenceRoll), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Attack = " + attack + ", Defence = " + defence), (ServerPlayerEntity) source);
				}*/
			}else {
				event.setCanceled(true);
				/*if(!source.getEntity().world.isRemote && source instanceof ServerPlayerEntity) {
					PacketHandler.sendTo(new StringPacket("Miss! Random = " + random), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Accuracy = " + accuracy + ", Strength = " + strength + ", Strength style bonus = " + strengthBonus), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Accuracy roll = " + accuracyRoll + ", Defence roll = " + defenceRoll), (ServerPlayerEntity) source);
					PacketHandler.sendTo(new StringPacket("Attack = " + attack + ", Defence = " + defence), (ServerPlayerEntity) source);
				}*/
			}
		}
	}
}
