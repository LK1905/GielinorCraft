package lk1905.gielinorcraft.event;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AttackStyleEvent extends Event{

	private final LivingEntity entity;
	private final int slot;
	private final int style;
	
	public AttackStyleEvent(LivingEntity entity, int slot, int style) {
		this.entity = entity;
		this.slot = slot;
		this.style = style;
	}
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public int getStyle() {
		return style;
	}
}
