package com.lk1905.gielinorcraft.network.messages;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.capability.skill.SkillContainer;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;

public class StatsResponseMessage {

	private static ISkillContainer capability;
	
	public StatsResponseMessage(LazyOptional<ISkillContainer> skillCapability) {
		
		capability = new SkillContainer();
	}
	
	public StatsResponseMessage(ISkillContainer skillCapability) {
		
		capability = skillCapability;
	}
	
	public static StatsResponseMessage fromBytes(PacketBuffer buf) {
		
		capability.deserializePacket(buf);
		
		return new StatsResponseMessage(capability);
	}
	
	public static void toBytes(StatsResponseMessage msg, PacketBuffer buf) {
		
		capability.serializePacket(buf);
	}
	
	public ISkillContainer getSkillCapability() {
		
		return capability;
	}
}
