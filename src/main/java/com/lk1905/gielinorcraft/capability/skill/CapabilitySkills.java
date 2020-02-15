package com.lk1905.gielinorcraft.capability.skill;

import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.utils.CapabilityUtils;
import com.lk1905.gielinorcraft.capability.CommonCapabilityProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilitySkills {

	@CapabilityInject(ISkillContainer.class)
	private static Capability<ISkillContainer> skillContainerCapability;
	
	public static void register() {
		
		CapabilityManager.INSTANCE.register(ISkillContainer.class, new SkillStorage(), () -> new SkillContainer());//<---THIS LINE
	}
	
	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> e) {
		
		if(!(e.getObject() instanceof PlayerEntity)) {
			return;
		}
		
		SkillContainer newContainer = new SkillContainer();
		
		e.addCapability(newContainer.getCapabilityID(), 
						new CommonCapabilityProvider<>(skillContainerCapability, null, newContainer));
	}
	
	@SubscribeEvent
	public static void onPlayerClone(final PlayerEvent.Clone e) {
		
		ISkillContainer oldCapability = (ISkillContainer) CapabilityUtils.getCapability(e.getOriginal(), null);
		ISkillContainer newCapability = (ISkillContainer) CapabilityUtils.getCapability(e.getPlayer(), null);
		
		newCapability.setAllSkills(oldCapability.getAllSkills());
	}
	
	public static ISkillContainer getSkillContainer(ICapabilityProvider provider) {
		
		return (ISkillContainer) CapabilityUtils.getCapability(provider, skillContainerCapability);
	}
	
	public static Capability<ISkillContainer> getSkillCapability(){
		
		return skillContainerCapability;
	}
}
