package lk1905.gielinorcraft.api.stats;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IStats {

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
