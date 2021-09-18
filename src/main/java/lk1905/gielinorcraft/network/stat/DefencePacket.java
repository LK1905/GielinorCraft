package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class DefencePacket {

	private int equip;
	private int type;
	private int stat;
	
	public DefencePacket(int equipSlot, int typeSlot, int newStat) {
		equip = equipSlot;
		type = typeSlot;
		stat = newStat;
	}
	
	public static void encode(DefencePacket msg, PacketBuffer buf) {
		buf.writeInt(msg.equip);
		buf.writeInt(msg.type);
		buf.writeInt(msg.stat);
	}
	
	public static DefencePacket decode(PacketBuffer buf) {
		return new DefencePacket(buf.readInt(), buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final DefencePacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setSlotDefence(msg.equip, msg.type, msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
