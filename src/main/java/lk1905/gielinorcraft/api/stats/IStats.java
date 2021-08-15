package lk1905.gielinorcraft.api.stats;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IStats {

	void addAccuracy(int slot, int value);
	void removeAccuracy(int slot, int value);
	
	void addDefence(int slot, int value);
	void removeDefence(int slot, int value);
	
	void addMeleeStrength(int value);
	void removeMeleeStrength(int value);
	
	void addRangedStrength(int value);
	void removeRangedStrength(int value);
	
	void addMagicStrength(double value);
	void removeMagicStrength(double value);
	
	void setAccuracy(int slot, int stat);
	
	void setDefence(int slot, int stat);
	
	void setMeleeStrength(int stat);
	void setRangedStrength(int stat);
	void setMagicStrength(double stat);
	
	int getAccuracy(int slot);
	
	int getDefence(int slot);
	
	int getMeleeStrength();
	int getRangedStrength();
	double getMagicStrength();
	
	CompoundNBT serializeNBT();
	void deserializeNBT(CompoundNBT data);
	
	void sync(ServerPlayerEntity player);
}
