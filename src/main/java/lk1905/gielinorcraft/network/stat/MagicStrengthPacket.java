package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MagicStrengthPacket {

private double stat;
	
	public MagicStrengthPacket(double newStat) {
		stat = newStat;
	}
	
	public static void encode(MagicStrengthPacket msg, PacketBuffer buf) {
		buf.writeDouble(msg.stat);
	}
	
	public static MagicStrengthPacket decode(PacketBuffer buf) {
		return new MagicStrengthPacket(buf.readDouble());
	}
	
	public static class Handler{
		public static void handle(final MagicStrengthPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setMagicStrength(cap.getMagicStrength() + msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
