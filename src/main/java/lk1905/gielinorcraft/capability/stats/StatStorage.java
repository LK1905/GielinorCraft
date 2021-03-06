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
		
		data.putInt("stab_accuracy", instance.getStabAccuracy());
		data.putInt("slash_accuracy", instance.getSlashAccuracy());
		data.putInt("crush_accuracy", instance.getCrushAccuracy());
		data.putInt("ranged_accuracy", instance.getRangedAccuracy());
		data.putInt("magic_accuracy", instance.getMagicAccuracy());
		
		data.putInt("stab_defence", instance.getStabDefence());
		data.putInt("slash_defence", instance.getSlashDefence());
		data.putInt("crush_defence", instance.getCrushDefence());
		data.putInt("ranged_defence", instance.getRangedDefence());
		data.putInt("magic_defence", instance.getMagicDefence());
		
		data.putInt("melee_strength", instance.getMeleeStrength());
		data.putInt("ranged_strength", instance.getRangedStrength());
		data.putDouble("magic_strength", instance.getMagicStrength());
		
		data.putDouble("slayer", instance.getSlayerBonus());
		data.putDouble("undead", instance.getUndeadBonus());
		data.putDouble("demonic", instance.getDemonicBonus());
		data.putDouble("draconic", instance.getDraconicBonus());
		data.putDouble("other", instance.getOtherBonus());
		
		return data;
	}

	@Override
	public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;
		
		instance.setStabAccuracy(data.getInt("stab_accuracy"));
		instance.setSlashAccuracy(data.getInt("slash_accuracy"));
		instance.setCrushAccuracy(data.getInt("crush_accuracy"));
		instance.setRangedAccuracy(data.getInt("ranged_accuracy"));
		instance.setMagicAccuracy(data.getInt("magic_accuracy"));
		
		instance.setStabDefence(data.getInt("stab_defence"));
		instance.setSlashDefence(data.getInt("slash_defence"));
		instance.setCrushDefence(data.getInt("crush_defence"));
		instance.setRangedDefence(data.getInt("ranged_defence"));
		instance.setMagicDefence(data.getInt("magic_defence"));
		
		instance.setMeleeStrength(data.getInt("melee_strength"));
		instance.setRangedStrength(data.getInt("ranged_strength"));
		instance.setMagicStrength(data.getDouble("magic_strength"));
		
		instance.setSlayerBonus(data.getDouble("slayer"));
		instance.setUndeadBonus(data.getDouble("undead"));
		instance.setDemonicBonus(data.getDouble("demonic"));
		instance.setDraconicBonus(data.getDouble("draconic"));
		instance.setOtherBonus(data.getDouble("other"));
	}

}
