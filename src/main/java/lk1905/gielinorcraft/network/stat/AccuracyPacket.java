package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AccuracyPacket {

	private int id;
	private int stat;
	
	public AccuracyPacket(int styleId, int newStat) {
		id = styleId;
		stat = newStat;
	}
	
	public static void encode(AccuracyPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.id);
		buf.writeInt(msg.stat);
	}
	
	public static AccuracyPacket decode(PacketBuffer buf) {
		return new AccuracyPacket(buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final AccuracyPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setAccuracy(msg.id, cap.getAccuracy(msg.id) + msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
