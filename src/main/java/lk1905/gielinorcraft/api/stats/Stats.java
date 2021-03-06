package lk1905.gielinorcraft.api.stats;

import net.minecraft.nbt.CompoundNBT;

public class Stats implements IStats{

	private int STAB_ACCURACY = 0;
	private int SLASH_ACCURACY = 0;
	private int CRUSH_ACCURACY = 0;
	private int RANGED_ACCURACY = 0;
	private int MAGIC_ACCURACY = 0;
	
	private int STAB_DEFENCE = 0;
	private int SLASH_DEFENCE = 0;
	private int CRUSH_DEFENCE = 0;
	private int RANGED_DEFENCE = 0;
	private int MAGIC_DEFENCE = 0;
	
	private int MELEE_STRENGTH = 0;
	private int RANGED_STRENGTH = 0;
	private double MAGIC_STRENGTH = 0;
	
	private double SLAYER_BONUS = 0;
	private double UNDEAD_BONUS = 0;
	private double DEMONIC_BONUS = 0;
	private double DRACONIC_BONUS = 0;
	private double OTHER_BONUS = 0;

	@Override
	public void setStabAccuracy(int stat) {
		stat = STAB_ACCURACY;	
	}

	@Override
	public void setSlashAccuracy(int stat) {
		stat = SLASH_ACCURACY;	
	}

	@Override
	public void setCrushAccuracy(int stat) {
		stat = CRUSH_ACCURACY;	
	}

	@Override
	public void setRangedAccuracy(int stat) {
		stat = RANGED_ACCURACY;	
	}

	@Override
	public void setMagicAccuracy(int stat) {
		stat = MAGIC_ACCURACY;	
	}

	@Override
	public void setStabDefence(int stat) {
		stat = STAB_DEFENCE;	
	}

	@Override
	public void setSlashDefence(int stat) {
		stat = SLASH_DEFENCE;	
	}

	@Override
	public void setCrushDefence(int stat) {
		stat = CRUSH_DEFENCE;	
	}

	@Override
	public void setRangedDefence(int stat) {
		stat = RANGED_DEFENCE;	
	}

	@Override
	public void setMagicDefence(int stat) {
		stat = MAGIC_DEFENCE;	
	}

	@Override
	public void setMeleeStrength(int stat) {
		stat = MELEE_STRENGTH;	
	}

	@Override
	public void setRangedStrength(int stat) {
		stat = RANGED_STRENGTH;	
	}

	@Override
	public void setMagicStrength(double stat) {
		stat = MAGIC_STRENGTH;	
	}

	@Override
	public void setSlayerBonus(double stat) {
		stat = SLAYER_BONUS;	
	}

	@Override
	public void setUndeadBonus(double stat) {
		stat = UNDEAD_BONUS;	
	}

	@Override
	public void setDemonicBonus(double stat) {
		stat = DEMONIC_BONUS;	
	}

	@Override
	public void setDraconicBonus(double stat) {
		stat = DRACONIC_BONUS;	
	}

	@Override
	public void setOtherBonus(double stat) {
		stat = OTHER_BONUS;	
	}

	@Override
	public int getStabAccuracy() {
		return STAB_ACCURACY;
	}

	@Override
	public int getSlashAccuracy() {
		return SLASH_ACCURACY;
	}

	@Override
	public int getCrushAccuracy() {
		return CRUSH_ACCURACY;
	}

	@Override
	public int getRangedAccuracy() {
		return RANGED_ACCURACY;
	}

	@Override
	public int getMagicAccuracy() {
		return MAGIC_ACCURACY;
	}

	@Override
	public int getStabDefence() {
		return STAB_DEFENCE;
	}

	@Override
	public int getSlashDefence() {
		return SLASH_DEFENCE;
	}

	@Override
	public int getCrushDefence() {
		return CRUSH_DEFENCE;
	}

	@Override
	public int getRangedDefence() {
		return RANGED_DEFENCE;
	}

	@Override
	public int getMagicDefence() {
		return MAGIC_DEFENCE;
	}

	@Override
	public int getMeleeStrength() {
		return MELEE_STRENGTH;
	}

	@Override
	public int getRangedStrength() {
		return RANGED_STRENGTH;
	}

	@Override
	public double getMagicStrength() {
		return MAGIC_STRENGTH;
	}

	@Override
	public double getSlayerBonus() {
		return SLAYER_BONUS;
	}

	@Override
	public double getUndeadBonus() {
		return UNDEAD_BONUS;
	}

	@Override
	public double getDemonicBonus() {
		return DEMONIC_BONUS;
	}

	@Override
	public double getDraconicBonus() {
		return DRACONIC_BONUS;
	}

	@Override
	public double getOtherBonus() {
		return OTHER_BONUS;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		
		data.putInt("stab_accuracy", STAB_ACCURACY);
		data.putInt("slash_accuracy", SLASH_ACCURACY);
		data.putInt("crush_accuracy", CRUSH_ACCURACY);
		data.putInt("ranged_accuracy", RANGED_ACCURACY);
		data.putInt("magic_accuracy", MAGIC_ACCURACY);
		
		data.putInt("stab_defence", STAB_DEFENCE);
		data.putInt("slash_defence", SLASH_DEFENCE);
		data.putInt("crush_defence", CRUSH_DEFENCE);
		data.putInt("ranged_defence", RANGED_DEFENCE);
		data.putInt("magic_defence", MAGIC_DEFENCE);
		
		data.putInt("melee_strength", MELEE_STRENGTH);
		data.putInt("ranged_strength", RANGED_STRENGTH);
		data.putDouble("magic_strength", MAGIC_STRENGTH);
		
		data.putDouble("slayer", SLAYER_BONUS);
		data.putDouble("undead", UNDEAD_BONUS);
		data.putDouble("demonic", DEMONIC_BONUS);
		data.putDouble("draconic", DRACONIC_BONUS);
		data.putDouble("other", OTHER_BONUS);
		
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		STAB_ACCURACY = data.getInt("stab_accuracy");
		SLASH_ACCURACY = data.getInt("slash_accuracy");
		CRUSH_ACCURACY = data.getInt("crush_accuracy");
		RANGED_ACCURACY = data.getInt("ranged_accuracy");
		MAGIC_ACCURACY = data.getInt("magic_accuracy");
		
		STAB_DEFENCE = data.getInt("stab_defence");
		SLASH_DEFENCE = data.getInt("slash_defence");
		CRUSH_DEFENCE = data.getInt("crush_defence");
		RANGED_DEFENCE = data.getInt("ranged_defence");
		MAGIC_DEFENCE = data.getInt("magic_defence");
		
		MELEE_STRENGTH = data.getInt("melee_strength");
		RANGED_STRENGTH = data.getInt("ranged_strength");
		MAGIC_STRENGTH = data.getDouble("magic_strength");
		
		SLAYER_BONUS = data.getDouble("slayer");
		UNDEAD_BONUS = data.getDouble("undead");
		DEMONIC_BONUS = data.getDouble("demonic");
		DRACONIC_BONUS = data.getDouble("draconic");
		OTHER_BONUS = data.getDouble("other");
	}
}
