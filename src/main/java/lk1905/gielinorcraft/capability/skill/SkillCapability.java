package lk1905.gielinorcraft.capability.skill;

import lk1905.gielinorcraft.capability.SerializableCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class SkillCapability {

	public static Capability<ISkills> SKILL_CAP = CapabilityManager.get(new CapabilityToken<>() {});

	public static ICapabilityProvider createProvider(final ISkills skills) {
		return new SerializableCapabilityProvider<>(SKILL_CAP, null, skills);
	}
}
