package com.lk1905.gielinorcraft.api.capability;

import java.util.Map;

import com.lk1905.gielinorcraft.api.skills.ISkill;

public interface ISkillContainer extends ICommonCapability{

	Map<String, ISkill> getAllSkills();
	
	Map<String, Double> getAllSkillXP();
	
	ISkill getSkill(String name);
	
	void setAllSkills(Map<String, ISkill> newSkills);
}
