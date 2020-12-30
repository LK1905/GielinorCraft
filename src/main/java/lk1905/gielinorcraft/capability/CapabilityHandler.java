package lk1905.gielinorcraft.capability;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.api.skill.Skills;
import lk1905.gielinorcraft.capability.skill.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {

	public static final ResourceLocation SKILLS_CAP = new ResourceLocation(Gielinorcraft.MODID, "skills");
	
	private static LivingEntity entity;
	
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillStorage(), () -> new Skills(entity));
	}
	
	@SubscribeEvent
	public static void onAttachCapabilites(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof LivingEntity) {
			event.addCapability(SKILLS_CAP, new SkillCapability());
		}
	}
}
