package com.lk1905.gielinorcraft.skills;

import java.util.Optional;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.api.skills.Skill;
import com.lk1905.gielinorcraft.api.skills.SkillIcon;
import com.lk1905.gielinorcraft.api.utils.CapabilityUtils;
import com.lk1905.gielinorcraft.api.utils.OptionalUtils;
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
		
		return new SkillIcon(new ResourceLocation(texture), new Position(0, 0), 16, 16);
	}
	
	@SubscribeEvent
	public void entityHurt(LivingHurtEvent e) {
		
		if(!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}
		
		PlayerEntity player = (PlayerEntity) e.getSource().getTrueSource();
		
		Optional<ISkillContainer> capability = OptionalUtils.toOptional(CapabilityUtils.getCapability(player, CapabilitySkills.getSkillCapability(), null));
		
		int xpGained = (int) e.getAmount() * 4;
		
		ISkillContainer skill = capability.get();
		
		((ISkill) skill).gainXP(xpGained, player);
	}
}
