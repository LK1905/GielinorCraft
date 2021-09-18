package lk1905.gielinorcraft.event.stat;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

/**This event modifies an entity's Ranged strength stat.*/
public class RangedEvent extends Event{

	private int equipSlot;
	private int stat;
	private LivingEntity entity;
	
	public RangedEvent(int equipSlot, int stat, LivingEntity entity) {
		this.equipSlot = equipSlot;
		this.stat = stat;
		this.entity = entity;
	}
	
	public int getEquipSlot() {
		return equipSlot;
	}
	
	public int getStat() {
		return stat;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}