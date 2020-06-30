package com.lk1905.gielinorcraft.api.skills;

import com.lk1905.gielinorcraft.api.events.LevelUpEvent;
import com.lk1905.gielinorcraft.api.events.XPGainEvent;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;

public abstract class Skill implements ISkill{

	protected int currentXP;
	
	protected static int[] xpLevels = new int[]{
            0, 83, 174, 276, 388, 512, 650, 801, 969, 1154,
            1358, 1584, 1833, 2107, 2411, 2746, 3115, 3523, 3973, 4470,
            5018, 5624, 6291, 7028, 7842, 8740, 9730, 10824, 12031, 13363,
            14833, 16456, 18247, 20224, 22406, 24815, 27473, 30408, 33648, 37224,
            41171, 45529, 50339, 55649, 61512, 67983, 75127, 83014, 91721, 101333,
            111945, 123660, 136594, 150872, 166636, 184040, 203254, 224466, 247886, 273742,
            302288, 333804, 368599, 407015, 449428, 496254, 547953, 605032, 668051, 737627,
            814445, 899257, 992895, 1096278, 1210421, 1336443, 1475581, 1629200, 1798808, 1986068,
            2192818, 2421087, 2673114, 2951373, 3258594, 3597792, 3972294, 4385776, 4842295, 5346332,
            5902831, 6517253, 7195629, 7944614, 8771558, 9684577, 10692629, 11805606, 13034431
	};
	
	public Skill() {
		currentXP = 0;
	}
	
	@Override
	public void gainXP(int amount, PlayerEntity player) {
		
		int xpToNext = xpToNextLevel();
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
	public void setXP(int xp) {
		currentXP = xp;
	}
	
	@Override
	public int getXP() {
		return currentXP;
	}
	
	@Override
	public int getLevel() {
		
		for(int level = 1; level < xpLevels.length; level++) {
            if (xpLevels[level] > currentXP) {
                return level;
			}
		}
		
		return getMaxLevel();
	}
	
	@Override
	public int xpForLevel(int level) {
		
		if(level >= getMaxLevel()) {
			return 0;
		}
		
		return xpLevels[level - 1];
	}
	
	@Override
	public int xpToNextLevel() {
		
		int currentXP = getXP();
		
		if(getLevel() >= getMaxLevel()) {
			return 0;
		}
		
		
		for(int level = 1; level < xpLevels.length; level++) {
            if (xpLevels[level] > currentXP) {
                return xpLevels[level] - currentXP;
            }
		}
		
		return 0;
	}
	
	@Override
	public int getMaxLevel() {
		return 99;
	}
	
	@Override
	public int getMaxXP() {
		return 200000000;
	}
	
	@Override
	public void setLevel(int newLevel) {
		currentXP = xpLevels[newLevel - 1];
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		data.putDouble("xp", currentXP);
		return data;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT data) {
		currentXP = data.getInt("xp");
	}
	
	@Override
	public void serializePacket(PacketBuffer buf) {
		buf.writeInt(getXP());
	}
	
	@Override
	public void deserializePacket(PacketBuffer buf) {
		currentXP = buf.readInt();
	}
}
