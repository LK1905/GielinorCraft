package com.lk1905.gielinorcraft.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lk1905.gielinorcraft.Gielinorcraft;
import com.lk1905.gielinorcraft.api.capability.ISkillContainer;
import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.api.skills.SkillIcon;
import com.lk1905.gielinorcraft.api.utils.Colour;
import com.lk1905.gielinorcraft.api.utils.Position;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class ScreenSkills extends Screen{

	private final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/skills.png");
	private List<String> skillNameIndices = new ArrayList<>();
	private ISkill activeSkillDisplay;
	
	protected int guiLeft;
	protected int guiTop;
	
	protected final int xSize = 134;
	protected final int ySize = 219;
	
	public enum ButtonIDs{
		SkillsButton
	}
	
	protected ScreenSkills() {
		
		super(new StringTextComponent("Skills"));
	}
	
	@Override
	public void init() {
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}
	
	public void setSize() {
		super.setSize(xSize, ySize);	
	}
	
	public void render(int mouseX, int mouseY, float partialTicks) {
		
		//GlStateManager.pushMatrix(); <---Outdated
		//GlStateManager.color(1F, 1F, 1F, 1F); <---Outdated
		
		this.minecraft.getTextureManager().bindTexture(TEXTURE);
		super.blit(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		Colour white = new Colour(255, 255, 255, 255);
		
		ISkillContainer skillCapability = Gielinorcraft.getSkillCapability();
		
		if(skillCapability == null) {
			
			super.drawCenteredString(this.minecraft.fontRenderer, "Loading Player stats",
									this.guiLeft + 87, this.guiTop + 130, white.getIntValue());
			
			super.drawCenteredString(this.minecraft.fontRenderer, "from server...",
					this.guiLeft + 87, this.guiTop + 141, white.getIntValue());
			
			//GlStateManager.popMatrix(); <---Outdated
			return;
		}
		
		int skillIndex = 1;
		skillNameIndices = new ArrayList<>();
		
		for(String skillName : skillCapability.getAllSkillXP().keySet()) {
			
			skillNameIndices.add(skillName);
			ISkill skill = skillCapability.getSkill(skillName);
			
			if(skill == activeSkillDisplay) {
				
				drawSkill(skillIndex++, skill, white);
				
				minecraft.fontRenderer.drawString(String.format("Skill: %d", activeSkillDisplay.getName()),
													this.guiLeft + 25, this.guiTop + 169, white.getIntValue());
				
				minecraft.fontRenderer.drawString(String.format("Level: %d", activeSkillDisplay.getLevel()),
													this.guiLeft + 25, this.guiTop + 180, white.getIntValue());
				
				minecraft.fontRenderer.drawString(String.format("XP: %d", activeSkillDisplay.getXP()),
													this.guiLeft + 25, this.guiTop + 191, white.getIntValue());
				
				minecraft.fontRenderer.drawString(String.format("XP to next level: %d", activeSkillDisplay.xpToNextLevel()),
													this.guiLeft + 25, this.guiTop + 202, white.getIntValue());
			}else {
				
				minecraft.fontRenderer.drawString(String.format("Total level: %d", skillCapability.getAllSkills()),
													this.guiLeft + 25, this.guiTop + 180, white.getIntValue());
				
				minecraft.fontRenderer.drawString(String.format("Total XP: %d", skillCapability.getAllSkillXP()),
													this.guiLeft + 25, this.guiTop + 191, white.getIntValue());
			}
		}
		
		//GlStateManager.popMatrix(); <---Outdated
	}
	
	private void drawSkill(int skillIndex, ISkill skill, Colour colour) {
		
		Position levelPosition = getLevelPosition(skillIndex);
		Position iconPosition = getIconPosition(skillIndex);
		SkillIcon icon = skill.getSkillIcon();
		
		//GlStateManager.pushMatrix(); <---Outdated
		//GlStateManager.color(1F, 1F, 1F, 1F); <---Outdated
		
		drawSkillIcon(icon, iconPosition);
		
		minecraft.fontRenderer.drawString(String.valueOf(skill.getLevel()), levelPosition.getX(), levelPosition.getY(), colour.getIntValue());
		minecraft.fontRenderer.drawString(String.valueOf(skill.getLevel()), levelPosition.getX() + 20,
											levelPosition.getY(), colour.getIntValue());
		
		//GlStateManager.popMatrix(); <---Outdated
	}
	
	private Position getIconPosition(int skillIndex) {
		
		Position position = getLevelPosition(skillIndex);
		position.setX(position.getX() - 20);
		position.setY(position.getY() - 2);
		return position;
	}
	
	private Position getLevelPosition(int skillIndex) {
		
		int row = (skillIndex / 3) + 1;
		int column = skillIndex % 3;
		int left = (column - 1) * 53;
		int right = column * 53;
		int columnOffset = (int) ((right - left) / 2F) + left;
		
		float x = this.guiLeft + 2 + columnOffset;
		float y = this.guiTop + 17 + (3F * row);
		
		return new Position(x, y);
	}
	
	private void drawSkillIcon(SkillIcon icon, Position location) {
		
		minecraft.getTextureManager().bindTexture(icon.getTextureLocation());
		
		blit((int) location.getX(), (int) location.getY(), 0, 0, icon.getTexWidth(), icon.getTexHeight(),
				12, 12, icon.getTexWidth(), icon.getTexHeight());
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
		
		if(mouseButton != 0) {
			return;
		}
		
		if(mouseX > this.guiLeft + 7 && mouseX <  this.guiLeft + 127 && mouseY > this.guiTop + 7 && mouseY < this.guiTop + 212) {
			
			int row = (int) ((mouseY - this.guiTop - 7) / 14F);
			int column = (int) ((mouseX - this.guiLeft - 7) / 53F);
			int skillIndex = (row * 3) + column;
			
			ISkillContainer skillCapability = Gielinorcraft.getSkillCapability();
			
			if(skillIndex > skillNameIndices.size() - 1) {
				
				Gielinorcraft.logger.warn(String.format("No skill definition exists for skills page index %d", skillIndex));
				return;
				
			}else if(skillIndex < 0){
				
				Gielinorcraft.logger.warn(String.format("The skill index calculation returned a negative value"
														+ "for mouse coordinates: %d %d - value of %d", mouseX, mouseY, skillIndex));
				return;
			}
			
			String skillName = skillNameIndices.get(skillIndex);
			activeSkillDisplay = skillCapability.getSkill(skillName);
			
		}else {
			super.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}
	
	public static void open() {
		
		Minecraft.getInstance().displayGuiScreen(new ScreenSkills());
	}
}
