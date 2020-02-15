package com.lk1905.gielinorcraft.api.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface ISkill {

	void gainXP(double amount, PlayerEntity player);
	
	void setXP(double xp);
	
	double getXP();
	
	double getMaxXP();
	
	int getLevel();
	
	int getMaxLevel();
	
	void setLevel(int newLevel);
	
	double xpToNextLevel();
	
	double xpForLevel(int level);
	
	String getName();
	
	CompoundNBT serializeNBT();
	
	void deserializeNBT(CompoundNBT data);
	
	void serializePacket(ByteBuf buf);
	
	void deserializePacket(ByteBuf buf);
	
	SkillIcon getSkillIcon();
}
