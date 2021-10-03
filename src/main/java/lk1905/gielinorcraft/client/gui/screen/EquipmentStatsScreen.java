package lk1905.gielinorcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.stat.IStats;
import lk1905.gielinorcraft.capability.stat.StatCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class EquipmentStatsScreen extends Screen{

	private final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/stats.png");
	private PlayerEntity player = Minecraft.getInstance().player;
	private IStats stats = player.getCapability(StatCapability.STAT_CAP).orElse(null);
	
	private final int xSize = 140;
	private final int ySize = 163;
	
	private int guiLeft;
	private int guiTop;
	
	public EquipmentStatsScreen() {
		super(new StringTextComponent("Equipment Stats"));
	}
	
	@Override
	public void init() {
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		stack.push();
		stack.scale(1F, 1F, 1F);
		Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
		this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
		int colour = 111111;
		drawCenteredString(stack, font, "Equipment Stats", width / 2, (height / 2) - 70, colour);
		
		drawString(stack, font, "Accuracy", (width / 2) - 60, height - 180, colour);
		drawString(stack, font, "Stab: " + stats.getTotalAccuracy(0), (width / 2) - 60, height - 170, colour);
		drawString(stack, font, "Slash: " + stats.getTotalAccuracy(1), (width / 2) - 60, height - 160, colour);
		drawString(stack, font, "Crush: " + stats.getTotalAccuracy(2), (width / 2) - 60, height - 150, colour);
		drawString(stack, font, "Magic: " + stats.getTotalAccuracy(3), (width / 2) - 60, height - 140, colour);
		drawString(stack, font, "Range: " + stats.getTotalAccuracy(4), (width / 2) - 60, height - 130, colour);
		drawString(stack, font, "Strength", (width / 2) - 60, height - 110, colour);
		drawString(stack, font, "Melee: " + stats.getTotalMeleeStrength(), (width / 2) - 60, height - 100, colour);
		drawString(stack, font, "Ranged: " + stats.getTotalRangedStrength(), (width / 2) - 60, height - 90, colour);
		drawString(stack, font, "Magic: " + stats.getTotalMagicStrength() + "%", (width / 2) - 60, height - 80, colour);

		drawString(stack, font, "Defence", (width / 2) + 5, height - 180, colour);
		drawString(stack, font, "Stab: " + stats.getTotalDefence(0), (width / 2) + 5, height - 170, colour);
		drawString(stack, font, "Slash: " + stats.getTotalDefence(1), (width / 2) + 5, height - 160, colour);
		drawString(stack, font, "Crush: " + stats.getTotalDefence(2), (width / 2) + 5, height - 150, colour);
		drawString(stack, font, "Magic: " + stats.getTotalDefence(3), (width / 2) + 5, height - 140, colour);
		drawString(stack, font, "Range: " + stats.getTotalDefence(4), (width / 2) + 5, height - 130, colour);
		
		stack.pop();
	}
}
