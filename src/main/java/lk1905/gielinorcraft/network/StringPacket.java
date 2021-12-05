package lk1905.gielinorcraft.network;

import java.util.function.Supplier;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

/**Sends a string from the server to a single client.*/
public class StringPacket {

	private String string;
	
	public StringPacket(String string) {
		this.string = string;
	}
	
	public static void encode(StringPacket msg, FriendlyByteBuf buf) {
		buf.writeUtf(msg.string);
	}
	
	public static StringPacket decode(FriendlyByteBuf buf) {
		return new StringPacket(buf.readUtf());
	}
	
	public static class Handler{
		public static void handle(final StringPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			Player player = mc.player;
			
			ctx.get().enqueueWork(() -> {
				
				player.sendMessage(new TextComponent(msg.string), Util.NIL_UUID);
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
