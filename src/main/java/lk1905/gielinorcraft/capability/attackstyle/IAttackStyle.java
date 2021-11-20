package lk1905.gielinorcraft.capability.attackstyle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.INBTSerializable;

public interface IAttackStyle extends INBTSerializable<CompoundTag>{

	void setAttackStyle(int slot, int style);
	int getAttackStyle(int slot);
	
	void setActiveSlot(int slot);
	int getActiveStyle();
	
	String getStyleType(int slot);
	String getStyleName(int slot);
	String getStyleDescription(int slot);
	
	int getActiveSlot();
	
	void sync(ServerPlayer player);
}
