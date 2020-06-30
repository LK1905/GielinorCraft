package com.lk1905.gielinorcraft.api.skills;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;

public interface ISkill {

	void gainXP(int amount, PlayerEntity player);
	
	void setXP(int xp);
	
	int getXP();
	
	int getMaxXP();
	
	int getLevel();
	
	int getMaxLevel();
	
	void setLevel(int newLevel);
	
	int xpToNextLevel();
	
	int xpForLevel(int level);
	
	String getName();
	
	CompoundNBT serializeNBT();
	
	void deserializeNBT(CompoundNBT data);
	
	void serializePacket(PacketBuffer buf);
	
	void deserializePacket(PacketBuffer buf);
	
	SkillIcon getSkillIcon();
}
