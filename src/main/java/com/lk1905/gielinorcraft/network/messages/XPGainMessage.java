package com.lk1905.gielinorcraft.network.messages;

import java.nio.charset.Charset;
import java.util.function.Supplier;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.client.ClientProxy;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class XPGainMessage {

	private String skillName;
	private int xpGained;
	
	public XPGainMessage(String newSkillName, int newXpGained) {
		
		skillName = newSkillName;
		xpGained = newXpGained;
	}
	
	public static XPGainMessage fromBytes(PacketBuffer buf) {
		
		int skillNameLength = buf.readInt();
		String skillName = buf.readCharSequence(skillNameLength, Charset.defaultCharset()).toString();
		int xpGained = buf.readInt();
		
		return new XPGainMessage(skillName, xpGained);
	}
	
	public static void toBytes(XPGainMessage msg, PacketBuffer buf) {
		
		buf.writeInt(msg.skillName.length());
		buf.writeCharSequence(msg.skillName, Charset.defaultCharset());
		buf.writeInt(msg.xpGained);
	}
	
	public String getSkillName() {
		
		return skillName;
	}
	
	public int getXpGained() {
		
		return xpGained;
	}
	
	public void handle(Supplier<NetworkEvent.Context> ctx) {
		
		ctx.get().enqueueWork(() -> {
			
			ISkillContainer skillCapability = ClientProxy.getSkillCapability();
			ISkill skill = skillCapability.getSkill(getSkillName());
			
			skill.setXP(skill.getXP() + getXpGained());
		});
		ctx.get().setPacketHandled(true);
	}
}
