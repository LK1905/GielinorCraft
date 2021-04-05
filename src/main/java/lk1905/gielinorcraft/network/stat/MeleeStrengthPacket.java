package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stats.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MeleeStrengthPacket {

	private int stat;
	
	public MeleeStrengthPacket(int newStat) {
		stat = newStat;
	}
	
	public static void encode(MeleeStrengthPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.stat);
	}
	
	public static MeleeStrengthPacket decode(PacketBuffer buf) {
		return new MeleeStrengthPacket(buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final MeleeStrengthPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setMeleeStrength(cap.getMeleeStrength() + msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
