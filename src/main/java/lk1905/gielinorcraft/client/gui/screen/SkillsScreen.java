package lk1905.gielinorcraft.client.gui.screen;

import java.util.Locale;

import com.ibm.icu.text.NumberFormat;
import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.capability.skill.Skills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

/**A Simple gui containing all the player's skill data. Just text for now, will upgrade in the future.*/
public class SkillsScreen extends Screen{

	private final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/skills.png");
	
	private final int[] dynamicLevel = new int[26];
	private final int[] staticLevel = new int[26];
	private final ImageButton[] skillButton = new ImageButton[26];
	private ImageButton totalButton;
	
	private PlayerEntity player = Minecraft.getInstance().player;
	private ISkills skills = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
	
	private final int xSize = 159;
	private final int ySize = 206;
	private int guiLeft;
	private int guiTop;
	
	public SkillsScreen() {
		super(new StringTextComponent("Skills"));
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void init() {
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		
		
		
		skillButton[Skills.ATTACK] = new ImageButton(guiLeft + 10, guiTop + 10, 46, 20, 10, 10, 0, TEXTURE, null);
		skillButton[Skills.HITPOINTS] = new ImageButton(guiLeft + 57, guiTop + 10, 46, 20, 57, 10, 0, TEXTURE, null);
		skillButton[Skills.MINING] = new ImageButton(guiLeft + 104, guiTop + 10, 46, 20, 104, 10, 0, TEXTURE, null);
		skillButton[Skills.STRENGTH] = new ImageButton(guiLeft + 10, guiTop + 31, 46, 20, 10, 31, 0, TEXTURE, null);
		skillButton[Skills.AGILITY] = new ImageButton(guiLeft + 57, guiTop + 31, 46, 20, 57, 31, 0, TEXTURE, null);
		skillButton[Skills.SMITHING] = new ImageButton(guiLeft + 104, guiTop + 31, 46, 20, 104, 31, 0, TEXTURE, null);
		skillButton[Skills.DEFENCE] = new ImageButton(guiLeft + 10, guiTop + 52, 46, 20, 10, 52, 0, TEXTURE, null);
		skillButton[Skills.HERBLORE] = new ImageButton(guiLeft + 57, guiTop + 52, 46, 20, 57, 52, 0, TEXTURE, null);
		skillButton[Skills.FISHING] = new ImageButton(guiLeft + 104, guiTop + 52, 46, 20, 104, 52, 0, TEXTURE, null);
		skillButton[Skills.RANGED] = new ImageButton(guiLeft + 10, guiTop + 73, 46, 20, 10, 73, 0, TEXTURE, null);
		skillButton[Skills.THIEVING] = new ImageButton(guiLeft + 57, guiTop + 73, 46, 20, 57, 73, 0, TEXTURE, null);
		skillButton[Skills.COOKING] = new ImageButton(guiLeft + 104, guiTop + 73, 46, 20, 104, 73, 0, TEXTURE, null);
		skillButton[Skills.PRAYER] = new ImageButton(guiLeft + 10, guiTop + 94, 46, 20, 10, 94, 0, TEXTURE, null);
		skillButton[Skills.CRAFTING] = new ImageButton(guiLeft + 57, guiTop + 94, 46, 20, 57, 94, 0, TEXTURE, null);
		skillButton[Skills.FIREMAKING] = new ImageButton(guiLeft + 104, guiTop + 94, 46, 20, 104, 94, 0, TEXTURE, null);
		skillButton[Skills.MAGIC] = new ImageButton(guiLeft + 10, guiTop + 115, 46, 20, 10, 115, 0, TEXTURE, null);
		skillButton[Skills.FLETCHING] = new ImageButton(guiLeft + 57, guiTop + 115, 46, 20, 57, 115, 0, TEXTURE, null);
		skillButton[Skills.WOODCUTTING] = new ImageButton(guiLeft + 104, guiTop + 115, 46, 20, 104, 115, 0, TEXTURE, null);
		skillButton[Skills.RUNECRAFTING] = new ImageButton(guiLeft + 10, guiTop + 136, 46, 20, 10, 136, 0, TEXTURE, null);
		skillButton[Skills.SLAYER] = new ImageButton(guiLeft + 57, guiTop + 136, 46, 20, 57, 136, 0, TEXTURE, null);
		skillButton[Skills.FARMING] = new ImageButton(guiLeft + 104, guiTop + 136, 46, 20, 104, 136, 0, TEXTURE, null);
		skillButton[Skills.CARPENTRY] = new ImageButton(guiLeft + 10, guiTop + 157, 46, 20, 10, 157, 0, TEXTURE, null);
		skillButton[Skills.HUNTER] = new ImageButton(guiLeft + 57, guiTop + 157, 46, 20, 57, 157, 0, TEXTURE, null);
		skillButton[Skills.SUMMONING] = new ImageButton(guiLeft + 104, guiTop + 157, 46, 20, 104, 157, 0, TEXTURE, null);
		skillButton[Skills.STONECUTTING] = new ImageButton(guiLeft + 10, guiTop + 178, 46, 20, 10, 178, 0, TEXTURE, null);
		skillButton[Skills.DIGGING] = new ImageButton(guiLeft + 57, guiTop + 178, 46, 20, 57, 178, 0, TEXTURE, null);
		
		for(int i = 0; i < 26; i++) {
			dynamicLevel[i] = skills.getLevel(i);
			staticLevel[i] = skills.getStaticLevel(i);
			addButton(skillButton[i]);
		}
		
		totalButton = new ImageButton(guiLeft + 104, guiTop + 178, 46, 20, 104, 178, 0, TEXTURE, null);
		addButton(totalButton);
	}
	
	@Override
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		stack.push();
		stack.scale(1F, 1F, 1F);
		Minecraft.getInstance().getTextureManager().bindTexture(TEXTURE);
		this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
		
		for(int i = 0; i < 26; i++) {
			skillButton[i].renderButton(stack, mouseX, mouseY, partialTicks);
			
			if(skillButton[i].isHovered()) {
				//I have no idea how to make tooltips have  multiple lines.
				this.renderTooltip(stack, new StringTextComponent(skills.getName(i) + " xp: "
						+ NumberFormat.getInstance(Locale.US).format((int) skills.getXp(i)) + ", xp to next: "
						+ NumberFormat.getInstance(Locale.US).format((int) (skills.getXpByLevel(skills.getStaticLevel(i) + 1) - skills.getXp(i)))), mouseX, mouseY);
			}
		}
		
		if(totalButton.isHovered()) {
			this.renderTooltip(stack, new StringTextComponent("total xp: " + NumberFormat.getInstance(Locale.US).format((int) skills.getTotalXp())), mouseX, mouseY);
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
		
		stack.pop();
	}

}
