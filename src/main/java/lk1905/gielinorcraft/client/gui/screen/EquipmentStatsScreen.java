package lk1905.gielinorcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.api.stats.IStats;
import lk1905.gielinorcraft.capability.stats.StatCapability;
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
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		this.blit(stack, width, height, 0, 0, width, height);
		
		drawCenteredString(stack, font, "Equipment Stats", width / 2, height - 200, 111111);
		
		drawString(stack, font, "Accuracy bonuses", (width / 2) - 100, height - 180, 111111);
		drawString(stack, font, "Stab: " + stats.getStabAccuracy(), (width / 2) - 100, height - 170, 111111);
		drawString(stack, font, "Slash: " + stats.getSlashAccuracy(), (width / 2) - 100, height - 160, 111111);
		drawString(stack, font, "Crush: " + stats.getCrushAccuracy(), (width / 2) - 100, height - 150, 111111);
		drawString(stack, font, "Ranged: " + stats.getRangedAccuracy(), (width / 2) - 100, height - 140, 111111);
		drawString(stack, font, "Magic: " + stats.getMagicAccuracy(), (width / 2) - 100, height - 130, 111111);
		drawString(stack, font, "Strength bonuses", (width / 2) - 100, height - 120, 111111);
		drawString(stack, font, "Melee: " + stats.getMeleeStrength(), (width / 2) - 100, height - 110, 111111);
		drawString(stack, font, "Ranged: " + stats.getRangedStrength(), (width / 2) - 100, height - 100, 111111);
		drawString(stack, font, "Magic: " + stats.getMagicStrength() + "%", (width / 2) - 100, height - 90, 111111);

		drawString(stack, font, "Defence bonuses", (width / 2) + 20, height - 180, 111111);
		drawString(stack, font, "Stab: " + stats.getStabDefence(), (width / 2) + 20, height - 170, 111111);
		drawString(stack, font, "Slash: " + stats.getSlashDefence(), (width / 2) + 20, height - 160, 111111);
		drawString(stack, font, "Crush: " + stats.getCrushDefence(), (width / 2) + 20, height - 150, 111111);
		drawString(stack, font, "Ranged: " + stats.getRangedDefence(), (width / 2) + 20, height - 140, 111111);
		drawString(stack, font, "Magic: " + stats.getMagicDefence(), (width / 2) + 20, height - 130, 111111);
		drawString(stack, font, "Other bonuses", (width / 2) + 20, height - 120, 111111);
		drawString(stack, font, "Slayer: " + stats.getSlayerBonus() + "%", (width / 2) + 20, height - 110, 111111);
		drawString(stack, font, "Undead: " + stats.getUndeadBonus() + "%", (width / 2) + 20, height - 100, 111111);
		drawString(stack, font, "Demonic: " + stats.getDemonicBonus() + "%", (width / 2) + 20, height - 90, 111111);
		drawString(stack, font, "Draconic: " + stats.getDraconicBonus() + "%", (width / 2) + 20, height - 80, 111111);
		drawString(stack, font, "Other: " + stats.getOtherBonus() + "%", (width / 2) + 20, height - 70, 111111);
		
		super.render(stack, mouseX, mouseY, partialTicks);
	}
}
