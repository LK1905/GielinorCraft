package lk1905.gielinorcraft.network.attackstyle;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class ChangeStylePacket {

	private final int slot;
	private final int id;
	private final String name;
	private final String descript;
	
	public ChangeStylePacket(int slot, int id, String name, String descript) {
		this.slot = slot;
		this.id = id;
		this.name = name;
		this.descript = descript;
	}
	
	public static void encode(ChangeStylePacket msg, PacketBuffer buf) {
		buf.writeInt(msg.slot);
		buf.writeInt(msg.id);
		buf.writeString(msg.name);
		buf.writeString(msg.descript);
	}
	
	public static ChangeStylePacket decode(PacketBuffer buf) {
		return new ChangeStylePacket(buf.readInt(), buf.readInt(), buf.readString(), buf.readString());
	}
	
	public static class Handler{
		public static void handle(final ChangeStylePacket msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				
				Minecraft mc = Minecraft.getInstance();
				
				mc.player.getCapability(AttackStyleCapability.STYLE_CAP).ifPresent(cap -> {
					cap.setStyleId(msg.slot, msg.id);
					cap.setStyleName(msg.slot, msg.name);
					cap.setStyleDescription(msg.slot, msg.descript);
				});
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
