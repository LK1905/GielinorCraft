package com.lk1905.gielinorcraft.network.messages;

import java.nio.charset.Charset;
import java.util.function.Supplier;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class XPGainMessage {

	private String skillName;
	private double xpGained;
	
	public XPGainMessage(String newSkillName, double newXpGained) {
		
		skillName = newSkillName;
		xpGained = newXpGained;
	}
	
	public static XPGainMessage fromBytes(PacketBuffer buf) {
		
		int skillNameLength = buf.readInt();
		String skillName = buf.readCharSequence(skillNameLength, Charset.defaultCharset()).toString();
		double xpGained = buf.readDouble();
		
		return new XPGainMessage(skillName, xpGained);
	}
	
	public static void toBytes(XPGainMessage msg, PacketBuffer buf) {
		
		buf.writeInt(msg.skillName.length());
		buf.writeCharSequence(msg.skillName, Charset.defaultCharset());
		buf.writeDouble(msg.xpGained);
	}
	
	public String getSkillName() {
		
		return skillName;
	}
	
	public double getXpGained() {
		
		return xpGained;
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() -> {
			
			ISkillContainer skillCapability = Gielinorcraft.getSkillCapability();
			ISkill skill = skillCapability.getSkill(getSkillName());
			
			skill.setXP(skill.getXP() + getXpGained());
		});
	}
}
