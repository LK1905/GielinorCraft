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

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityHandler {

	public static final ResourceLocation SKILLS_CAP = new ResourceLocation(Gielinorcraft.MODID, "skills");;
	
	@SubscribeEvent
	public static void onAttachCapabilites(AttachCapabilitiesEvent<Entity> event) {
		
		if(event.getObject() instanceof LivingEntity) {
			final Skills skills = new Skills((LivingEntity) event.getObject());
			event.addCapability(SKILLS_CAP, SkillCapability.createProvider(skills));
		}
	}
	
	public static void register() {
		CapabilityManager.INSTANCE.register(ISkills.class, new SkillStorage(), () -> new Skills(null));
	}
}
