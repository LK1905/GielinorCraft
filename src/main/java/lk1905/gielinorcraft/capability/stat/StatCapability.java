package lk1905.gielinorcraft.capability.stat;

import lk1905.gielinorcraft.capability.SerializableCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class StatCapability {

	@CapabilityInject(IStats.class)
	public static Capability<IStats> STAT_CAP = null;

	public static ICapabilityProvider createProvider(final IStats stats) {
		return new SerializableCapabilityProvider<>(STAT_CAP, null, stats);
	}
}
