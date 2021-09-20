package lk1905.gielinorcraft.capability.stat;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

/**Don't actually need to use this class, as IStats Capability doesn't need to save anything.*/
public class StatStorage implements Capability.IStorage<IStats>{

	@Override
	public INBT writeNBT(Capability<IStats> capability, IStats instance, Direction side) {
		return new CompoundNBT();
	}

	@Override
	public void readNBT(Capability<IStats> capability, IStats instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
	}
}