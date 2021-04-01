package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.api.combat.IAttackStyles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IAttackStyle {

	void setAttackStyle(int slot, IAttackStyles style);
	IAttackStyles getAttackStyle(int slot);
	
	void setActiveStyle(int style);
	IAttackStyles getActiveStyle();
	
	void setStyleName(int slot, String name);
	String getStyleName(int slot);
	
	void setStyleDescription(int slot, String description);
	String getStyleDescription(int slot);
	
	void setStyleId(int slot, int id);
	int getStyleId(int slot);
	
	int getActiveStyleId();
	
	LivingEntity getEntity();
	
	CompoundNBT serializeNBT();
	void deserializeNBT(CompoundNBT data);
	
	void sync(ServerPlayerEntity player);
}
