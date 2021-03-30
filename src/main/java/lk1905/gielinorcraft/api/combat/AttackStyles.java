package lk1905.gielinorcraft.api.combat;

public enum AttackStyles implements IAttackStyles{
	
	EMPTY("Empty", -1),
	ACCURATE_STAB("Accurate", 0),
	ACCURATE_SLASH("Accurate", 1),
	ACCURATE_CRUSH("Accurate", 2),
	AGGRESSIVE_STAB("Aggressive", 0),
	AGGRESSIVE_SLASH("Aggressive", 1),
	AGGRESSIVE_CRUSH("Aggressive", 2),
	CONTROLLED_STAB("Controlled", 0),
	CONTROLLED_SLASH("Controlled", 1),
	CONTROLLED_CRUSH("Controlled", 2),
	DEFENSIVE_STAB("Defensive", 0),
	DEFENSIVE_SLASH("Defensive", 1),
	DEFENSIVE_CRUSH("Defensive", 2),
	RANGED_ACCURATE("Accurate", 4),
	RANGED_RAPID("Rapid", 4),
	RANGED_LONG("Long Range", 4),
	SPELL_DEFENSIVE("Defensive", 3),
	SPELL_CAST("Standard", 3)
	;

	private final String name;
	private final int id;
	
	AttackStyles(String styleName, int styleId) {
		name = styleName;
		id = styleId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStyleId() {
		return id;
	}
}
