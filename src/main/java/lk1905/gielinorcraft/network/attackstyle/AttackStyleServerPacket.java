package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class AttackStyleServerPacket {

	private final int slot;
	
	public AttackStyleServerPacket(int slot) {
		this.slot = slot;
	}
	
	public static void encode(AttackStyleServerPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.slot);
	}
	
	public static AttackStyleServerPacket decode(PacketBuffer buf) {
		return new AttackStyleServerPacket(buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final AttackStyleServerPacket msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				
				Minecraft mc = Minecraft.getInstance();
				
				mc.player.getCapability(AttackStyleCapability.STYLE_CAP).ifPresent(cap -> cap.setActiveSlot(msg.slot));
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
