package com.lk1905.gielinorcraft.api.events;

import com.lk1905.gielinorcraft.api.skills.ISkill;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class LevelUpEvent extends Event{

	private final ISkill skill;
	private final PlayerEntity player;
	
	public LevelUpEvent(ISkill skill, PlayerEntity player) {
		this.skill = skill;
		this.player = player;
	}
	
	public ISkill getSkill() {
		return skill;
	}
	
	public PlayerEntity getPlayer() {
		return player;
	}
}
