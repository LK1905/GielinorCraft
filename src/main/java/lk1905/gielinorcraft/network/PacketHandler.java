package lk1905.gielinorcraft.network;

import java.util.List;

import lk1905.gielinorcraft.network.attackstyle.*;
import lk1905.gielinorcraft.network.skill.*;
import lk1905.gielinorcraft.network.stat.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

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
		HANDLER.registerMessage(disc++,
				XPGainPacket.class,
				XPGainPacket::encode,
				XPGainPacket::decode,
				XPGainPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				LevelUpPacket.class,
				LevelUpPacket::encode,
				LevelUpPacket::decode,
				LevelUpPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				StringPacket.class,
				StringPacket::encode,
				StringPacket::decode,
				StringPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				AttackStyleCapPacket.class,
				AttackStyleCapPacket::encode,
				AttackStyleCapPacket::decode,
				AttackStyleCapPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				AttackStyleClientPacket.class,
				AttackStyleClientPacket::encode,
				AttackStyleClientPacket::decode,
				AttackStyleClientPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				AttackStyleServerPacket.class,
				AttackStyleServerPacket::encode,
				AttackStyleServerPacket::decode,
				AttackStyleServerPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				ChangeStylePacket.class,
				ChangeStylePacket::encode,
				ChangeStylePacket::decode,
				ChangeStylePacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				AccuracyPacket.class,
				AccuracyPacket::encode,
				AccuracyPacket::decode,
				AccuracyPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				DefencePacket.class,
				DefencePacket::encode,
				DefencePacket::decode,
				DefencePacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				MeleeStrengthPacket.class,
				MeleeStrengthPacket::encode,
				MeleeStrengthPacket::decode,
				MeleeStrengthPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				RangedStrengthPacket.class,
				RangedStrengthPacket::encode,
				RangedStrengthPacket::decode,
				RangedStrengthPacket.Handler::handle);
		HANDLER.registerMessage(disc++,
				MagicStrengthPacket.class,
				MagicStrengthPacket::encode,
				MagicStrengthPacket::decode,
				MagicStrengthPacket.Handler::handle);
	}
	
	/**
	 * Sends a packet to a specific player.<br>
	 * Must be called server side.
	 * */
	public static void sendTo(Object msg, ServerPlayer player) {
		if(!(player instanceof FakePlayer)) {
			HANDLER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
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
		List<ServerPlayer> list = server.getPlayerList().getPlayers();
		for(ServerPlayer e : list) {
			sendTo(msg, e);
		}
	}
}
