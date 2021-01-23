package lk1905.gielinorcraft.capability.skill;

import lk1905.gielinorcraft.api.capability.SerializableCapabilityProvider;
import lk1905.gielinorcraft.api.skill.ISkills;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SkillCapability {

	@CapabilityInject(ISkills.class)
	public static Capability<ISkills> SKILL_CAP = null;

	public static ICapabilityProvider createProvider(final ISkills skills) {
		return new SerializableCapabilityProvider<>(SKILL_CAP, null, skills);
	}
}
