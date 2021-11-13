package lk1905.gielinorcraft.capability.magic;

public interface ICombatSpells {
	int getSpellbookId();
	int getSpellType();
	int getElementType();
	int getLevelReq();
	double getSpellXp();
	int getDamage();
	String getName();
}
