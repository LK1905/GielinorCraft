package lk1905.gielinorcraft.capability.stat;

public interface IItemStats {

	int getStabAccuracy();
	int getSlashAccuracy();
	int getCrushAccuracy();
	int getRangedAccuracy();
	int getMagicAcuracy();
	
	int getStabDefence();
	int getSlashDefence();
	int getCrushDefence();
	int getRangedDefence();
	int getMagicDefence();
	
	int getMeleeStrength();
	int getRangedStrength();
	double getMagicStrength();
}
