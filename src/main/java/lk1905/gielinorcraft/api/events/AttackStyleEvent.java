package lk1905.gielinorcraft.api.events;

import javax.annotation.Nonnull;

import lk1905.gielinorcraft.api.combat.IAttackStyles;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class AttackStyleEvent extends Event{

	private final LivingEntity entity;
	private final int slot;
	private final IAttackStyles style;
	
	public AttackStyleEvent(LivingEntity entity, int slot, IAttackStyles style) {
		this.entity = entity;
		this.slot = slot;
		this.style = style;
	}
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
	
	@Nonnull
	public int getSlot() {
		return slot;
	}
	
	public IAttackStyles getStyle() {
		return style;
	}
}
