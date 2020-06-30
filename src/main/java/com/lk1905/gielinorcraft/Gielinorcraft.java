package com.lk1905.gielinorcraft;

import org.apache.logging.log4j.Logger;
import com.lk1905.gielinorcraft.api.skills.Skill;
import com.lk1905.gielinorcraft.capability.skill.CapabilitySkills;
import com.lk1905.gielinorcraft.capability.skill.SkillContainer;
import com.lk1905.gielinorcraft.client.ClientEventHandler;
import com.lk1905.gielinorcraft.client.ClientProxy;
import com.lk1905.gielinorcraft.network.handlers.StatsResponseHandler;
import com.lk1905.gielinorcraft.skills.AttackSkill;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Gielinorcraft.MODID)
@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gielinorcraft {

	public static Logger logger;
	
	public static final String MODID = "gielinorcraft";
	
	public Gielinorcraft() {
		
	}
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent e) {

		CapabilitySkills.register();
		
		SkillContainer.registerNewSkill(AttackSkill.class);
		
		MinecraftForge.EVENT_BUS.register(Skill.class);
		MinecraftForge.EVENT_BUS.register(AttackSkill.class);
	}
	
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent e) {
		
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		
		StatsResponseHandler.registerListener(ClientProxy::loadSkillCapability);
	}
}
	
