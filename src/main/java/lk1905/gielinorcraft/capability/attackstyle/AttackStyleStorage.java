package lk1905.gielinorcraft.capability.attackstyle;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class AttackStyleStorage implements Capability.IStorage<IAttackStyle>{

	@Nullable
	@Override
	public INBT writeNBT(Capability<IAttackStyle> capability, IAttackStyle instance, Direction side) {
		CompoundNBT data = new CompoundNBT();
		
		data.putInt("active_style", instance.getActiveStyleId());
		return data;
	}

	@Override
	public void readNBT(Capability<IAttackStyle> capability, IAttackStyle instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;

		instance.setActiveStyle(data.getInt("active_style"));
	}
}
