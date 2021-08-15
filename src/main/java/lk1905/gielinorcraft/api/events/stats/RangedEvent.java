package lk1905.gielinorcraft.api.events.stats;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

/**This event modifies an entity's Ranged strength stat.*/
public class RangedEvent extends Event{

	private int stat;
	private LivingEntity entity;
	
	public RangedEvent(int stat, LivingEntity entity) {
		this.stat = stat;
		this.entity = entity;
	}
	
	public int getStat() {
		return stat;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}
