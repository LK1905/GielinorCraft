package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AttackStyleCapPacket {

private final CompoundNBT nbt;
	
	public AttackStyleCapPacket(CompoundNBT nbt) {
		this.nbt = nbt;
	}
	
	public static void encode(AttackStyleCapPacket msg, PacketBuffer buf) {
		buf.writeCompoundTag(msg.nbt);
	}
	
	public static AttackStyleCapPacket decode(PacketBuffer buf) {
		return new AttackStyleCapPacket(buf.readCompoundTag());
	}
	
	public static class Handler{
		public static void handle(final AttackStyleCapPacket msg, Supplier<NetworkEvent.Context> ctx) {
		
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(AttackStyleCapability.STYLE_CAP).ifPresent(cap -> cap.deserializeNBT(msg.nbt));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
