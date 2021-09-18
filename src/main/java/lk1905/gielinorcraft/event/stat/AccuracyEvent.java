package lk1905.gielinorcraft.event.stat;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AccuracyEvent extends Event{

	private int slot;
	private int stat;
	private LivingEntity entity;
	
	public AccuracyEvent(int slot, int stat, LivingEntity entity) {
		this.slot = slot;
		this.stat = stat;
		this.entity = entity;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public int getStat() {
		return stat;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}
