package lk1905.gielinorcraft.api.events.stats;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class DefenceEvent extends Event{

	private int slot;
	private int stat;
	private LivingEntity entity;
	
	public DefenceEvent(int slot, int stat, LivingEntity entity) {
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
