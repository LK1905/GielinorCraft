package com.lk1905.gielinorcraft.network.handlers;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.network.messages.StatsResponseMessage;

import net.minecraftforge.fml.network.NetworkEvent;

public class StatsResponseHandler {

	private final static Set<BiConsumer<StatsResponseMessage, Supplier<NetworkEvent.Context>>> currentListeners = new HashSet<>();
	
	public static void registerListener(BiConsumer<StatsResponseMessage, Supplier<NetworkEvent.Context>> listener) {
		
		currentListeners.add(listener);
	}
	
	public StatsResponseMessage onMessage(StatsResponseMessage message, Supplier<NetworkEvent.Context> ctx) {
		
		Gielinorcraft.logger.info("Received a Stats Response Message!");
		
		for(BiConsumer<StatsResponseMessage, Supplier<NetworkEvent.Context>> listener : currentListeners) {
			
			listener.accept(message, ctx);
		}
		
		return null;
	}
}
