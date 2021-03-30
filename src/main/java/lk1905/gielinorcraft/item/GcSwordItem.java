package lk1905.gielinorcraft.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class GcSwordItem extends SwordItem{
	
	private final int stab;
	private final int slash;
	private final int strength;
	
	public GcSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, int stab, int slash, int strength, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		this.stab = stab;
		this.slash = slash;
		this.strength = strength;
	}
	
	public GcSwordItem(IItemTier tier, int stab, int slash, int strength, Properties builderIn) {
		this(tier, 0, -3.175F, stab, slash, strength, builderIn);
	}

	public void setStab(int stat) {
		stat = stab;
	}
	
	public void setSlash(int stat) {
		stat = slash;
	}
	
	public void setStrength(int stat) {
		stat = strength;
	}
	
	public int getStab() {
		return stab;
	}
	
	public int getSlash() {
		return slash;
	}
	
	public int getStrength() {
		return strength;
	}
}
