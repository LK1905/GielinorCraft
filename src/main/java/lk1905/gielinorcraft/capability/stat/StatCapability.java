package lk1905.gielinorcraft.capability.stat;

import lk1905.gielinorcraft.capability.SerializableCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class StatCapability {

	public static Capability<IStats> STAT_CAP = CapabilityManager.get(new CapabilityToken<>() {});

	public static ICapabilityProvider createProvider(final IStats stats) {
		return new SerializableCapabilityProvider<>(STAT_CAP, null, stats);
	}
}
