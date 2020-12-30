package lk1905.gielinorcraft.api.skill;

import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**Handles the skill restoration data.*/
public final class SkillRestoration {

	/**The skill index.*/
	private final int skillId;
	
	/**The current game tick.*/
	private int tick;
	
	/**
	 * Constructs a new {@code SkillRestoration} {@code Object}.
	 * @param skillId The skill id.
	 * */
	public SkillRestoration(int skillId) {
		this.skillId = skillId;
		
	}
	
	/**
	 * Restores the skill.
	 * @param entity The entity.
	 * */
	public void restore(LivingEntity entity) {
		
		LazyOptional<ISkills> cap = entity.getCapability(SkillCapability.SKILL_CAP);
		ISkills skills = cap.orElse(null);
		
		if(tick == 1200) {
			if(skillId == Skills.HITPOINTS) {
				int max = skills.getMaximumLifepoints();
			}else {
				int dynamic = skills.getLevel(skillId);
				int stat = skills.getStaticLevel(skillId);
				if(dynamic != stat) {
					skills.updateLevel(skillId, dynamic < stat ? 1 : -1, stat);
				}
			}
		}
	}
	
	/**Restarts the restoration.*/
	@SubscribeEvent
	public void onTick(TickEvent event) {
		
		int timer = tick;
		if(timer < 1200) {
			timer++;
		}else {
			timer = 0;
		}
	}
	
	/**
	 * Gets the game tick.
	 * @return The tick.
	 * */
	public int getTick() {
		return tick;
	}
	
	/**
	 * Sets the game tick.
	 * @param tick The tick to set.
	 * */
	public void setTick(int tick) {
		this.tick = tick;
	}
	
	/**
	 * Gets the skill id.
	 * @return The skillId
	 * */
	public int getSkillId() {
		return skillId;
	}
}
