package lk1905.gielinorcraft.events;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if(!event.isWasDeath()) {
			return;
		}
		
		ISkills oldSkills = event.getOriginal().getCapability(SkillCapability.SKILL_CAP, null).orElse(null);
		ISkills newSkills = event.getPlayer().getCapability(SkillCapability.SKILL_CAP, null).orElse(null);
		
		if(oldSkills != null) {
			if(newSkills != null) {
				for(int i = 0; i < 26; i++) {
					newSkills.setXp(i, oldSkills.getXp(i));
					newSkills.setStaticLevel(i, oldSkills.getStaticLevel(i));
					newSkills.setLevel(i, oldSkills.getLevel(i));
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerChangedDimensionEvent(PlayerChangedDimensionEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		if(!player.world.isRemote) {
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.sync(player));
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.modifyMaxHealth(player));
		}
	}
	
	@SubscribeEvent
	public static void onRespawnEvent(PlayerRespawnEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		if(!player.world.isRemote) {
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.sync(player));
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.modifyMaxHealth(player));
		}
	}
	
	@SubscribeEvent
	public static void onPlayerConnect(PlayerLoggedInEvent event) {
		ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
		if(!player.world.isRemote) {
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.sync(player));
			player.getCapability(SkillCapability.SKILL_CAP).ifPresent(c -> c.modifyMaxHealth(player));
		}
	}
}
