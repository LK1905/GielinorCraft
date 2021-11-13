package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class ChangeStylePacket {

	private final int slot;
	private final int style;
	
	public ChangeStylePacket(int slot, int style) {
		this.slot = slot;
		this.style = style;
	}
	
	public static void encode(ChangeStylePacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.slot);
		buf.writeInt(msg.style);
	}
	
	public static ChangeStylePacket decode(FriendlyByteBuf buf) {
		return new ChangeStylePacket(buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final ChangeStylePacket msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				
				Minecraft mc = Minecraft.getInstance();
				
				mc.player.getCapability(AttackStyleCapability.STYLE_CAP).ifPresent(cap -> {
					cap.setAttackStyle(msg.slot, msg.style);
				});
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
