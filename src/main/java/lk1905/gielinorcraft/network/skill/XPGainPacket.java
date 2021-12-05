package lk1905.gielinorcraft.network.skill;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class XPGainPacket {

	private int id;
	private double xp;
	
	public XPGainPacket(int skillId, double xpGained) {
		id = skillId;
		xp = xpGained;
	}
	
	public static void encode(XPGainPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.id);
		buf.writeDouble(msg.xp);
	}
	
	public static XPGainPacket decode(FriendlyByteBuf buf) {
		return new XPGainPacket(buf.readInt(), buf.readDouble());
	}
	
	public static class Handler{
		public static void handle(final XPGainPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(SkillCapability.SKILL_CAP).ifPresent(cap -> cap.setXp(msg.id, cap.getXp(msg.id) + msg.xp));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
