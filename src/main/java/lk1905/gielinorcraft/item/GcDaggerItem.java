package lk1905.gielinorcraft.item;

import lk1905.gielinorcraft.Gielinorcraft;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GcDaggerItem extends SwordItem{
	
	private int stab;
	private int slash;
	private int strength;
	
	public GcDaggerItem(IItemTier tier, int stab, int slash, int strength, Properties builderIn) {
		super(tier, 0, -2.9F, builderIn);
		this.stab = stab;
		this.slash = slash;
		this.strength = strength;
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