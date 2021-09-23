package lk1905.gielinorcraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class GcIngameGui extends AbstractGui{

	private Minecraft mc = Minecraft.getInstance();
	private PlayerEntity player = mc.player;
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
	
	private LivingEntity entity = (LivingEntity) mc.pointedEntity;
	
	public GcIngameGui(Minecraft mc, MatrixStack stack) {
		drawString(stack, mc.fontRenderer, styleCap.getStyleType(styleCap.getActiveSlot()), 250, 195, 111111);
		
		if(entity != null) {
			ISkills entityStats = entity.getCapability(SkillCapability.SKILL_CAP).orElse(null);
			drawString(stack, mc.fontRenderer, entity.getName().getString() + " (level " + entityStats.getCombatLevel() + ")", 10, 10, 111111);
			drawString(stack, mc.fontRenderer, (int) entity.getHealth() + " / " + (int) entity.getMaxHealth(), 10, 20, 111111);
		}
	}
}
