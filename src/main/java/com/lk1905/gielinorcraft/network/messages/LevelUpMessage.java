package com.lk1905.gielinorcraft.network.messages;

import java.util.function.Supplier;

import com.lk1905.gielinorcraft.api.skills.SkillIcon;
import com.lk1905.gielinorcraft.api.utils.NetworkUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public class LevelUpMessage {

	private String skillName;
	private SkillIcon skillIcon;
	private int level;
	
	public LevelUpMessage(String skillName, SkillIcon skillIcon, int level) {
		
		this.skillName = skillName;
		this.skillIcon = skillIcon;
		this.level = level;
	}
	
	public static LevelUpMessage fromBytes(PacketBuffer buf) {
		
		String skillName = NetworkUtils.readStringFromBuffer(buf);
		SkillIcon skillIcon = NetworkUtils.readSkillIconFromBuffer(buf);
		int level = buf.readInt();
		
		return new LevelUpMessage(skillName, skillIcon, level);
	}
	
	public static void toBytes(LevelUpMessage msg, PacketBuffer buf) {
		
		NetworkUtils.writeStringToBuffer(buf, msg.skillName);
		NetworkUtils.writeSkillIconToBuffer(buf, msg.skillIcon);
		buf.writeInt(msg.level);
	}
	
	public String getSkillName() {
		
		return skillName;
	}
	
	public SkillIcon getSkillIcon() {
		
		return skillIcon;
	}
	
	public int getLevel() {
		
		return level;
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() -> {
			
			ResourceLocation location = new ResourceLocation("minecraft", "entity.player.levelup");
			
			Minecraft.getInstance().player.playSound(new SoundEvent(location), 100, 100);
		});
		ctx.get().setPacketHandled(true);
	}
}
