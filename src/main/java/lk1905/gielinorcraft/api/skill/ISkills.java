package lk1905.gielinorcraft.api.skill;

import com.google.gson.JsonArray;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

/**Represents an entity's skills.*/
public interface ISkills {

	/**
	 * Determine whether the specified skill is a combat skill.
	 * @param skill
	 * @return true if so.
	 * */
	public boolean isCombat(int skill);
	
	/**Configures the skills.*/
	public void configure();
	
	/**Called every game tick.*/
	public void restoreTick();
	
	/**
	 * Copies the skills data.
	 * @param skills The skills.
	 * */
	public void copy(Skills skills);
	
	/**
	 * Adds the experience to the skill.
	 * @param slot The skill slot.
	 * @param xp The experience.
	 * */
	public void addXp(int slot, double xp);
	
	/**
	 * Gets the highest combat skill id.
	 * @return The id of the highest combat skill.
	 * */
	public int getHighestCombatSkill();
	
	/**Returns the dynamic levels to the static levels.*/
	public void restore();
	
	/**
	 * Deserializes the skill data from the buf.
	 * @param buf The byte buf.
	 * */
	public void deserializePacket(ByteBuf buf);
	
	public void parse(JsonArray skillData);
	
	/**
	 * Serializes the skill data on the buf.
	 * @param buf The byte buf.
	 * */
	public void serializePacket(ByteBuf buf);
	
	public CompoundNBT serializeNBT();
	
	public void deserializeNBT(CompoundNBT data);
	
	/**
	 * Gets the static level.
	 * @param slot The skills' slot.
	 * @return The level.
	 * */
	public int getStaticLevelByXp(int slot);
	
	/**
	 * Gets the experience for a certain level.
	 * @param level The level.
	 * @return The experience needed.
	 * */
	public int getXpByLevel(int level);
	
	/**
	 * Gets the combat level.
	 * @return The combat level.
	 * */
	public int getCombatLevel();
	
	/**@return The entity.*/
	public LivingEntity getEntity();
	
	/**
	 * Gets the experience.
	 * @param slot The slot
	 * @return The experience.
	 * */
	public double getXp(int slot);
	
	/**
	 * Sets the experience.
	 * @param xp The experience
	 * @param slot The slot
	 * */
	public void setXp(int slot, double xp);
	
	/**
	 * Gets the static skill level.
	 * @param slot The slot.
	 * @return The static level.
	 * */
	public int getStaticLevel(int slot);
	
	/**
	 * Sets the experience gained.
	 * @param xpGained The experience gained.
	 * */
	public void setXpGained(double xpGained);
	
	/**
	 * Gets the experience gained.
	 * @return The experience gained.
	 * */
	public double getXpGained();
	
	/**
	 * Sets the dynamic level.
	 * @param slot The skill id.
	 * @param level The level.
	 * */
	public void setLevel(int slot, int level);
	
	/**
	 * Gets the dynamic level.
	 * @param slot The skill id.
	 * @return The dynamic level.
	 * */
	public int getLevel(int slot);
	
	/**
	 * Sets the current amount of lifepoints.
	 * @param lifepoints The lifepoints.
	 * */
	public void setLifepoints(int lifepoints);
	
	/**
	 * Gets the lifepoints.
	 * @return The lifepoints.
	 * */
	public int getLifepoints();
	
	/**
	 * Gets the maximum amount of lifepoints.
	 * @return The maximum amount.
	 * */
	public int getMaximumLifepoints();
	
	/**
	 * Sets amount of lifepoints increase.
	 * @param amount The amount.
	 * */
	public void setLifepointsIncrease(int amount);
	
	/**
	 * Gets the prayer points.
	 * @return The prayer points.
	 * */
	public double getPrayerPoints();
	
	/**Recharges the prayer points.*/
	public void rechargePrayerPoints();
	
	/**
	 * Updates the current amount of prayer points (by decrementing).
	 * @param amount The amount to decrement with.
	 * */
	public void decrementPrayerPoints(double amount);
	
	/**
	 * Updates the current amount of prayer points (by incrementing).
	 * @param amount The amount to decrement with.
	 * */
	public void incrementPrayerPoints(double amount);
	
	/**
	 * Sets the current prayer points.
	 * @param amount The amount.
	 * */
	public void setPrayerPoints(double amount);
	
	/**
	 * Updates the current skill level (by incrementing the current amount with the given amount, up to the given maximum.
	 * @param skill The skill id.
	 * @param amount The amount to increment.
	 * @param max The maximum amount the skill can be.
	 * @return The amount of "overflow".
	 * */
	public int updateLevel(int skill, int amount, int max);
	
	/**
	 * Updates a level.
	 * @param skill The skill.
	 * @param amount The amount.
	 * @return the left.
	 * */
	public int updateLevel(int skill, int amount);
	
	/**
	 * Drains a certain percentage of a level.
	 * @param skill The skill.
	 * @param drainPercentage The drain percentage.
	 * @param maxDrainPercentage The maximum drain percentage.
	 * */
	public void drainLevel(int skill, double drainPercentage, double maxDrainPercentage);
	
	/**
	 * Sets the static level.
	 * @param skill The skill id.
	 * @param level the level to set.
	 * */
	public void setStaticLevel(int skill, int level);
	
	/**
	 * Gets the restoration tick.
	 * @return The resotoration tick.
	 * */
	public SkillRestoration[] getRestoration();
	
	/**
	 * Gets the amount of mastered skills.
	 * @return The amount of mastered skills.
	 * */
	public int getMasteredSkills();
	
	/**
	 * Method used to get the skill by name.
	 * @param name The name.
	 * @return the skill.
	 * */
	public int getSkillByName(final String name);
	
	/**
	 * Gets the total level.
	 * @return the total level.
	 * */
	public int getTotalLevel();
	
	/**
	 * Gets the total experience.
	 * @return the experience.
	 * */
	public int getTotalXp();
	
	/**
	 * Gets the lifepointsUpdate.
	 * @return The lifepointsUpdate.
	 * */
	public boolean isLifepointsUpdate();
	
	/**
	 * Sets the lifepointsUpdate.
	 * @param lifepointsUpdate The lifepointsUpdate to set.
	 * */
	public void setLifepointsUpdate(boolean lifepointsUpdate);
	
	/**
	 * Gets the static levels.
	 * @return the level.
	 * */
	public int[] getStaticLevels();
	
	/**
	 * Checks if the player has the required level.
	 * @param skillId the skill id.
	 * @param level the level.
	 * @return {code True} if so.
	 * */
	public boolean hasLevel(int skillId, int level);
	
	/*public void sync(ServerPlayerEntity player);*/
}
