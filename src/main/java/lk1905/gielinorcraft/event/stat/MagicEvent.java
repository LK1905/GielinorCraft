package lk1905.gielinorcraft.event.stat;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

/**This event modifies an entity's Magic strength stat.*/
public class MagicEvent extends Event{

	private int equipSlot;
	private double stat;
	private LivingEntity entity;
	
	public MagicEvent(int equipSlot, double stat, LivingEntity entity) {
		this.equipSlot = equipSlot;
		this.stat = stat;
		this.entity = entity;
	}
	
	public int getEquipSlot() {
		return equipSlot;
	}
	
	public double getStat() {
		return stat;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}
