package com.lk1905.gielinorcraft.capability.skill;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class SkillContainer implements ISkillContainer{

	private final static Set<Class<? extends ISkill>> skillClasses = new HashSet<>();
	private final Map<String, ISkill> skills;
	
	public SkillContainer() {
		
		skills = new HashMap<>();
		
		for(Class<? extends ISkill> skillClass : skillClasses) {
			
			ISkill skill;
			
			try {
				skill = skillClass.newInstance();
				
			}catch(InstantiationException | IllegalAccessException e) {
				
				Gielinorcraft.logger.error("Failed to initialise skill instance from class descriptor: " + skillClass.getName());
				continue;
			}
			
			skills.put(skill.getName(), skill);
		}
	}
	
	@Override
	public ISkill getSkill(String name) {
		
		return this.skills.get(name);
	}
	
	@Override
	public void setAllSkills(Map<String, ISkill> newSkills) {
		
		this.skills.putAll(newSkills);
	}
	
	@Override
	public void serializePacket(PacketBuffer buf) {
		
		buf.writeInt(skills.size());
		
		for(ISkill skill : skills.values()) {
			
			buf.writeInt(skill.getName().length());
			buf.writeCharSequence(skill.getName(), Charset.defaultCharset());
			skill.serializePacket(buf);
		}
	}
	
	@Override
	public void deserializePacket(PacketBuffer buf) {
		
		int numberOfSkills = buf.readInt();
		
		for(int i = 0; i < numberOfSkills; i++) {
			
			int lengthOfSkillName = buf.readInt();
			String skillName = buf.readCharSequence(lengthOfSkillName, Charset.defaultCharset()).toString();
			getSkill(skillName).deserializePacket(buf);
		}
	}
	
	@Override
	public ResourceLocation getCapabilityID() {
		
		return new ResourceLocation(Gielinorcraft.MODID, "skills");
	}
	
	@Override
	public Map<String, ISkill> getAllSkills(){
		return skills;
	}
	
	@Override
	public Map<String, Integer> getAllSkillXP(){
		
		Map<String, Integer> skillXP = new HashMap<>();
		
		for(Map.Entry<String, ISkill> entry : skills.entrySet())	{
			
			skillXP.put(entry.getKey(), entry.getValue().getXP());
		}
		
		return skillXP;
	}
	
	public static void registerNewSkill(Class<? extends ISkill> skillClass) {
		
		skillClasses.add(skillClass);
	}
}
