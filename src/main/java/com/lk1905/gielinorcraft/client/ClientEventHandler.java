package com.lk1905.gielinorcraft.client;

import org.lwjgl.glfw.GLFW;

import com.lk1905.gielinorcraft.client.gui.ScreenSkills;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientEventHandler {

	private static final String menuString = "Gielinorcraft - Menus";
	
	private static final KeyBinding toggleScreenSkills = new KeyBinding("Toggle Skills screen", GLFW.GLFW_KEY_Y, menuString);
	
	public ClientEventHandler() {
		
		ClientRegistry.registerKeyBinding(toggleScreenSkills);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onKeyInput(KeyInputEvent e) {	
		
		if(toggleScreenSkills.isPressed() && Minecraft.getInstance().currentScreen == null) {
			
			Minecraft.getInstance().displayGuiScreen(new ScreenSkills());
		}
	}
}
