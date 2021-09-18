package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.capability.SerializableCapabilityProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class AttackStyleCapability {

	@CapabilityInject(IAttackStyle.class)
	public static Capability<IAttackStyle> STYLE_CAP = null;

	public static ICapabilityProvider createProvider(final IAttackStyle style) {
		return new SerializableCapabilityProvider<>(STYLE_CAP, null, style);
	}
}
