package lk1905.gielinorcraft.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class GcSwordItem extends SwordItem{
	
	private int stab;
	private int slash;
	private int strength;
	
	public GcSwordItem(IItemTier tier, int stab, int slash, int strength, Properties builderIn) {
		super(tier, 0, -3.175F, builderIn);
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
