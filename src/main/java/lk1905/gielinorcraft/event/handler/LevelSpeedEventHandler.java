package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.skill.Skills;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LevelSpeedEventHandler {

	@SubscribeEvent
	public static void setDiggingSpeed(BreakSpeed event) {
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		if(!player.level.isClientSide) {
			if(player.getMainHandItem().getItem() instanceof ShovelItem) {
				event.setNewSpeed(event.getOriginalSpeed() * (0.5F + skills.getLevel(Skills.DIGGING) / 100));
			}
		}
	}
	
	@SubscribeEvent
	public static void setMiningSpeed(BreakSpeed event) {
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		if(!player.level.isClientSide) {
			if(player.getMainHandItem().getItem() instanceof PickaxeItem) {
				event.setNewSpeed(event.getOriginalSpeed() * (0.5F + skills.getLevel(Skills.MINING) / 100));
			}
		}
	}
	
	@SubscribeEvent
	public static void setWoodcuttingSpeed(BreakSpeed event) {
		Player player = event.getPlayer();
		ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
		
		if(!player.level.isClientSide) {
			if(player.getMainHandItem().getItem() instanceof AxeItem) {
				event.setNewSpeed(event.getOriginalSpeed() * (0.5F + skills.getLevel(Skills.WOODCUTTING) / 100));
			}
		}
	}
}
