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
		
		data.putDouble("slayer", instance.getSlayerBonus());
		data.putDouble("undead", instance.getUndeadBonus());
		data.putDouble("demonic", instance.getDemonicBonus());
		data.putDouble("draconic", instance.getDraconicBonus());
		data.putDouble("other", instance.getOtherBonus());
		
		data.putDouble("melee_absorbtion", instance.getMeleeAbsorbtion());
		data.putDouble("ranged_absorbtion", instance.getRangedAbsorbtion());
		data.putDouble("magic_absorbtion", instance.getMagicAbsorbtion());
		
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
		
		instance.setSlayerBonus(data.getDouble("slayer"));
		instance.setUndeadBonus(data.getDouble("undead"));
		instance.setDemonicBonus(data.getDouble("demonic"));
		instance.setDraconicBonus(data.getDouble("draconic"));
		instance.setOtherBonus(data.getDouble("other"));
		
		instance.setMeleeAbsorbtion(data.getDouble("melee_absorbtion"));
		instance.setRangedAbsorbtion(data.getDouble("ranged_absorbtion"));
		instance.setMagicAbsorbtion(data.getDouble("magic_absorbtion"));
	}

}
