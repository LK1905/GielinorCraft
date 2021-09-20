package lk1905.gielinorcraft.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;

public class GcIngameGui extends AbstractGui{

	private Minecraft mc = Minecraft.getInstance();
	private PlayerEntity player = mc.player;
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElseThrow(() -> new NullPointerException("Could not find capability."));
	
	public GcIngameGui(Minecraft mc, MatrixStack stack) {
		drawString(stack, mc.fontRenderer, styleCap.getStyleType(styleCap.getActiveSlot()), 250, 195, 111111);
	}
}
