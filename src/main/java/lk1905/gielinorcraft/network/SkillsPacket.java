package lk1905.gielinorcraft.network;

import java.util.function.Supplier;

import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SkillsPacket {

	private final CompoundNBT nbt;
	
	public SkillsPacket(CompoundNBT nbt) {
		this.nbt = nbt;
	}
	
	public static void encode(SkillsPacket msg, PacketBuffer buf) {
		
	}
	
	public static SkillsPacket decode(PacketBuffer buf) {
		return new SkillsPacket(new CompoundNBT());
	}
	
	public static class Handler{
		public static void handle(final SkillsPacket msg, Supplier<NetworkEvent.Context> ctx) {
		
			ctx.get().enqueueWork(() -> {
				
				Minecraft.getInstance().player.getCapability(SkillCapability.SKILL_CAP).ifPresent(cap -> cap.deserializeNBT(msg.nbt));
				
			});
			ctx.get().setPacketHandled(true);
		}
	}
}
