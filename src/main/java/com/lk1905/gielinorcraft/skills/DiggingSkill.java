package com.lk1905.gielinorcraft.skills;

import com.lk1905.gielinorcraft.api.skills.Skill;
import com.lk1905.gielinorcraft.api.skills.SkillIcon;
import com.lk1905.gielinorcraft.api.utils.Position;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;

public class DiggingSkill extends Skill{

	@Override
	public String getName() {
		return "Digging";
	}
	
	@Override
	public SkillIcon getSkillIcon() {
		
		String texture = "minecraft:textures/items/iron_shovel.png";
		
		return new SkillIcon(new ResourceLocation(texture), new Position(0, 0), 16, 16);
	}
	
	private double xpForBlock(Block block) {
		
		if(block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.SAND || block == Blocks.GRAVEL
		|| block == Blocks.FARMLAND || block == Blocks.RED_SAND || block == Blocks.GRASS_PATH) {
			
			return 5;
			
		}else if(block == Blocks.MYCELIUM || block == Blocks.PODZOL) {
			
			return 10;
		}else if(block == Blocks.CLAY || block == Blocks.SNOW_BLOCK) {
			
			return 15;
		}else if(block == Blocks.SOUL_SAND) {
			
			return 20;
		}
		
		return 0;
	}
}
