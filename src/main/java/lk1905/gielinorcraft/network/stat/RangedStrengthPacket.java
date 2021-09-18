package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class RangedStrengthPacket {

	private int equip;
	private int stat;
	
	public RangedStrengthPacket(int equipSlot, int newStat) {
		equip = equipSlot;
		stat = newStat;
	}
	
	public static void encode(RangedStrengthPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.equip);
		buf.writeInt(msg.stat);
	}
	
	public static RangedStrengthPacket decode(PacketBuffer buf) {
		return new RangedStrengthPacket(buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final RangedStrengthPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setSlotRangedStrength(msg.equip, msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
