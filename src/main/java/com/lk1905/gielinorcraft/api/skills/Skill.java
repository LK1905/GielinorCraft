package com.lk1905.gielinorcraft.api.skills;

import com.lk1905.gielinorcraft.api.events.LevelUpEvent;
import com.lk1905.gielinorcraft.api.events.XPGainEvent;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;

public abstract class Skill implements ISkill{

	protected double currentXP;
	
	protected double xpLevels = Math.floor((getLevel() + 300 * (2 ^ (getLevel() / 7)))/4);
	
	public Skill() {
		currentXP = 0;
	}
	
	@Override
	public void gainXP(double amount, PlayerEntity player) {
		
		double xpToNext = xpToNextLevel();
		int currentLevel = getLevel();
		
		this.currentXP += amount;
		
		if(amount < getMaxXP()) {
			XPGainEvent xpEvent = new XPGainEvent(this, player, amount);
			MinecraftForge.EVENT_BUS.post(xpEvent);
		}
		
		if(amount >= xpToNext && currentLevel < getMaxLevel()) {
			LevelUpEvent levelEvent = new LevelUpEvent(this, player);
			MinecraftForge.EVENT_BUS.post(levelEvent);
		}
	}
	
	@Override
	public void setXP(double xp) {
		currentXP = xp;
	}
	
	@Override
	public double getXP() {
		return currentXP;
	}
	
	@Override
	public int getLevel() {
		
		for(int level = 1; level < getMaxLevel(); level++) {
			
			if(xpLevels >= currentXP) {
				return level;
			}
		}
		
		return getMaxLevel();
	}
	
	@Override
	public double xpForLevel(int level) {
		
		if(level >= getMaxLevel()) {
			return 0;
		}
		
		return xpLevels;
	}
	
	@Override
	public double xpToNextLevel() {
		
		double currentXP = getXP();
		
		if(getLevel() >= getMaxLevel()) {
			return 0;
		}
		
		
		if(xpLevels > currentXP) {
				
			return xpLevels - currentXP;
		}
		
		return 0;
	}
	
	@Override
	public int getMaxLevel() {
		return 99;
	}
	
	@Override
	public double getMaxXP() {
		return 200000000;
	}
	
	@Override
	public void setLevel(int newLevel) {
		currentXP = xpLevels;
	}
	
	public double getXpLevels() {
		return xpLevels;
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		data.putDouble("xp", currentXP);
		return data;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT data) {
		currentXP = data.getDouble("xp");
	}
	
	@Override
	public void serializePacket(PacketBuffer buf) {
		buf.writeDouble(getXP());
	}
	
	@Override
	public void deserializePacket(PacketBuffer buf) {
		currentXP = buf.readDouble();
	}
}
