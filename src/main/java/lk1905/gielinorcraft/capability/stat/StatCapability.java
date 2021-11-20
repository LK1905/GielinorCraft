package lk1905.gielinorcraft.capability.stat;

import lk1905.gielinorcraft.capability.SimpleCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class StatCapability {

	public static Capability<IStats> STAT_CAP = CapabilityManager.get(new CapabilityToken<>() {});

	public static ICapabilityProvider createProvider(final IStats stats) {
		return new SimpleCapabilityProvider<>(STAT_CAP, null, stats);
	}
}
