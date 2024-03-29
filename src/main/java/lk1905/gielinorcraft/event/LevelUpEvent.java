package lk1905.gielinorcraft.event;

import javax.annotation.Nonnull;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class LevelUpEvent extends Event{

	private final int id;
	private final LivingEntity entity;
	private final int level;
	
	public LevelUpEvent(int skillId, LivingEntity entity, int newLevel) {
		id = skillId;
		this.entity = entity;
		level = newLevel;
	}
	
	public int getSkillId() {
		return id;
	}
	
	@Nonnull
	public LivingEntity getEntity() {
		return entity;
	}
	
	public int getNewLevel() {
		return level;
	}
}
