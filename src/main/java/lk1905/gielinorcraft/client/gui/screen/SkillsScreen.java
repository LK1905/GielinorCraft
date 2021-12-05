package lk1905.gielinorcraft.client.gui.screen;

import java.util.Locale;

import com.ibm.icu.text.NumberFormat;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.skill.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

/**A Simple gui containing all the player's skill data. Just text for now, will upgrade in the future.*/
public class SkillsScreen extends Screen{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/skills.png");
	
	private final int[] dynamicLevel = new int[26];
	private final int[] staticLevel = new int[26];
	private final Button[] skillButton = new Button[26];
	private Button totalButton;
	
	private Player player = Minecraft.getInstance().player;
	private ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
	
	private final int xSize = 159;
	private final int ySize = 206;
	private int guiLeft;
	private int guiTop;
	
	public SkillsScreen() {
		super(new TextComponent("Skills"));
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void init() {
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
				
		skillButton[Skills.ATTACK] = new Button(guiLeft + 10, guiTop + 10, 46, 20, null, (button) -> {});
		skillButton[Skills.HITPOINTS] = new Button(guiLeft + 57, guiTop + 10, 46, 20, null, (button) -> {});
		skillButton[Skills.MINING] = new Button(guiLeft + 104, guiTop + 10, 46, 20, null, (button) -> {});
		skillButton[Skills.STRENGTH] = new Button(guiLeft + 10, guiTop + 31, 46, 20, null, (button) -> {});
		skillButton[Skills.AGILITY] = new Button(guiLeft + 57, guiTop + 31, 46, 20, null, (button) -> {});
		skillButton[Skills.SMITHING] = new Button(guiLeft + 104, guiTop + 31, 46, 20, null, (button) -> {});
		skillButton[Skills.DEFENCE] = new Button(guiLeft + 10, guiTop + 52, 46, 20, null, (button) -> {});
		skillButton[Skills.HERBLORE] = new Button(guiLeft + 57, guiTop + 52, 46, 20, null, (button) -> {});
		skillButton[Skills.FISHING] = new Button(guiLeft + 104, guiTop + 52, 46, 20, null, (button) -> {});
		skillButton[Skills.RANGED] = new Button(guiLeft + 10, guiTop + 73, 46, 20, null, (button) -> {});
		skillButton[Skills.THIEVING] = new Button(guiLeft + 57, guiTop + 73, 46, 20, null, (button) -> {});
		skillButton[Skills.COOKING] = new Button(guiLeft + 104, guiTop + 73, 46, 20, null, (button) -> {});
		skillButton[Skills.PRAYER] = new Button(guiLeft + 10, guiTop + 94, 46, 20, null, (button) -> {});
		skillButton[Skills.CRAFTING] = new Button(guiLeft + 57, guiTop + 94, 46, 20, null, (button) -> {});
		skillButton[Skills.FIREMAKING] = new Button(guiLeft + 104, guiTop + 94, 46, 20, null, (button) -> {});
		skillButton[Skills.MAGIC] = new Button(guiLeft + 10, guiTop + 115, 46, 20, null, (button) -> {});
		skillButton[Skills.FLETCHING] = new Button(guiLeft + 57, guiTop + 115, 46, 20, null, (button) -> {});
		skillButton[Skills.WOODCUTTING] = new Button(guiLeft + 104, guiTop + 115, 46, 20, null, (button) -> {});
		skillButton[Skills.RUNECRAFTING] = new Button(guiLeft + 10, guiTop + 136, 46, 20, null, (button) -> {});
		skillButton[Skills.SLAYER] = new Button(guiLeft + 57, guiTop + 136, 46, 20, null, (button) -> {});
		skillButton[Skills.FARMING] = new Button(guiLeft + 104, guiTop + 136, 46, 20, null, (button) -> {});
		skillButton[Skills.CARPENTRY] = new Button(guiLeft + 10, guiTop + 157, 46, 20, null, (button) -> {});
		skillButton[Skills.HUNTER] = new Button(guiLeft + 57, guiTop + 157, 46, 20, null, (button) -> {});
		skillButton[Skills.SUMMONING] = new Button(guiLeft + 104, guiTop + 157, 46, 20, null, (button) -> {});
		skillButton[Skills.STONECUTTING] = new Button(guiLeft + 10, guiTop + 178, 46, 20, null, (button) -> {});
		skillButton[Skills.DIGGING] = new Button(guiLeft + 57, guiTop + 178, 46, 20, null, (button) -> {});
		
		for(int i = 0; i < 26; i++) {
			dynamicLevel[i] = skills.getLevel(i);
			staticLevel[i] = skills.getStaticLevel(i);
			addWidget(skillButton[i]);
		}
		
		totalButton = new Button(guiLeft + 104, guiTop + 178, 46, 20, null, (button) -> {});
		addWidget(totalButton);
	}
	
	@Override
	public void renderBackground(final PoseStack stack) {
		super.renderBackground(stack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	public void render(final PoseStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		stack.pushPose();		
		
		for(int i = 0; i < 26; i++) {
			if(skillButton[i].isHoveredOrFocused()) {
				//I have no idea how to make tooltips have  multiple lines.
				this.renderTooltip(stack, new TextComponent(skills.getName(i) + " xp: "
						+ NumberFormat.getInstance(Locale.US).format((int) skills.getXp(i)) + ", xp to next: "
						+ NumberFormat.getInstance(Locale.US).format((int) (skills.getXpByLevel(skills.getStaticLevel(i) + 1) - skills.getXp(i)))), mouseX, mouseY);
			}
		}
		
		if(totalButton.isHoveredOrFocused()) {
			this.renderTooltip(stack, new TextComponent("total xp: " + NumberFormat.getInstance(Locale.US).format((int) skills.getTotalXp())), mouseX, mouseY);
		}
		
		int colour = 0xffff00;
		
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.ATTACK], guiLeft + 34, guiTop + 11, colour);
		drawCenteredString(stack, font, "" + (int) player.getHealth(), guiLeft + 81, guiTop + 11, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.MINING], guiLeft + 128, guiTop + 11, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.STRENGTH], guiLeft + 34, guiTop + 32, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.AGILITY], guiLeft + 81, guiTop + 32, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.SMITHING], guiLeft + 128, guiTop + 32, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.DEFENCE], guiLeft + 34, guiTop + 53, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.HERBLORE], guiLeft + 81, guiTop + 53, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.FISHING], guiLeft + 128, guiTop + 53, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.RANGED], guiLeft + 34, guiTop + 74, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.THIEVING], guiLeft + 81, guiTop + 74, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.COOKING], guiLeft + 128, guiTop + 74, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.PRAYER], guiLeft + 34, guiTop + 95, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.CRAFTING], guiLeft + 81, guiTop + 95, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.FIREMAKING], guiLeft + 128, guiTop + 95, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.MAGIC], guiLeft + 34, guiTop + 116, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.FLETCHING], guiLeft + 81, guiTop + 116, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.WOODCUTTING], guiLeft + 128, guiTop + 116, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.RUNECRAFTING], guiLeft + 34, guiTop + 137, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.SLAYER], guiLeft + 81, guiTop + 137, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.FARMING], guiLeft + 128, guiTop + 137, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.CARPENTRY], guiLeft + 34, guiTop + 158, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.HUNTER], guiLeft + 81, guiTop + 158, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.SUMMONING], guiLeft + 128, guiTop + 158, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.STONECUTTING], guiLeft + 34, guiTop + 179, colour);
		drawCenteredString(stack, font, "" + dynamicLevel[Skills.DIGGING], guiLeft + 81, guiTop + 179, colour);
		
		drawCenteredString(stack, font, "" + staticLevel[Skills.ATTACK], guiLeft + 46, guiTop + 19, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.HITPOINTS], guiLeft + 93, guiTop + 19, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.MINING], guiLeft + 140, guiTop + 19, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.STRENGTH], guiLeft + 46, guiTop + 40, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.AGILITY], guiLeft + 93, guiTop + 40, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.SMITHING], guiLeft + 140, guiTop + 40, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.DEFENCE], guiLeft + 46, guiTop + 61, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.HERBLORE], guiLeft + 93, guiTop + 61, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.FISHING], guiLeft + 140, guiTop + 61, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.RANGED], guiLeft + 46, guiTop + 82, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.THIEVING], guiLeft + 93, guiTop + 82, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.COOKING], guiLeft + 140, guiTop + 82, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.PRAYER], guiLeft + 46, guiTop + 103, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.CRAFTING], guiLeft + 93, guiTop + 103, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.FIREMAKING], guiLeft + 140, guiTop + 103, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.MAGIC], guiLeft + 46, guiTop + 124, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.FLETCHING], guiLeft + 93, guiTop + 124, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.WOODCUTTING], guiLeft + 140, guiTop + 124, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.RUNECRAFTING], guiLeft + 46, guiTop + 145, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.SLAYER], guiLeft + 93, guiTop + 145, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.FARMING], guiLeft + 140, guiTop + 145, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.CARPENTRY], guiLeft + 46, guiTop + 166, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.HUNTER], guiLeft + 93, guiTop + 166, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.SUMMONING], guiLeft + 140, guiTop + 166, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.STONECUTTING], guiLeft + 46, guiTop + 187, colour);
		drawCenteredString(stack, font, "" + staticLevel[Skills.DIGGING], guiLeft + 93, guiTop + 187, colour);
		
		drawCenteredString(stack, font, "Total lvl:", guiLeft + 126, guiTop + 179, colour);
		drawCenteredString(stack, font, "" + skills.getTotalLevel(), guiLeft + 126, guiTop + 188, colour);
		
		stack.popPose();
	}

}
