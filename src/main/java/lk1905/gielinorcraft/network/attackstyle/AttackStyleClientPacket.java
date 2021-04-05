package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AttackStyleClientPacket {

	private final int slot;
	
	public AttackStyleClientPacket(int slot) {
		this.slot = slot;
	}
	
	public static void encode(AttackStyleClientPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.slot);
	}
	
	public static AttackStyleClientPacket decode(PacketBuffer buf) {
		return new AttackStyleClientPacket(buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final AttackStyleClientPacket msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				
				ServerPlayerEntity player = ctx.get().getSender();
				
				player.getCapability(AttackStyleCapability.STYLE_CAP).ifPresent(cap -> cap.setActiveStyle(msg.slot));
				PacketHandler.sendTo(new AttackStyleServerPacket(msg.slot), player);
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
