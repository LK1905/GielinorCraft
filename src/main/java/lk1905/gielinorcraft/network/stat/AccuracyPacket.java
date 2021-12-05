package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AccuracyPacket {

	private int equip;
	private int type;
	private int stat;
	
	public AccuracyPacket(int equipSlot, int typeSlot, int newStat) {
		equip = equipSlot;
		type = typeSlot;
		stat = newStat;
	}
	
	public static void encode(AccuracyPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.equip);
		buf.writeInt(msg.type);
		buf.writeInt(msg.stat);
	}
	
	public static AccuracyPacket decode(FriendlyByteBuf buf) {
		return new AccuracyPacket(buf.readInt(), buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final AccuracyPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setSlotAccuracy(msg.equip, msg.type, msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
