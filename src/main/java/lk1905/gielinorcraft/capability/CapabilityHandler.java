package lk1905.gielinorcraft.capability;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.api.skill.Skills;
import lk1905.gielinorcraft.api.stats.*;
import lk1905.gielinorcraft.capability.skill.*;
import lk1905.gielinorcraft.capability.stats.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {

	public static final ResourceLocation SKILLS_CAP = new ResourceLocation(Gielinorcraft.MODID, "skills");
	public static final ResourceLocation STATS_CAP = new ResourceLocation(Gielinorcraft.MODID, "stats");
	public static final ResourceLocation MAX_HEALTH_CAP = new ResourceLocation(Gielinorcraft.MODID, "health");
	
	@SubscribeEvent
	public static void onAttachCapabilites(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof LivingEntity) {
			final Skills skills = new Skills((LivingEntity) event.getObject());
			event.addCapability(SKILLS_CAP, SkillCapability.createProvider(skills));
			
			final Stats stats = new Stats();
			event.addCapability(STATS_CAP, StatCapability.createProvider(stats));
		}
	}
	
	public static void register() {
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillStorage(), () -> new Skills(null));
		CapabilityManager.INSTANCE.register(IStats.class, new StatStorage(), () -> new Stats());
	}
}
