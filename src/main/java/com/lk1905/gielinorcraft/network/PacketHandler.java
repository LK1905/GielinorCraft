package com.lk1905.gielinorcraft.network;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.network.handlers.*;
import com.lk1905.gielinorcraft.network.messages.*;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private static final String PROTOCOL_VERSION = Integer.toString(1);
	
	private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Gielinorcraft.MODID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);
	
	public static void register() {
		
		int disc  = 0;
		
		INSTANCE.registerMessage(disc++,
				LevelSetMessage.class, 
				LevelSetMessage::toBytes, 
				LevelSetMessage::fromBytes, 
				LevelSetMessage::handle);
		
		INSTANCE.registerMessage(disc++,
				LevelUpMessage.class, 
				LevelUpMessage::toBytes, 
				LevelUpMessage::fromBytes, 
				LevelUpMessage::handle);
		
		INSTANCE.registerMessage(disc++,
				XPGainMessage.class,
				XPGainMessage::toBytes,
				XPGainMessage::fromBytes,
				XPGainMessage::handle);
		
		/*INSTANCE.registerMessage(disc++,
				StatsRequestMessage.class,
				null,
				null,
				StatsRequestHandler.class);
		
		INSTANCE.registerMessage(disc++,
				StatsResponseMessage.class,
				null,
				null,
				StatsResponseHandler.class);*/
	}
}
