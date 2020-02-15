package com.lk1905.gielinorcraft.skills;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.api.skills.Skill;
import com.lk1905.gielinorcraft.api.skills.SkillIcon;
import com.lk1905.gielinorcraft.api.utils.CapabilityUtils;
import com.lk1905.gielinorcraft.api.utils.Position;
import com.lk1905.gielinorcraft.capability.skill.CapabilitySkills;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AttackSkill extends Skill{

	@Override
	public String getName() {
		return "Attack";
	}
	
	@Override
	public SkillIcon getSkillIcon() {
		
		String texture = "minecraft:textures/items/iron_sword.png";
		
		return new SkillIcon(new ResourceLocation(texture), new Position(0,0), 16, 16);
	}
	
	@SubscribeEvent
	public static void entityHurt(LivingHurtEvent e) {
		
		if(!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}
		
		PlayerEntity player = (PlayerEntity) e.getSource().getTrueSource();
		
		ISkill attackSkill = (ISkill) ((ISkillContainer) CapabilityUtils.getCapability(
								player, CapabilitySkills.getSkillCapability(), null)).getSkill("Attack");
		
		double xpGained = (double) e.getAmount() * 4;
		
		attackSkill.gainXP(xpGained, player);
	}
}
