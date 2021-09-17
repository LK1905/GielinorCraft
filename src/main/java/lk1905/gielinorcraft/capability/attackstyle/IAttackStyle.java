package lk1905.gielinorcraft.capability.attackstyle;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IAttackStyle {

	void setAttackStyle(int slot, int style);
	int getAttackStyle(int slot);
	
	void setActiveSlot(int slot);
	int getActiveStyle();
	
	String getStyleName(int slot);
	
	String getStyleDescription(int slot);
	
	int getActiveSlot();
	
	LivingEntity getEntity();
	
	CompoundNBT serializeNBT();
	void deserializeNBT(CompoundNBT data);
	
	void sync(ServerPlayerEntity player);
}
