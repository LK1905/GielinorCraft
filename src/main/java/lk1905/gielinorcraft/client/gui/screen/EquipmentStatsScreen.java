package lk1905.gielinorcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.capability.stat.IStats;
import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class EquipmentStatsScreen extends Screen{

	private PlayerEntity player = Minecraft.getInstance().player;
	private IStats stats = player.getCapability(StatCapability.STAT_CAP).orElse(null);
	
	public EquipmentStatsScreen() {
		super(new StringTextComponent("Equipment Stats"));
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		this.blit(stack, width, height, 0, 0, width, height);
		
		drawCenteredString(stack, font, "Equipment Stats", width / 2, height - 200, 111111);
		
		drawString(stack, font, "Accuracy bonuses", (width / 2) - 100, height - 180, 111111);
		drawString(stack, font, "Stab: " + stats.getTotalAccuracy(0), (width / 2) - 100, height - 170, 111111);
		drawString(stack, font, "Slash: " + stats.getTotalAccuracy(1), (width / 2) - 100, height - 160, 111111);
		drawString(stack, font, "Crush: " + stats.getTotalAccuracy(2), (width / 2) - 100, height - 150, 111111);
		drawString(stack, font, "Ranged: " + stats.getTotalAccuracy(3), (width / 2) - 100, height - 140, 111111);
		drawString(stack, font, "Magic: " + stats.getTotalAccuracy(4), (width / 2) - 100, height - 130, 111111);
		drawString(stack, font, "Strength bonuses", (width / 2) - 100, height - 120, 111111);
		drawString(stack, font, "Melee: " + stats.getTotalMeleeStrength(), (width / 2) - 100, height - 110, 111111);
		drawString(stack, font, "Ranged: " + stats.getTotalRangedStrength(), (width / 2) - 100, height - 100, 111111);
		drawString(stack, font, "Magic: " + stats.getTotalMagicStrength() + "%", (width / 2) - 100, height - 90, 111111);

		drawString(stack, font, "Defence bonuses", (width / 2) + 20, height - 180, 111111);
		drawString(stack, font, "Stab: " + stats.getTotalDefence(0), (width / 2) + 20, height - 170, 111111);
		drawString(stack, font, "Slash: " + stats.getTotalDefence(1), (width / 2) + 20, height - 160, 111111);
		drawString(stack, font, "Crush: " + stats.getTotalDefence(2), (width / 2) + 20, height - 150, 111111);
		drawString(stack, font, "Ranged: " + stats.getTotalDefence(3), (width / 2) + 20, height - 140, 111111);
		drawString(stack, font, "Magic: " + stats.getTotalDefence(4), (width / 2) + 20, height - 130, 111111);
		drawString(stack, font, "Other bonuses", (width / 2) + 20, height - 120, 111111);
		
		super.render(stack, mouseX, mouseY, partialTicks);
	}
}
