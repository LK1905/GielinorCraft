package lk1905.gielinorcraft.capability.skill;

import lk1905.gielinorcraft.api.skill.ISkills;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class SkillCapability implements ICapabilitySerializable<INBT>{

	@CapabilityInject(ISkills.class)
	public static final Capability<ISkills> SKILL_CAP = null;
	
	private LazyOptional<ISkills> instance = LazyOptional.of(() -> SKILL_CAP.getDefaultInstance());
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == SKILL_CAP ? instance.cast() : LazyOptional.empty();
	}

	@Override
	public INBT serializeNBT() {
		return SKILL_CAP.getStorage().writeNBT(SKILL_CAP, this.instance.orElseThrow(() ->
												new IllegalArgumentException("LazyOptional must not be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		SKILL_CAP.getStorage().readNBT(SKILL_CAP, this.instance.orElseThrow(() ->
										new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
		
	}

}
