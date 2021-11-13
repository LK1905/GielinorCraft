package lk1905.gielinorcraft.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class GcIngameGui extends GuiComponent{

	private Minecraft mc = Minecraft.getInstance();
	private Player player = mc.player;
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
	private ISkills playerStats = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
	private int playerCombat = playerStats.getCombatLevel();
	
	private LivingEntity entity = (LivingEntity) mc.crosshairPickEntity;
	
	public GcIngameGui(Minecraft mc, PoseStack stack) {
		drawString(stack, mc.font, styleCap.getStyleType(styleCap.getActiveSlot()), 250, 195, 111111);
		
		if(entity != null) {
			ISkills entityStats = entity.getCapability(SkillCapability.SKILL_CAP).orElse(null);
			int entityCombat = entityStats.getCombatLevel();
			int levelColour;
			
			if(entityCombat - 10 >= playerCombat) {
				levelColour = 0xff0000;
			}else if(entityCombat - 9 == playerCombat || entityCombat - 8 == playerCombat || entityCombat - 7 == playerCombat) {
				levelColour = 0xff3000;
			}else if(entityCombat - 6 == playerCombat || entityCombat - 5 == playerCombat || entityCombat - 4 == playerCombat) {
				levelColour = 0xff7000;
			}else if(entityCombat - 3 == playerCombat || entityCombat - 2 == playerCombat || entityCombat - 1 == playerCombat) {
				levelColour = 0xffb000;
			}else if(entityCombat == playerCombat) {
				levelColour = 0xffff00;
			}else if(entityCombat + 1 == playerCombat || entityCombat + 2 == playerCombat || entityCombat + 3 == playerCombat) {
				levelColour = 0xc0ff00;
			}else if(entityCombat + 4 == playerCombat || entityCombat + 5 == playerCombat || entityCombat + 6 == playerCombat) {
				levelColour = 0x80ff00;
			}else if(entityCombat + 7 == playerCombat || entityCombat + 8 == playerCombat || entityCombat + 9 == playerCombat) {
				levelColour = 0x40ff00;
			}else {
				levelColour = 0x00ff00;
			}
			
			drawString(stack, mc.font, entity.getName().getString() + " (level " + entityCombat + ")", 5, 5, levelColour);
			drawString(stack, mc.font, (int) entity.getHealth() + " / " + (int) entity.getMaxHealth(), 5, 15, levelColour);
		}
	}
}
