package com.lk1905.gielinorcraft.client.gui;

import com.lk1905.gielinorcraft.api.skills.ISkill;
import com.lk1905.gielinorcraft.client.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ActiveSkillGui extends Screen{

	protected ActiveSkillGui() {
		super(new StringTextComponent(""));
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	public void onOverlayRender(RenderGameOverlayEvent.Post e) {
		
		if(e.getType() != RenderGameOverlayEvent.ElementType.ALL) {
			return;
		}
		
		Minecraft mc = Minecraft.getInstance();
		
		int xPos = (width / 2);
		int yPos = 20;
		
		ISkill activeSkill = ClientProxy.getActivelyTrainedSkill();
		
		if(activeSkill == null) {
			return;
		}
		
		String s = String.format("%s", "Skill: " + activeSkill.getName() + ", Level: " + activeSkill.getLevel() + ", XP: " + activeSkill.getXP());
		
		mc.fontRenderer.drawString(s, xPos, yPos, 111111);
	}
	
	public static void open() {
		
		Minecraft.getInstance().displayGuiScreen(new ActiveSkillGui());
	}
}
