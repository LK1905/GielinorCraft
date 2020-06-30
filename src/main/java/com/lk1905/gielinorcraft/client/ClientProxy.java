package com.lk1905.gielinorcraft.client;

import java.util.function.Supplier;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.network.messages.StatsResponseMessage;

import net.minecraftforge.fml.network.NetworkEvent;

public class ClientProxy {

	private static ISkillContainer skillCapability;
	private static ISkill activelyTrainedSkill;
	
	public static void loadSkillCapability(StatsResponseMessage msg, Supplier<NetworkEvent.Context> ctx) {
		
		if(msg == null || ctx == null) {
			return;
		}
		
		skillCapability = msg.getSkillCapability();
	}
	
	public static ISkillContainer getSkillCapability() {
		
		return skillCapability;
	}
	
	public static void setActivelyTrainedSkill(ISkill newActiveSkill) {
		
		activelyTrainedSkill = newActiveSkill;
	}
	
	public static ISkill getActivelyTrainedSkill() {
		
		return activelyTrainedSkill;
	}
}
