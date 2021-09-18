package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.*;
import lk1905.gielinorcraft.capability.skill.*;
import lk1905.gielinorcraft.capability.stat.*;
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
	public static final ResourceLocation ATTACK_STYLE_CAP = new ResourceLocation(Gielinorcraft.MODID, "styles");
	
	@SubscribeEvent
	public static void onAttachCapabilites(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof LivingEntity) {
			final Skills skills = new Skills((LivingEntity) event.getObject());
			event.addCapability(SKILLS_CAP, SkillCapability.createProvider(skills));
			
			final Stats stats = new Stats((LivingEntity) event.getObject());
			event.addCapability(STATS_CAP, StatCapability.createProvider(stats));
			
			final AttackStyle style = new AttackStyle((LivingEntity) event.getObject());
			event.addCapability(ATTACK_STYLE_CAP, AttackStyleCapability.createProvider(style));
		}
	}
	
	public static void register() {
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillStorage(), () -> new Skills(null));
		CapabilityManager.INSTANCE.register(IStats.class, new StatStorage(), () -> new Stats(null));
		CapabilityManager.INSTANCE.register(IAttackStyle.class, new AttackStyleStorage(), () -> new AttackStyle(null));
	}
}
