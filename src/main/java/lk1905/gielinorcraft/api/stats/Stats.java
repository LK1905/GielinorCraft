package lk1905.gielinorcraft.api.stats;

import net.minecraft.nbt.CompoundNBT;

public class Stats implements IStats{

	public static final int STAB = 0, SLASH = 1, CRUSH = 2, MAGIC = 3, RANGED = 4;
	
	private final int[] accuracy = new int[5];
	private final int[] defence = new int[5];
	
	private int MELEE_STRENGTH = 0;
	private int RANGED_STRENGTH = 0;
	private double MAGIC_STRENGTH = 0;
	
	private double SLAYER_BONUS = 0;
	private double UNDEAD_BONUS = 0;
	private double DEMONIC_BONUS = 0;
	private double DRACONIC_BONUS = 0;
	private double OTHER_BONUS = 0;
	
	private double MELEE_ABSORBTION = 0;
	private double RANGED_ABSORBTION = 0;
	private double MAGIC_ABSORBTION = 0;
	
	@Override
	public void setAccuracy(int slot, int stat) {
		accuracy[slot] = stat;
	}
	
	@Override
	public void setDefence(int slot, int stat) {
		defence[slot] = stat;
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
	public void setMeleeAbsorbtion(double stat) {
		stat = MELEE_ABSORBTION;
	}
	
	@Override
	public void setRangedAbsorbtion(double stat) {
		stat = RANGED_ABSORBTION;
	}
	
	@Override
	public void setMagicAbsorbtion(double stat) {
		stat = MAGIC_ABSORBTION;
	}
	
	@Override
	public int getAccuracy(int slot) {
		return accuracy[slot];
	}
	
	@Override
	public int getDefence(int slot) {
		return defence[slot];
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
	public double getMeleeAbsorbtion() {
		return MELEE_ABSORBTION;
	}
	
	@Override
	public double getRangedAbsorbtion() {
		return RANGED_ABSORBTION;
	}
	
	@Override
	public double getMagicAbsorbtion() {
		return MAGIC_ABSORBTION;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 5; i++) {
			data.putInt("accuracy_" + i, accuracy[i]);
			data.putInt("defence_" + i, defence[i]);
		}
		
		data.putInt("melee_strength", MELEE_STRENGTH);
		data.putInt("ranged_strength", RANGED_STRENGTH);
		data.putDouble("magic_strength", MAGIC_STRENGTH);
		
		data.putDouble("slayer", SLAYER_BONUS);
		data.putDouble("undead", UNDEAD_BONUS);
		data.putDouble("demonic", DEMONIC_BONUS);
		data.putDouble("draconic", DRACONIC_BONUS);
		data.putDouble("other", OTHER_BONUS);
		
		data.putDouble("melee_absorbtion", MELEE_ABSORBTION);
		data.putDouble("ranged_absorbtion", RANGED_ABSORBTION);
		data.putDouble("magic_absorbtion", MAGIC_ABSORBTION);
		
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		for(int i = 0; i < 5; i++) {
			accuracy[i] = data.getInt("accuracy_" + i);
			defence[i] = data.getInt("defence_" + i);
		}
		
		MELEE_STRENGTH = data.getInt("melee_strength");
		RANGED_STRENGTH = data.getInt("ranged_strength");
		MAGIC_STRENGTH = data.getDouble("magic_strength");
		
		SLAYER_BONUS = data.getDouble("slayer");
		UNDEAD_BONUS = data.getDouble("undead");
		DEMONIC_BONUS = data.getDouble("demonic");
		DRACONIC_BONUS = data.getDouble("draconic");
		OTHER_BONUS = data.getDouble("other");
		
		MELEE_ABSORBTION = data.getDouble("melee_absorbtion");
		RANGED_ABSORBTION = data.getDouble("ranged_absorbtion");
		MAGIC_ABSORBTION = data.getDouble("magic_absorbtion");
	}
}
