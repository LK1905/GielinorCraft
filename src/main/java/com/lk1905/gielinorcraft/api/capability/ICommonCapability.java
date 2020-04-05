package com.lk1905.gielinorcraft.api.capability;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public interface ICommonCapability {

	void serializePacket(PacketBuffer buf);
	void deserializePacket(PacketBuffer buf);
	ResourceLocation getCapabilityID();
}
