package lk1905.gielinorcraft.network.stat;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.stats.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class StatsPacket {

private final CompoundNBT nbt;
	
	public StatsPacket(CompoundNBT nbt) {
		this.nbt = nbt;
	}
	
	public static void encode(StatsPacket msg, PacketBuffer buf) {
		buf.writeCompoundTag(msg.nbt);
	}
	
	public static StatsPacket decode(PacketBuffer buf) {
		return new StatsPacket(buf.readCompoundTag());
	}
	
	public static class Handler{
		public static void handle(final StatsPacket msg, Supplier<NetworkEvent.Context> ctx) {
		
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(StatCapability.STAT_CAP).ifPresent(cap -> cap.deserializeNBT(msg.nbt));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
