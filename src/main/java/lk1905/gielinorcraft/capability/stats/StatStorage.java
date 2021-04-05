package lk1905.gielinorcraft.capability.stats;

import lk1905.gielinorcraft.api.stats.IStats;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class StatStorage implements Capability.IStorage<IStats>{

	@Override
	public INBT writeNBT(Capability<IStats> capability, IStats instance, Direction side) {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 5; i++) {
			data.putInt("accuracy_" + i, instance.getAccuracy(i));
			data.putInt("defence_" + i, instance.getDefence(i));
		}
		
		data.putInt("melee_strength", instance.getMeleeStrength());
		data.putInt("ranged_strength", instance.getRangedStrength());
		data.putDouble("magic_strength", instance.getMagicStrength());
		
		return data;
	}

	@Override
	public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;
		
		for(int i = 0; i < 5; i++) {
			instance.setAccuracy(i, data.getInt("accuracy_" + i));
			instance.setDefence(i, data.getInt("defence_" + i));
		}
		
		instance.setMeleeStrength(data.getInt("melee_strength"));
		instance.setRangedStrength(data.getInt("ranged_strength"));
		instance.setMagicStrength(data.getDouble("magic_strength"));
		
	}

}
