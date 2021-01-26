package lk1905.gielinorcraft.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

/**Sends a string from the server to a single client.*/
public class StringPacket {

	private String string;
	
	public StringPacket(String string) {
		this.string = string;
	}
	
	public static void encode(StringPacket msg, PacketBuffer buf) {
		buf.writeString(msg.string);
	}
	
	public static StringPacket decode(PacketBuffer buf) {
		return new StringPacket(buf.readString());
	}
	
	public static class Handler{
		public static void handle(final StringPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			PlayerEntity player = mc.player;
			
			ctx.get().enqueueWork(() -> {
				
				player.sendMessage(new StringTextComponent(msg.string), Util.DUMMY_UUID);
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
