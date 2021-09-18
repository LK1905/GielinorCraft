package lk1905.gielinorcraft.capability.stat;

public enum ItemStats implements IItemStats{
	IRON_DAGGER(5, 3, -4, 1, 0, 0, 0, 0, 1, 0, 4, 0, 0.0)
	;

	private final int stabA;
	private final int slashA;
	private final int crushA;
	private final int rangedA;
	private final int magicA;
	
	private final int stabD;
	private final int slashD;
	private final int crushD;
	private final int rangedD;
	private final int magicD;
	
	private final int meleeS;
	private final int rangedS;
	private final double magicS;
	
	ItemStats(int stabAccuracy, int slashAccuracy, int crushAccuracy, int rangedAccuracy, int magicAccuracy,
			int stabDefence, int slashDefence, int crushDefence, int rangedDefence, int magicDefence,
			int meleeStrength, int rangedStrength, double magicStrength) {
		stabA = stabAccuracy;
		slashA = slashAccuracy;
		crushA = crushAccuracy;
		rangedA = rangedAccuracy;
		magicA = magicAccuracy;
		stabD = stabDefence;
		slashD = slashDefence;
		crushD = crushDefence;
		rangedD = rangedDefence;
		magicD = magicDefence;
		meleeS = meleeStrength;
		rangedS = rangedStrength;
		magicS = magicStrength;
	}
	
	@Override
	public int getStabAccuracy() {
		return stabA;
	}

	@Override
	public int getSlashAccuracy() {
		return slashA;
	}

	@Override
	public int getCrushAccuracy() {
		return crushA;
	}

	@Override
	public int getRangedAccuracy() {
		return rangedA;
	}

	@Override
	public int getMagicAcuracy() {
		return magicA;
	}

	@Override
	public int getStabDefence() {
		return stabD;
	}

	@Override
	public int getSlashDefence() {
		return slashD;
	}

	@Override
	public int getCrushDefence() {
		return crushD;
	}

	@Override
	public int getRangedDefence() {
		return rangedD;
	}

	@Override
	public int getMagicDefence() {
		return magicD;
	}

	@Override
	public int getMeleeStrength() {
		return meleeS;
	}

	@Override
	public int getRangedStrength() {
		return rangedS;
	}

	@Override
	public double getMagicStrength() {
		return magicS;
	}

}
