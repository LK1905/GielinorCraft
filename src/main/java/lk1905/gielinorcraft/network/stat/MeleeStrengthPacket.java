package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class MeleeStrengthPacket {

	private int equip;
	private int stat;
	
	public MeleeStrengthPacket(int equipSlot, int newStat) {
		equip = equipSlot;
		stat = newStat;
	}
	
	public static void encode(MeleeStrengthPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.equip);
		buf.writeInt(msg.stat);
	}
	
	public static MeleeStrengthPacket decode(FriendlyByteBuf buf) {
		return new MeleeStrengthPacket(buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final MeleeStrengthPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.setSlotMeleeStrength(msg.equip, msg.stat));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
