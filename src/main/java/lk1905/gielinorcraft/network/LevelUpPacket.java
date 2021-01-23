package lk1905.gielinorcraft.network;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class LevelUpPacket {

	private int id;
	private int level;
	
	public LevelUpPacket(int skillId, int newLevel) {
		id = skillId;
		level = newLevel;
	}
	
	public static void encode(LevelUpPacket msg, PacketBuffer buf) {
		buf.writeInt(msg.id);
		buf.writeInt(msg.level);
	}
	
	public static LevelUpPacket decode(PacketBuffer buf) {
		return new LevelUpPacket(buf.readInt(), buf.readInt());
	}
	
	public static class Handler{
		public static void handle(final LevelUpPacket msg, Supplier<NetworkEvent.Context> ctx) {
			
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(SkillCapability.SKILL_CAP).ifPresent(cap -> cap.setStaticLevel(msg.id, msg.level));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
