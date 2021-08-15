package lk1905.gielinorcraft.api.magic;

public enum CombatSpells implements ICombatSpells{
	WIND_STRIKE(0, 0, 0, 1, 5.5, 2, "Wind Strike"),
	WATER_STRIKE(0, 0, 1, 5, 7.5, 4, "Water Strike"),
	EARTH_STRIKE(0, 0, 2, 9, 9.5, 6, "Earth Strike"),
	FIRE_STRIKE(0, 0, 3, 13, 11.5, 8, "Fire Strike"),
	WIND_BOLT(0, 1, 0, 17, 13.5, 9, "Wind Bolt"),
	WATER_BOLT(0, 1, 1, 23, 16.5, 10, "Water Bolt"),
	EARTH_BOLT(0, 1, 2, 29, 19.5, 11, "Earth Bolt"),
	FIRE_BOLT(0, 1, 3, 35, 22.5, 12, "Fire Bolt"),
	CRUMBLE_UNDEAD(0, -1, -1, 39, 24.5, 15, "Crumble Undead"),
	WIND_BLAST(0, 2, 0, 41, 25.5, 13, "Wind Blast"),
	WATER_BLAST(0, 2, 1, 47, 28.5, 14, "Water Blast"),
	EARTH_BLAST(0, 2, 2, 53, 31.5, 15, "Earth Blast"),
	FIRE_BLAST(0, 2, 3, 59, 34.5, 16, "Fire Blast"),
	IBAN_BLAST(0, -1, -1, 50, 30.0, 25, "Iban Blast"),
	MAGIC_DART(0, -1, -1, 50, 30.0, 15, "Magic Dart"),
	SARADOMIN_STRIKE(0, -1, -1, 60, 35.0, 20, "Saradomin Strike"),
	ZAMORAK_FLAMES(0, -1, -1, 60, 35.0, 20, "Flames of Zamorak"),
	GUTHIX_CLAWS(0, -1, -1, 60, 35.0, 20, "Claws of Guthix"),
	WIND_WAVE(0, 3, 0, 62, 36.0, 17, "Wind Wave"),
	WATER_WAVE(0, 3, 1, 65, 37.5, 18, "Water Wave"),
	EARTH_WAVE(0, 3, 2, 70, 40.0, 19, "Earth Wave"),
	FIRE_WAVE(0, 3, 3, 75, 42.5, 20, "Fire Wave"),
	AIR_SURGE(0, 4, 0, 81, 44.5, 22, "Air Surge"),
	WATER_SURGE(0, 4, 1, 85, 46.5, 24, "Water Surge"),
	EARTH_SURGE(0, 4, 2, 90, 48.5, 26, "Earth Surge"),
	FIRE_SURGE(0, 4, 3, 95, 50.5, 28, "Fire Surge"),
	SMOKE_RUSH(1, 5, 4, 50, 30.0, 15, "Smoke Rush"),
	SHADOW_RUSH(1, 5, 5, 52, 31.0, 16, "Shadow Rush"),
	BLOOD_RUSH(1, 5, 6, 56, 33.0, 17, "Blood Rush"),
	ICE_RUSH(1, 5, 7, 58, 34.0, 18, "Ice Rush"),
	SMOKE_BURST(1, 6, 4, 62, 36.0, 19, "Smoke Burst"),
	SHADOW_BURST(1, 6, 5, 64, 37.0, 20, "Shadow Burst"),
	BLOOD_BURST(1, 6, 6, 68, 39.0, 21, "Blood Burst"),
	ICE_BURST(1, 6, 7, 70, 40.0, 22, "Ice Burst"),
	SMOKE_BLITZ(1, 7, 4, 74, 42.0, 23, "Smoke Blitz"),
	SHADOW_BLITZ(1, 7, 5, 76, 43.0, 24, "Shadow Blitz"),
	BLOOD_BLITZ(1, 7, 6, 80, 45.0, 25, "Blood Blitz"),
	ICE_BLITZ(1, 7, 7, 82, 46.0, 26, "Ice Blitz"),
	SMOKE_BARRAGE(1, 8, 4, 86, 48.0, 27, "Smoke Barrage"),
	SHADOW_BARRAGE(1, 8, 5, 88, 49.0, 28, "Shadow Barrage"),
	BLOOD_BARRAGE(1, 8, 6, 92, 51.0, 29, "Blood Barrage"),
	ICE_BARRAGE(1, 8, 7, 94, 52.0, 30, "Ice Barrage");

	private final int bookId;
	private final int type;
	private final int element;
	private final int level;
	private final double xp;
	private final int damage;
	private final String name;
	
	CombatSpells(int spellbookId, int spellType, int elementType, int levelReq, double spellXp, int baseDamage, String spellName) {
		bookId = spellbookId;
		type = spellType;
		element = elementType;
		level = levelReq;
		xp = spellXp;
		damage = baseDamage;
		name = spellName;
	}
	
	@Override
	public int getSpellbookId() {
		return bookId;
	}

	@Override
	public int getSpellType() {
		return type;
	}

	@Override
	public int getElementType() {
		return element;
	}
	
	@Override
	public int getLevelReq() {
		return level;
	}
	
	@Override
	public double getSpellXp() {
		return xp;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public String getName() {
		return name;
	}

}
