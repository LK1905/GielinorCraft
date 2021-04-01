package lk1905.gielinorcraft.api.combat;

public enum AttackStyles implements IAttackStyles{
	
	EMPTY("Empty", "Empty", -1),
	ACCURATE_STAB("Accurate", "Stab - Attack xp", 0),
	ACCURATE_SLASH("Accurate", "Slash - Attack xp", 1),
	ACCURATE_CRUSH("Accurate", "Crush - Attack xp", 2),
	AGGRESSIVE_STAB("Aggressive", "Stab - Strength xp", 0),
	AGGRESSIVE_SLASH("Aggressive", "Slash - Strength xp", 1),
	AGGRESSIVE_CRUSH("Aggressive", "Crush - Strength xp", 2),
	CONTROLLED_STAB("Controlled", "Stab - Attack/Defence/Strength xp", 0),
	CONTROLLED_SLASH("Controlled", "Slash - Attack/Defence/Strength xp", 1),
	CONTROLLED_CRUSH("Controlled", "Crush - Attack/Defence/Strength xp", 2),
	DEFENSIVE_STAB("Defensive", "Stab - Defence xp", 0),
	DEFENSIVE_SLASH("Defensive", "Slash - Defence xp", 1),
	DEFENSIVE_CRUSH("Defensive", "Crush - Defence xp", 2),
	RANGED_ACCURATE("Accurate", "Ranged xp", 4),
	RANGED_RAPID("Rapid",  "Ranged xp",4),
	RANGED_LONG("Long Range", "Ranged/Defence xp",4),
	SPELL_DEFENSIVE("Defensive", "Magic/Defence xp",3),
	SPELL_CAST("Standard", "Magic xp",3)
	;

	private final String name;
	private final String descript;
	private final int id;
	
	AttackStyles(String styleName, String description, int styleId) {
		name = styleName;
		id = styleId;
		descript = description;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return descript;
	}

	@Override
	public int getStyleId() {
		return id;
	}
}
