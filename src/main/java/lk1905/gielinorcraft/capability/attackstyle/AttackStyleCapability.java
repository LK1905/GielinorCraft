package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.capability.SerializableCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class AttackStyleCapability {

	public static Capability<IAttackStyle> STYLE_CAP = CapabilityManager.get(new CapabilityToken<>() {});

	public static ICapabilityProvider createProvider(final IAttackStyle style) {
		return new SerializableCapabilityProvider<>(STYLE_CAP, null, style);
	}
}