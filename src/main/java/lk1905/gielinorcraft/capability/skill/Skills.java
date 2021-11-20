package lk1905.gielinorcraft.capability.skill;

import java.util.Collections;
import java.util.UUID;

import lk1905.gielinorcraft.event.LevelUpEvent;
import lk1905.gielinorcraft.event.XPGainEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.skill.SkillsPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;

public final class Skills implements ISkills {

	/**Represents an array of skill names.*/
	public static final String[] SKILL_NAME = {
			"Attack",
			"Defence",
			"Strength",
			"Hitpoints",
			"Ranged",
			"Prayer",
			"Magic",
			"Cooking",
			"Woodcutting",
			"Fletching",
			"Fishing",
			"Firemaking",
			"Crafting",
			"Smithing",
			"Mining",
			"Herblore",
			"Agility",
			"Thieving",
			"Slayer",
			"Farming",
			"Runecrafting",
			"Hunter",
			"Carpentry",
			"Summoning",
			"Digging",
			"Stonecutting"
	};
	
	/**Constants for the skill ids.*/
	public static final int
			ATTACK = 0,
			DEFENCE = 1,
			STRENGTH = 2,
			HITPOINTS = 3,
			RANGED = 4,
			PRAYER = 5,
			MAGIC = 6,
			COOKING = 7,
			WOODCUTTING = 8,
			FLETCHING = 9,
			FISHING = 10,
			FIREMAKING = 11,
			CRAFTING = 12,
			SMITHING = 13,
			MINING = 14,
			HERBLORE = 15,
			AGILITY = 16,
			THIEVING = 17,
			SLAYER = 18,
			FARMING = 19,
			RUNECRAFTING = 20,
			HUNTER = 21,
			CARPENTRY = 22,
			SUMMONING = 23,
			DIGGING = 24,
			STONECUTTING = 25;
	
	/**Number of skills ingame.*/
	public static final int NUM_SKILLS = 26;
	
	/**Represents the entity instance.*/
	private final LivingEntity entity;
	
	/**An array containing all the player's experience.*/
	private final double[] xp;
	
	/**An array containing all the maximum levels.*/
	private final int[] staticLevels;
	
	/**An array containing all the current levels.*/
	private final int[] dynamicLevels;
	
	/**Represents the amount of Prayer Points left.*/
	private double prayerPoints = 1;
	
	/**The increased maximum health value.*/
	private int healthIncrease = 0;
	
	/**The total experience gained.*/
	private double xpGained = 0;
	
	/**If a health update should occur.*/
	private boolean healthUpdate;
	
	/**
	 * Constructs a new {@code Skills} {@code Object}.
	 * @param entity The entity.
	 * */
	public Skills(LivingEntity entity) {
		this.entity = entity;
		this.xp = new double[26];
		this.staticLevels = new int[26];
		this.dynamicLevels = new int[26];
		
		for(int i = 0; i < 26; i++) {
			if(!(entity instanceof Player)) {
					xp[i] = getXpByLevel(i);
					staticLevels[i] = 1;
					dynamicLevels[i] = getStaticLevel(i);
			}else if(i == HITPOINTS) {
				xp[HITPOINTS] = 1154;
				dynamicLevels[HITPOINTS] = getLevel(HITPOINTS);
				staticLevels[HITPOINTS] = getStaticLevelByXp(HITPOINTS);
			}else {
				xp[i] = 0;
				staticLevels[i] = getStaticLevelByXp(i);
				dynamicLevels[i] = getStaticLevel(i);
			}
		}
	}
	
