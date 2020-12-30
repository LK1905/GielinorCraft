package lk1905.gielinorcraft.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.client.ClientEventHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class SkillsScreen extends Screen{

	private String[] skill_info = new String[26];
	private LazyOptional<ISkills> cap = ClientEventHandler.mc.player.getCapability(SkillCapability.SKILL_CAP);
	private ISkills skills = cap.orElse(null);
	
	public SkillsScreen() {
		super(new StringTextComponent("Skills"));
	}
	
	@Override
	public void init() {
		super.init();
		
		//Theres probrably an easier way of doing this.
		skill_info[0] = "Attack, Level: " + skills.getLevel(0) + "/" + skills.getStaticLevel(0) + ", xp: " + (int) Math.floor(skills.getXp(0));
		skill_info[1] = "Defence, Level: " + skills.getLevel(1) + "/" + skills.getStaticLevel(1) + ", xp: " + (int) Math.floor(skills.getXp(1));
		skill_info[2] = "Strength, Level: " + skills.getLevel(2) + "/" + skills.getStaticLevel(2) + ", xp: " + (int) Math.floor(skills.getXp(2));
		skill_info[3] = "Hitpoints, Level: " + skills.getLevel(3) + "/" + skills.getStaticLevel(3) + ", xp: " + (int) Math.floor(skills.getXp(3));
		skill_info[4] = "Ranged, Level: " + skills.getLevel(4) + "/" + skills.getStaticLevel(4) + ", xp: " + (int) Math.floor(skills.getXp(4));
		skill_info[5] = "Prayer, Level: " + skills.getLevel(5) + "/" + skills.getStaticLevel(5) + ", xp: " + (int) Math.floor(skills.getXp(5));
		skill_info[6] = "Magic, Level: " + skills.getLevel(6) + "/" + skills.getStaticLevel(6) + ", xp: " + (int) Math.floor(skills.getXp(6));
		skill_info[7] = "Cooking, Level: " + skills.getLevel(7) + "/" + skills.getStaticLevel(7) + ", xp: " + (int) Math.floor(skills.getXp(7));
		skill_info[8] = "Woodcutting, Level: " + skills.getLevel(8) + "/" + skills.getStaticLevel(8) + ", xp: " + (int) Math.floor(skills.getXp(8));
		skill_info[9] = "Fletching, Level: " + skills.getLevel(9) + "/" + skills.getStaticLevel(9) + ", xp: " + (int) Math.floor(skills.getXp(9));
		skill_info[10] = "Fishing, Level: " + skills.getLevel(10) + "/" + skills.getStaticLevel(10) + ", xp: " + (int) Math.floor(skills.getXp(10));
		skill_info[11] = "Firemaking, Level: " + skills.getLevel(11) + "/" + skills.getStaticLevel(11) + ", xp: " + (int) Math.floor(skills.getXp(11));
		skill_info[12] = "Crafting, Level: " + skills.getLevel(12) + "/" + skills.getStaticLevel(12) + ", xp: " + (int) Math.floor(skills.getXp(12));
		skill_info[13] = "Smithing, Level: " + skills.getLevel(13) + "/" + skills.getStaticLevel(13) + ", xp: " + (int) Math.floor(skills.getXp(13));
		skill_info[14] = "Mining, Level: " + skills.getLevel(14) + "/" + skills.getStaticLevel(14) + ", xp: " + (int) Math.floor(skills.getXp(14));
		skill_info[15] = "Herblore, Level: " + skills.getLevel(15) + "/" + skills.getStaticLevel(15) + ", xp: " + (int) Math.floor(skills.getXp(15));
		skill_info[16] = "Agility, Level: " + skills.getLevel(16) + "/" + skills.getStaticLevel(16) + ", xp: " + (int) Math.floor(skills.getXp(16));
		skill_info[17] = "Thieving, Level: " + skills.getLevel(17) + "/" + skills.getStaticLevel(17) + ", xp: " + (int) Math.floor(skills.getXp(17));
		skill_info[18] = "Slayer, Level: " + skills.getLevel(18) + "/" + skills.getStaticLevel(18) + ", xp: " + (int) Math.floor(skills.getXp(18));
		skill_info[19] = "Farming, Level: " + skills.getLevel(19) + "/" + skills.getStaticLevel(19) + ", xp: " + (int) Math.floor(skills.getXp(19));
		skill_info[20] = "Runecrafting, Level: " + skills.getLevel(20) + "/" + skills.getStaticLevel(20) + ", xp: " + (int) Math.floor(skills.getXp(20));
		skill_info[21] = "Hunter, Level: " + skills.getLevel(21) + "/" + skills.getStaticLevel(21) + ", xp: " + (int) Math.floor(skills.getXp(21));
		skill_info[22] = "Carpentry, Level: " + skills.getLevel(22) + "/" + skills.getStaticLevel(22) + ", xp: " + (int) Math.floor(skills.getXp(22));
		skill_info[23] = "Summoning, Level: " + skills.getLevel(23) + "/" + skills.getStaticLevel(23) + ", xp: " + (int) Math.floor(skills.getXp(23));
		skill_info[24] = "Digging, Level: " + skills.getLevel(24) + "/" + skills.getStaticLevel(24) + ", xp: " + (int) Math.floor(skills.getXp(24));
		skill_info[25] = "Stonecutting, Level: " + skills.getLevel(25) + "/" + skills.getStaticLevel(25) + ", xp: " + (int) Math.floor(skills.getXp(25));
	}
	
	@Override
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		this.blit(stack, width, height, 0, 0, width, height);
		
		drawCenteredString(stack, font, "Skills", width / 2, height - 200, 111111);
		drawCenteredString(stack, font, "Total level: " + skills.getTotalLevel() + ", Total xp: " +  (int) Math.floor(skills.getTotalXp()), width / 2, height - 190, 111111);
		
		//Again, theres probrably an easier way of doing this.
		drawString(stack, font, skill_info[0], (width / 2) - 200, height - 180, 111111);
		drawString(stack, font, skill_info[1], (width / 2) - 200, height - 170, 111111);
		drawString(stack, font, skill_info[2], (width / 2) - 200, height - 160, 111111);
		drawString(stack, font, skill_info[3], (width / 2) - 200, height - 150, 111111);
		drawString(stack, font, skill_info[4], (width / 2) - 200, height - 140, 111111);
		drawString(stack, font, skill_info[5], (width / 2) - 200, height - 130, 111111);
		drawString(stack, font, skill_info[6], (width / 2) - 200, height - 120, 111111);
		drawString(stack, font, skill_info[7], (width / 2) - 200, height - 110, 111111);
		drawString(stack, font, skill_info[8], (width / 2) - 200, height - 100, 111111);
		drawString(stack, font, skill_info[9], (width / 2) - 200, height - 90, 111111);
		drawString(stack, font, skill_info[10], (width / 2) - 200, height - 80, 111111);
		drawString(stack, font, skill_info[11], (width / 2) - 200, height - 70, 111111);
		drawString(stack, font, skill_info[12], (width / 2) - 200, height - 60, 111111);
		drawString(stack, font, skill_info[13], (width / 2) + 20, height - 180, 111111);
		drawString(stack, font, skill_info[14], (width / 2) + 20, height - 170, 111111);
		drawString(stack, font, skill_info[15], (width / 2) + 20, height - 160, 111111);
		drawString(stack, font, skill_info[16], (width / 2) + 20, height - 150, 111111);
		drawString(stack, font, skill_info[17], (width / 2) + 20, height - 140, 111111);
		drawString(stack, font, skill_info[18], (width / 2) + 20, height - 130, 111111);
		drawString(stack, font, skill_info[19], (width / 2) + 20, height - 120, 111111);
		drawString(stack, font, skill_info[20], (width / 2) + 20, height - 110, 111111);
		drawString(stack, font, skill_info[21], (width / 2) + 20, height - 100, 111111);
		drawString(stack, font, skill_info[22], (width / 2) + 20, height - 90, 111111);
		drawString(stack, font, skill_info[23], (width / 2) + 20, height - 80, 111111);
		drawString(stack, font, skill_info[24], (width / 2) + 20, height - 70, 111111);
		drawString(stack, font, skill_info[25], (width / 2) + 20, height - 60, 111111);
		
		super.render(stack, mouseX, mouseY, partialTicks);
	}

}
