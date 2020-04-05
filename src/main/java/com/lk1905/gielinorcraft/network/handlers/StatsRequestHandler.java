package com.lk1905.gielinorcraft.network.handlers;

import java.util.function.Supplier;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.capability.skill.CapabilitySkills;
import com.lk1905.gielinorcraft.network.messages.StatsRequestMessage;
import com.lk1905.gielinorcraft.network.messages.StatsResponseMessage;

import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

public class StatsRequestHandler {

	public StatsResponseMessage onMessage(StatsRequestMessage message, Supplier<NetworkEvent.Context> ctx) {
		
		Gielinorcraft.logger.info("Received a Stats Request Message!");
		
		LazyOptional<ISkillContainer> skillCapability = ctx.get().getSender().getServerWorld().getCapability(CapabilitySkills.getSkillCapability(), null);
		
		return new StatsResponseMessage(skillCapability);
	}
}
