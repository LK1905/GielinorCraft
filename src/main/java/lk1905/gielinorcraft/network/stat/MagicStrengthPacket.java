package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class MagicStrengthPacket {

	private int equip;
	private double stat;
	
	public MagicStrengthPacket(int equipSlot, double newStat) {
		equip = equipSlot;
		stat = newStat;
	}
	
	public static void encode(MagicStrengthPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.equip);
		buf.writeDouble(msg.stat);
	}
	
	public static MagicStrengthPacket decode(FriendlyByteBuf buf) {
		return new MagicStrengthPacket(buf.readInt(), buf.readDouble());
	}
	
	public static class Handler{
		public static void handle(final MagicStrengthPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setSlotMagicStrength(msg.equip, msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
