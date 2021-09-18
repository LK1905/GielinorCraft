package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.stat.IStats;
import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.entity.LivingEntity;
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
		int sourceActive = sourceStyle.getActiveStyle();
		
		IAttackStyle receiverStyle = receiver.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		int receiverActive = receiverStyle.getActiveStyle();
		
		int attackBonus = 0;
		int defenceBonus = 0;
		int strengthBonus = 0;
		int rangedBonus = 0;

		double random = Math.random();
		
		if(sourceActive == 1 || sourceActive == 2 || sourceActive == 3) {
			attackBonus = 3;
		}else if(sourceActive == 4 || sourceActive == 5 || sourceActive == 6) {
			strengthBonus = 3;
		}else if(sourceActive == 7 || sourceActive == 8 || sourceActive == 9) {
			attackBonus = 1;
			strengthBonus = 1;
		}else if(sourceActive == 13) {
			rangedBonus = 3;
		}
		
		if(receiverActive == 10 || receiverActive == 11 || receiverActive == 12) {
			defenceBonus = 3;
		}else if(receiverActive == 7 || receiverActive == 8 || receiverActive == 9) {
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
			if(sourceActive == i && i > 0 && i < 13) {
				accuracyRoll = attack * (sourceStats.getAccuracy(i) + 64);
				defenceRoll = defence * (receiverStats.getDefence(i) + 64);
				damage = (int) Math.floor(1.3 + (strength / 10) + (sourceStats.getMeleeStrength() / 80) + ((strength * sourceStats.getMeleeStrength()) / 640));
			}else if(sourceActive == i && i == 4) {
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
			}else {
				event.setCanceled(true);
			}
		}
	}
}
