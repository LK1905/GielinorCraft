package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class AttackStyleCapPacket {

private final CompoundTag nbt;
	
	public AttackStyleCapPacket(CompoundTag nbt) {
		this.nbt = nbt;
	}
	
	public static void encode(AttackStyleCapPacket msg, FriendlyByteBuf buf) {
		buf.writeNbt(msg.nbt);
	}
	
	public static AttackStyleCapPacket decode(FriendlyByteBuf buf) {
		return new AttackStyleCapPacket(buf.readNbt());
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
