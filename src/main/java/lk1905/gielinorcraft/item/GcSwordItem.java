package lk1905.gielinorcraft.item;

import lk1905.gielinorcraft.api.stats.IStats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class GcSwordItem extends SwordItem{
	
	private LivingEntity entity;
	private IStats stat;
	
	public GcSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, int stab, int slash, int strength, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		
	}
	
	public GcSwordItem(IItemTier tier, int stab, int slash, int strength, Properties builderIn) {
		this(tier, 0, -3.175F, stab, slash, strength, builderIn);
		
		if(entity.getHeldItemMainhand().getItem() == this) {
			stat.setAccuracy(0, stab);
			stat.setAccuracy(1, slash);
			stat.setMeleeStrength(strength);
		}
	}
}
