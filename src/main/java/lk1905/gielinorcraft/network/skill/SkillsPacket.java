package lk1905.gielinorcraft.network.skill;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SkillsPacket {

	private final CompoundTag nbt;
	
	public SkillsPacket(CompoundTag nbt) {
		this.nbt = nbt;
	}
	
	public static void encode(SkillsPacket msg, FriendlyByteBuf buf) {
		buf.writeNbt(msg.nbt);
	}
	
	public static SkillsPacket decode(FriendlyByteBuf buf) {
		return new SkillsPacket(buf.readNbt());
	}
	
	public static class Handler{
		public static void handle(final SkillsPacket msg, Supplier<NetworkEvent.Context> ctx) {
		
			Minecraft mc = Minecraft.getInstance();
			
			ctx.get().enqueueWork(() -> {
				
				mc.player.getCapability(SkillCapability.SKILL_CAP).ifPresent(cap -> cap.deserializeNBT(msg.nbt));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
