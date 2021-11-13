package lk1905.gielinorcraft.event.stat;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

/**This event modifies an entity's Melee strength stat.*/
public class MeleeEvent extends Event{

	private final int equipSlot;
	private final int stat;
	private final LivingEntity entity;
	
	public MeleeEvent(int equipSlot, int stat, LivingEntity entity) {
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
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
}