	@Override
	public boolean isCombat(int skill) {
		
		if((skill >= ATTACK && skill <= MAGIC) || (skill == SUMMONING)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void addXp(int slot, double xp) {

		double xpAdd = xp;
		this.xp[slot] += xpAdd;
		
		if(this.xp[slot] >= 200000000) {
			this.xp[slot] = 200000000;
		}
		
		xpGained += xpAdd;
		int newLevel = getStaticLevelByXp(slot);
		
		if(newLevel > staticLevels[slot]) {
			int amount = newLevel - staticLevels[slot];
			
			if(dynamicLevels[slot] < newLevel) {
				dynamicLevels[slot] += amount;
			}
			staticLevels[slot] = newLevel;
			
			MinecraftForge.EVENT_BUS.post(new LevelUpEvent(slot, this.entity, newLevel));
		}
		
		if(xp < 200000000) {
			MinecraftForge.EVENT_BUS.post(new XPGainEvent(slot, this.entity, xp));
		}
	}
	
	@Override
	public int getHighestCombatSkill() {
		int id = 0;
		int last = 0;
		
		for(int i = 0; i < 5; i++) {
			if(staticLevels[i] > last) {
				last = staticLevels[i];
				id = 1;
			}
		}
		return id;
	}
	
	@Override
	public void restore() {
		for(int i = 0; i < 26; i++) {
			int stat = getStaticLevel(i);
			setLevel(i, stat);
		}
		rechargePrayerPoints();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag data = new CompoundTag();
		
		for(int i = 0; i < 26; i++) {
			data.putDouble("xp_" + i, xp[i]);
			data.putInt("static_" + i, staticLevels[i]);
			data.putInt("dynamic_" + i, dynamicLevels[i]);
		}	
		data.putInt("health", getMaxHealth());
		return data;
	}
	
	@Override
	public void deserializeNBT(CompoundTag data) {
		
		for(int i = 0; i < 26; i++) {
			xp[i] = data.getDouble("xp_" + i);
			staticLevels[i] = data.getInt("static_" + i);
			dynamicLevels[i] = data.getInt("dynamic_" + i);
		}
	}
	
	@Override
	public int getStaticLevelByXp(int slot) {
		
		double exp = xp[slot];
		double points = 0;
		double output = 0;
		
		for(byte lvl = 1; lvl < 100; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if((output - 1) >= exp) {
				return lvl;
			}
		}
		return 99;
	}
	
	@Override
	public int levelFromXP(double xp) {
		double points = 0;
		double output = 0;
		
		for(byte lvl = 1; lvl < 100; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if((output - 1) >= xp) {
				return lvl;
			}
		}
		return 99;
	}
	
	@Override
	public double getXpByLevel(int level) {
		double points = 0;
		double output = 0;
		
		for(int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			if(lvl >= level) {
				return output;
			} 
			output = Math.floor(points / 4);
		}
		return 0;
	}
	
	@Override
	public int getCombatLevel() {
		return calculateCombatLevel();
	}
	
	/**
	 * Calculates the combat level.
	 * @return The combat level.
	 * */
	private int calculateCombatLevel() {
		int combatLevel = 0;
		int melee = staticLevels[ATTACK] + staticLevels[STRENGTH];
		int range = (int) (1.5 * staticLevels[RANGED]);
		int magic = (int) (1.5 * staticLevels[MAGIC]);
		
		if(melee > range && melee > magic) {
			combatLevel = melee;
		}else if(range > melee && range > magic) {
			combatLevel = range;
		}else {
			combatLevel = magic;
		}
		
		combatLevel = staticLevels[DEFENCE] + staticLevels[HITPOINTS] + (staticLevels[PRAYER] / 2)
				+ (staticLevels[SUMMONING] / 2) + (int) (1.3 * combatLevel);
		return combatLevel / 4;
	}
	
	@Override
	public double getXp(int slot) {
		return xp[slot];
	}
	
	@Override
	public void setXp(int slot, double xp) {
		this.xp[slot] = xp;
	}
	
	@Override
	public int getStaticLevel(int slot) {
		return staticLevels[slot];
	}
	
	@Override
	public void setXpGained(double xpGained) {
		this.xpGained = xpGained;
	}
	
	@Override
	public double getXpGained() {
		return xpGained;
	}
	
	@Override
	public void setLevel(int slot, int level) {
		
		if(slot == HITPOINTS) {
			level = (int) entity.getHealth();
		}else if(slot == PRAYER) {
			prayerPoints = level;
		}
		
		dynamicLevels[slot] = level;
	}
	
	@Override
	public int getLevel(int slot) {
		return dynamicLevels[slot];
	}
	
	@Override
	public int getMaxHealth() {
		return staticLevels[HITPOINTS] + healthIncrease;
	}
	
	@Override
	public void setHealthIncrease(int amount) {
		this.healthIncrease = amount;
	}
	
	/**Modifies the max health of the entity.*/
	public void modifyMaxHealth() {
		if(entity == null) {
			return;
		}
		final float newAmount;
		final float oldAmount;
		
		if(entity instanceof Player) {
			oldAmount = 20;
			newAmount = getStaticLevel(HITPOINTS);
		}else {
			oldAmount = 0;
			newAmount = 0;
		}
		
		final UUID MODIFIER_ID = UUID.fromString("d5d0d878-b3c2-469b-ba89-ac01c0635a9c");
		final AttributeInstance health = entity.getAttribute(Attributes.MAX_HEALTH);
		final AttributeModifier mod = new AttributeModifier(MODIFIER_ID, "Max Health", newAmount - oldAmount, AttributeModifier.Operation.ADDITION);
		health.removeModifier(mod);
		health.addPermanentModifier(mod);
	}
	
	@Override
	public double getPrayerPoints() {
		return prayerPoints;
	}
	
	@Override
	public void rechargePrayerPoints() {
		prayerPoints = staticLevels[PRAYER];
	}
	
	@Override
	public void decrementPrayerPoints(double amount) {
		prayerPoints -= amount;
		
		if(prayerPoints < 0) {
			prayerPoints = 0;
		}
	}
	
	@Override
	public void incrementPrayerPoints(double amount) {
		prayerPoints += amount;
		
		if(prayerPoints < 0) {
			prayerPoints = 0;
		}
		if(prayerPoints > staticLevels[PRAYER]) {
			prayerPoints = staticLevels[PRAYER];
		}
	}
	
	@Override
	public void setPrayerPoints(double amount) {
		prayerPoints = amount;
	}
	
	@Override
	public int updateLevel(int skill, int amount, int max) {
		
		if(amount > 0 && dynamicLevels[skill] > max) {
			return -amount;
		}
		
		int left = (dynamicLevels[skill] + amount) - max;
		int level = dynamicLevels[skill] += amount;
		
		if(level < 0) {
			dynamicLevels[skill] = 0;
		}else if(amount < 0 && level < max) {
			dynamicLevels[skill] = max;
		}else if(amount > 0 && level > max) {
			dynamicLevels[skill] = max;
		}
		return left;
	}
	
	@Override
	public int updateLevel(int skill, int amount) {
		return updateLevel(skill, amount, amount >= 0 ? getStaticLevel(skill) + amount : getStaticLevel(skill) - amount);
	}
	
	@Override
	public void drainLevel(int skill, double drainPercentage, double maxDrainPercentage) {
		int drain = (int) (dynamicLevels[skill] * drainPercentage);
		int min = (int) (staticLevels[skill] * (1 - maxDrainPercentage));
		updateLevel(skill, -drain, min);
	}
	
	@Override
	public void setStaticLevel(int skill, int level) {
			staticLevels[skill] = dynamicLevels[skill] = level = getStaticLevelByXp(skill);
	}
	
	@Override
	public int getMasteredSkills() {
		int count = 0;
		for(int i = 0; i < 25; i++) {
			if(getStaticLevel(i) >= 99) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getSkillByName(final String name) {
		
		for(int i = 0; i < SKILL_NAME.length; i++) {
			if(SKILL_NAME[i].equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int getTotalLevel() {
		int level = 0;
		
		for(int i = 0; i < 26; i++) {
			level+= getStaticLevel(i);
		}
		return level;
	}
	
	@Override
	public int getTotalXp() {
		int total = 0;
		
		for(int skill = 0; skill < Skills.SKILL_NAME.length; skill++) {
			total += this.getXp(skill);
		}
		return total;
	}
	
	@Override
	public boolean isHealthUpdate() {
		return healthUpdate;
	}
	
	@Override
	public void setHealthUpdate(boolean update) {
		this.healthUpdate = update;
	}
	
	@Override
	public int[] getStaticLevels() {
		return staticLevels;
	}
	
	@Override
	public boolean hasLevel(int skillId, int level) {
		return getStaticLevel(skillId) >= level;
	}
	
	@Override
	public String getName(int slot) {
		return SKILL_NAME[slot];
	}
	
	@Override
	public int[] getDynamicLevels() {
		return dynamicLevels;
	}
	
	@Override
	public void sync(ServerPlayer player) {
		if(entity instanceof ServerPlayer) {
			PacketHandler.sendTo(new SkillsPacket(serializeNBT()), player);
			
			if(!player.level.isClientSide) {
				AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
				ClientboundUpdateAttributesPacket packet = new ClientboundUpdateAttributesPacket(player.getId(), Collections.singleton(attribute));
				((ServerLevel) player.getLevel()).getChunkSource().broadcastAndSend(player, packet);
			}
		}
	}
}
