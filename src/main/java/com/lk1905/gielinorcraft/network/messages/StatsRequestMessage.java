package com.lk1905.gielinorcraft.network.messages;

import net.minecraft.network.PacketBuffer;

public class StatsRequestMessage {

	public static StatsRequestMessage fromBytes(PacketBuffer buf) {
		
		return new StatsRequestMessage();
	}
	
	public static void toBytes(StatsRequestMessage msg, PacketBuffer buf) {
		
	}
}
