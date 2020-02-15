package com.lk1905.gielinorcraft;

import org.apache.logging.log4j.Logger;

import com.lk1905.gielinorcraft.capability.skill.CapabilitySkills;
import com.lk1905.gielinorcraft.capability.skill.SkillContainer;
import com.lk1905.gielinorcraft.skills.AttackSkill;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Gielinorcraft.MODID)
public class Gielinorcraft {

	public static Logger logger;
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {
		
		CapabilitySkills.register();
		
		SkillContainer.registerNewSkill(AttackSkill.class);
		
		MinecraftForge.EVENT_BUS.register(AttackSkill.class);
	}
}
