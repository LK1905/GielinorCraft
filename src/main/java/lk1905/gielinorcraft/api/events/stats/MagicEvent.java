package lk1905.gielinorcraft.api.events.stats;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

/**This event modifies an entity's Magic strength stat.*/
public class MagicEvent extends Event{

	private double stat;
	private LivingEntity entity;
	
	public MagicEvent(double stat, LivingEntity entity) {
		this.stat = stat;
		this.entity = entity;
	}
	
	public double getStat() {
		return stat;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
}
