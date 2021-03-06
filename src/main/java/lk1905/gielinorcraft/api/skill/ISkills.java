package lk1905.gielinorcraft.api.skill;

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
	 * Serializes the skill data into nbt tag format.
	 * @return A CompoundNBT tag representing skill data.
	 * */
	public CompoundNBT serializeNBT();
	
	/**
	 * Deserializes the skill data from nbt tag format.
	 * @param data The CompoundNBT representing skill data.
	 * */
	public void deserializeNBT(CompoundNBT data);
	
	/**
	 * Gets the static level.
	 * @param slot The skills' slot.
	 * @return The level.
	 * */
	public int getStaticLevelByXp(int slot);
	
	public int levelFromXP(double xp);
	
	/**
	 * Gets the experience for a certain level.
	 * @param level The level.
	 * @return The experience needed.
	 * */
	public double getXpByLevel(int level);
	
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
	 * Gets the maximum amount of health.
	 * @return The maximum amount.
	 * */
	public int getMaxHealth();
	
	/**
	 * Sets amount of health increase.
	 * @param amount The amount.
	 * */
	public void setHealthIncrease(int amount);
	
	/**Modifies the max health of the entity.*/
	public void modifyMaxHealth(LivingEntity entity);
	
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
	 * Gets the Health Update.
	 * @return The health Update.
	 * */
	public boolean isHealthUpdate();
	
	/**
	 * Sets the Health Update.
	 * @param update The health Update to set.
	 * */
	public void setHealthUpdate(boolean update);
	
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
	
	/**
	 * Gets the name of the skill.
	 * @return The skill name.
	 * */
	public String getName(int slot);
	
	public int[] getDynamicLevels();
	
	public void sync(ServerPlayerEntity player);
}
