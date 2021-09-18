package lk1905.gielinorcraft.capability.stat;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class StatStorage implements Capability.IStorage<IStats>{

	@Override
	public INBT writeNBT(Capability<IStats> capability, IStats instance, Direction side) {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 5; j++) {
				data.putInt("accuracy_" + i + "_" + j, instance.getSlotAccuracy(i, j));
				data.putInt("defence_" + i + "_" + j, instance.getSlotDefence(i, j));
			}
		
			data.putInt("melee_strength_" + i, instance.getSlotMeleeStrength(i));
			data.putInt("ranged_strength_" + i, instance.getSlotRangedStrength(i));
			data.putDouble("magic_strength_" + i, instance.getSlotMagicStrength(i));
		}
		
		return data;
	}

	@Override
	public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 5; j++) {
				instance.setSlotAccuracy(i, j, data.getInt("accuracy_" + i + "_" + j));
				instance.setSlotDefence(i, j, data.getInt("defence_" + i + "_" + j));
			}
		
			instance.setSlotMeleeStrength(i, data.getInt("melee_strength_" + i));
			instance.setSlotRangedStrength(i, data.getInt("ranged_strength_" + i));
			instance.setSlotMagicStrength(i, data.getDouble("magic_strength_" + i));
		}
	}

}
