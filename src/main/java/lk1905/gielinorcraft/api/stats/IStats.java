package lk1905.gielinorcraft.api.stats;

import net.minecraft.nbt.CompoundNBT;

public interface IStats {

	public void setStabAccuracy(int stat);
	public void setSlashAccuracy(int stat);
	public void setCrushAccuracy(int stat);
	public void setRangedAccuracy(int stat);
	public void setMagicAccuracy(int stat);
	
	public void setStabDefence(int stat);
	public void setSlashDefence(int stat);
	public void setCrushDefence(int stat);
	public void setRangedDefence(int stat);
	public void setMagicDefence(int stat);
	
	public void setMeleeStrength(int stat);
	public void setRangedStrength(int stat);
	public void setMagicStrength(double stat);
	
	public void setSlayerBonus(double stat);
	public void setUndeadBonus(double stat);
	public void setDemonicBonus(double stat);
	public void setDraconicBonus(double stat);
	public void setOtherBonus(double stat);
	
	public int getStabAccuracy();
	public int getSlashAccuracy();
	public int getCrushAccuracy();
	public int getRangedAccuracy();
	public int getMagicAccuracy();
	
	public int getStabDefence();
	public int getSlashDefence();
	public int getCrushDefence();
	public int getRangedDefence();
	public int getMagicDefence();
	
	public int getMeleeStrength();
	public int getRangedStrength();
	public double getMagicStrength();

	public double getSlayerBonus();
	public double getUndeadBonus();
	public double getDemonicBonus();
	public double getDraconicBonus();
	public double getOtherBonus();
	
	public CompoundNBT serializeNBT();
	public void deserializeNBT(CompoundNBT data);
}
