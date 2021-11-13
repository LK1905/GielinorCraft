package lk1905.gielinorcraft.capability.attackstyle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public interface IAttackStyle {

	void setAttackStyle(int slot, int style);
	int getAttackStyle(int slot);
	
	void setActiveSlot(int slot);
	int getActiveStyle();
	
	String getStyleType(int slot);
	String getStyleName(int slot);
	String getStyleDescription(int slot);
	
	int getActiveSlot();
	
	CompoundTag serializeNBT();
	void deserializeNBT(CompoundTag data);
	
	void sync(ServerPlayer player);
}
