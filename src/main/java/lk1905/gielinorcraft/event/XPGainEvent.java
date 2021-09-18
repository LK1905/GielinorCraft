package lk1905.gielinorcraft.event;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class XPGainEvent extends Event{

	private final int id;
	private final LivingEntity entity;
	private final double xp;
	
	public XPGainEvent(int skillId, LivingEntity entity, double xpGained) {
		id = skillId;
		this.entity = entity;
		xp = xpGained;
	}
	
	@Nonnull
	public int getSkillId() {
		return id;
	}
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
	
	public double getXPGained() {
		return xp;
	}
}
