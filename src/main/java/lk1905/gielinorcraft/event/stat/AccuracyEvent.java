package lk1905.gielinorcraft.event.stat;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AccuracyEvent extends Event{

	private final int equipSlot;
	private final int typeSlot;
	private final int stat;
	private final LivingEntity entity;
	
	public AccuracyEvent(int equipSlot, int typeSlot, int stat, LivingEntity entity) {
		this.equipSlot = equipSlot;
		this.typeSlot = typeSlot;
		this.stat = stat;
		this.entity = entity;
	}
	
	public int getEquipSlot() {
		return equipSlot;
	}
	
	public int getAttackType() {
		return typeSlot;
	}
	
	public int getStat() {
		return stat;
	}
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
}