package lk1905.gielinorcraft.capability.stat;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class StatStorage implements Capability.IStorage<IStats>{

	@Override
	public INBT writeNBT(Capability<IStats> capability, IStats instance, Direction side) {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 5; i++) {
			data.putInt("accuracy_" + i, instance.getSlotAccuracy(0, i));
			data.putInt("defence_" + i, instance.getSlotDefence(0, i));
		}
		
		data.putInt("melee_strength", instance.getSlotMeleeStrength(0));
		data.putInt("ranged_strength", instance.getSlotRangedStrength(0));
		data.putDouble("magic_strength", instance.getSlotMagicStrength(0));
		
		return data;
	}

	@Override
	public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;
		
		for(int i = 0; i < 5; i++) {
			instance.setSlotAccuracy(0, i, data.getInt("accuracy_" + i));
			instance.setSlotDefence(0, i, data.getInt("defence_" + i));
		}
		
		instance.setSlotMeleeStrength(0, data.getInt("melee_strength"));
		instance.setSlotRangedStrength(0, data.getInt("ranged_strength"));
		instance.setSlotMagicStrength(0, data.getDouble("magic_strength"));
		
	}

}
