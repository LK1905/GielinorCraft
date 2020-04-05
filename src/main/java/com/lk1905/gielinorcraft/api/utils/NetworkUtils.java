package com.lk1905.gielinorcraft.api.utils;

import java.nio.charset.Charset;

import com.lk1905.gielinorcraft.api.skills.SkillIcon;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class NetworkUtils {

	public static String readStringFromBuffer(PacketBuffer buf) {
		
		int length = buf.readInt();
		return buf.readCharSequence(length, Charset.defaultCharset()).toString();
	}
	
	public static void writeStringToBuffer(PacketBuffer buf, String message) {
		
		buf.writeInt(message.length());
		buf.writeCharSequence(message, Charset.defaultCharset());
	}
	
	public static ResourceLocation readResourceFromBuffer(PacketBuffer buf) {
		
		String domain = readStringFromBuffer(buf);
		String path = readStringFromBuffer(buf);
		
		return new ResourceLocation(domain, path);
	}
	
	public static void writeResourceToBuffer(PacketBuffer buf, ResourceLocation location) {
		
		writeStringToBuffer(buf, location.getPath());
	}
	
	public static SkillIcon readSkillIconFromBuffer(PacketBuffer buf) {
		
		ResourceLocation location = readResourceFromBuffer(buf);
		Position offset = readPositionFromBuffer(buf);
		
		int texWidth = buf.readInt();
		int texHeight = buf.readInt();
		
		return new SkillIcon(location, offset, texWidth, texHeight);
	}
	
	public static void writeSkillIconToBuffer(PacketBuffer buf, SkillIcon icon) {
		
		writeResourceToBuffer(buf, icon.getTextureLocation());
		writePositionToBuffer(buf, icon.getTextureOffset());
		
		buf.writeInt(icon.getTexWidth());
		buf.writeInt(icon.getTexHeight());
	}
	
	public static Position readPositionFromBuffer(PacketBuffer buf) {
		
		float x = buf.readFloat();
		float y = buf.readFloat();
		
		return new Position(x, y);
	}
	
	public static void writePositionToBuffer(PacketBuffer buf, Position position) {
		
		buf.writeFloat(position.getX());
		buf.writeFloat(position.getY());
	}
}
