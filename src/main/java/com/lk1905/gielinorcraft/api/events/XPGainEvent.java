package com.lk1905.gielinorcraft.api.events;

import com.lk1905.gielinorcraft.api.skills.ISkill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class XPGainEvent extends Event{

	private final ISkill skill;
	private final PlayerEntity player;
	private final double xpGained;
	
	public XPGainEvent(ISkill skill, PlayerEntity player, double xpGained) {
		this.skill = skill;
		this.player = player;
		this.xpGained = xpGained;
	}
	
	public ISkill getSkill() {
		return skill;
	}
	
	public PlayerEntity getPlayer() {
		return player;
	}
	
	public double getXPGained() {
		return xpGained;
	}
}
