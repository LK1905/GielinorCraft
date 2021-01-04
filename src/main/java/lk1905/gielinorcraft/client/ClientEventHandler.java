package lk1905.gielinorcraft.client;

import org.lwjgl.glfw.GLFW;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.client.gui.screen.SkillsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler {

	public static Minecraft mc;
	public static KeyBinding[] keyBindings;
	
	public ClientEventHandler() {
		mc = Minecraft.getInstance();
		keyBindings = new KeyBinding[1];
		keyBindings[0] = new KeyBinding("Toggle skills screen", GLFW.GLFW_KEY_Y, "Gielinorcraft - Menus");
		
		for(int i = 0; i < keyBindings.length; i++) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEventKeyInput(KeyInputEvent event) {
		
		if(keyBindings[0].isPressed() && mc.currentScreen == null) {
			mc.displayGuiScreen(new SkillsScreen());
		}
	}
}
