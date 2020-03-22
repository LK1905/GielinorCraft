package com.lk1905.gielinorcraft;

import org.apache.logging.log4j.Logger;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.Skill;
import com.lk1905.gielinorcraft.capability.skill.CapabilitySkills;
import com.lk1905.gielinorcraft.capability.skill.SkillContainer;
import com.lk1905.gielinorcraft.skills.AttackSkill;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Gielinorcraft.MODID)
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gielinorcraft {

	public static Logger logger;
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {
		
	}
	
	private static ISkillContainer skillCapability;
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent e) {

		CapabilitySkills.register();
		
		SkillContainer.registerNewSkill(AttackSkill.class);
		
		MinecraftForge.EVENT_BUS.register(Skill.class);
		MinecraftForge.EVENT_BUS.register(AttackSkill.class);
	}
	
	public static ISkillContainer getSkillCapability() {
		
		return skillCapability;
	}
}
