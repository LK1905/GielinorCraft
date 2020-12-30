package lk1905.gielinorcraft.network;

import java.util.List;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
												.named(new ResourceLocation("gielinorcraft", "main_channel"))
												.clientAcceptedVersions(PROTOCOL_VERSION::equals)
												.serverAcceptedVersions(PROTOCOL_VERSION::equals)
												.networkProtocolVersion(() -> PROTOCOL_VERSION)
												.simpleChannel();
	public static void register() {
		int disc = 0;
		HANDLER.registerMessage(disc++,
				SkillsPacket.class,
				SkillsPacket::encode,
				SkillsPacket::decode,
				SkillsPacket.Handler::handle);
	}
	
	/**
	 * Sends a packet to a specific player.<br>
	 * Must be called server side.
	 * */
	public static void sendTo(Object msg, ServerPlayerEntity player) {
		if(!(player instanceof FakePlayer)) {
			HANDLER.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	/**
	 * Sends a packet to the server.<br>
	 * Must be called client side.
	 * */
	public static void sendToServer(Object msg) {
		HANDLER.sendToServer(msg);
	}
	
	/**Server side.*/
	public static void sendToAllPlayers(Object msg, MinecraftServer server) {
		List<ServerPlayerEntity> list = server.getPlayerList().getPlayers();
		for(ServerPlayerEntity e : list) {
			sendTo(msg, e);
		}
	}
}
