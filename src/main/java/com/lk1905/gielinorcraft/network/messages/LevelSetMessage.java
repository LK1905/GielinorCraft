package com.lk1905.gielinorcraft.network.messages;

import java.util.function.Supplier;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.api.utils.NetworkUtils;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class LevelSetMessage {

	private int newLevel;
	private String skillName;
	
	public LevelSetMessage(String skillName, int newLevel) {
		
		this.skillName = skillName;
		this.newLevel = newLevel;
	}
	
	public static LevelSetMessage fromBytes(PacketBuffer buf) {
		
		String skillName = NetworkUtils.readStringFromBuffer(buf);
		int newLevel = buf.readInt();
		
		return new LevelSetMessage(skillName, newLevel);
	}
	
	public static void toBytes(LevelSetMessage msg, PacketBuffer buf) {
		
		NetworkUtils.writeStringToBuffer(buf, msg.skillName);
		buf.writeInt(msg.newLevel);
	}
	
	public int getNewLevel() {
		
		return newLevel;
	}
	
	public String getSkillName() {
		
		return skillName;
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() -> {
			
			ISkillContainer skillCapability = Gielinorcraft.getSkillCapability();
			
			ISkill skill = skillCapability.getSkill(getSkillName());
			
			skill.setLevel(getNewLevel());
		});
	}
}
