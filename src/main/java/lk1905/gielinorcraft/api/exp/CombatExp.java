package lk1905.gielinorcraft.api.exp;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CombatExp {

	@SubscribeEvent
	public static void entityHurt(LivingHurtEvent event) {
		if(!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
			return;
		}
		
		PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
		LazyOptional<ISkills> cap = player.getCapability(SkillCapability.SKILL_CAP);
		ISkills skills = cap.orElse(null);
		
		double xpGained = event.getAmount() * (1 + 1/3);
		
		skills.addXp(0, xpGained);
		skills.addXp(1, xpGained);
		skills.addXp(2, xpGained);
		skills.addXp(3, xpGained);
	}
}
