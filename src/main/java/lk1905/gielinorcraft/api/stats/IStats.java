package lk1905.gielinorcraft.api.stats;

import net.minecraft.nbt.CompoundNBT;

public interface IStats {

	public void setAccuracy(int slot, int stat);
	
	public void setDefence(int slot, int stat);
	
	public void setMeleeStrength(int stat);
	public void setRangedStrength(int stat);
	public void setMagicStrength(double stat);
	
	public void setSlayerBonus(double stat);
	public void setUndeadBonus(double stat);
	public void setDemonicBonus(double stat);
	public void setDraconicBonus(double stat);
	public void setOtherBonus(double stat);
	
	public void setMeleeAbsorbtion(double stat);
	public void setRangedAbsorbtion(double stat);
	public void setMagicAbsorbtion(double stat);
	
	public int getAccuracy(int slot);
	
	public int getDefence(int slot);
	
	public int getMeleeStrength();
	public int getRangedStrength();
	public double getMagicStrength();

	public double getSlayerBonus();
	public double getUndeadBonus();
	public double getDemonicBonus();
	public double getDraconicBonus();
	public double getOtherBonus();
	
	public double getMeleeAbsorbtion();
	public double getRangedAbsorbtion();
	public double getMagicAbsorbtion();
	
	public CompoundNBT serializeNBT();
	public void deserializeNBT(CompoundNBT data);
}
