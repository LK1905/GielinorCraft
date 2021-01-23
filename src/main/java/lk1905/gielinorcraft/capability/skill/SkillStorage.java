package lk1905.gielinorcraft.capability.skill;

import javax.annotation.Nullable;

import lk1905.gielinorcraft.api.skill.ISkills;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SkillStorage implements Capability.IStorage<ISkills>{

	@Nullable
	@Override
	public INBT writeNBT(Capability<ISkills> capability, ISkills instance, Direction side) {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 26; i++) {
			data.putInt("xp_" + i, (int) instance.getXp(i));
			data.putInt("static_" + i, instance.getStaticLevel(i));
			data.putInt("dynamic_"+ i, instance.getLevel(i));
		}
		return data;
	}

	@Override
	public void readNBT(Capability<ISkills> capability, ISkills instance, Direction side, INBT nbt) {
		if(!(nbt instanceof CompoundNBT)) {
			return;
		}
		CompoundNBT data = (CompoundNBT) nbt;

		for(int i = 0; i < 26; i++) {
			instance.setXp(i, data.getInt("xp_" + i));
			instance.setStaticLevel(i, data.getInt("static_" + i));
			instance.setLevel(i, data.getInt("dynamic_"+ i));
		}
	}

}
