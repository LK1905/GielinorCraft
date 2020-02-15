package com.lk1905.gielinorcraft.capability.skill;

import java.util.Map;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class SkillStorage implements Capability.IStorage<ISkillContainer>{

	public SkillStorage() {
		
	}
	
	@Override
	public CompoundNBT writeNBT(Capability<ISkillContainer> capability, ISkillContainer instance, Direction side) {
		
		CompoundNBT skillData = new CompoundNBT();
		
		for(Map.Entry<String, ISkill> entry : instance.getAllSkills().entrySet()) {
			
			skillData.put(entry.getKey(), entry.getValue().serializeNBT());
		}
		
		return skillData;
	}
	
	@Override
	public void readNBT(Capability<ISkillContainer> capability, ISkillContainer instance, Direction side, INBT nbt) {
		
		CompoundNBT skillData = (CompoundNBT) nbt;
		
		for(String key : skillData.keySet()) {
			
			instance.getSkill(key).deserializeNBT(skillData.getCompound(key));
		}
	}

}
