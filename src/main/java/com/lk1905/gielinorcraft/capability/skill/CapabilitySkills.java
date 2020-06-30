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
import net.minecraftforge.common.util.LazyOptional;
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
		
		PlayerEntity player = e.getPlayer();
		
		if(player.world.isRemote) {
			return;
		}
		
		PlayerEntity old = e.getOriginal();
		old.revive();
		
		LazyOptional<ISkillContainer> oldCapability = CapabilityUtils.getCapability(old, skillContainerCapability, null);
		
		ISkillContainer oldSkills = oldCapability.orElse(null);
		
		if(oldSkills != null) {
			
			LazyOptional<ISkillContainer> newCapability = CapabilityUtils.getCapability(player, skillContainerCapability, null);
			
			ISkillContainer newSkills = newCapability.orElse(null);
			
			if(newSkills != null) {
				
				newSkills.setAllSkills(oldSkills.getAllSkills());
			}
		}
		
		/*ISkillContainer oldCapability = (ISkillContainer) CapabilityUtils.getCapability(e.getOriginal(), skillContainerCapability, null);
		ISkillContainer newCapability = (ISkillContainer) CapabilityUtils.getCapability(e.getPlayer(), skillContainerCapability, null);
		
		newCapability.setAllSkills(oldCapability.getAllSkills());*/
	}
	
	public static LazyOptional<ISkillContainer> getSkillContainer(ICapabilityProvider provider) {
		
		return CapabilityUtils.getCapability(provider, skillContainerCapability, null);
	}
	
	public static Capability<ISkillContainer> getSkillCapability(){
		
		return skillContainerCapability;
	}
}
