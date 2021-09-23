package lk1905.gielinorcraft.event.handler;

import org.lwjgl.glfw.GLFW;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.client.gui.GcIngameGui;
import lk1905.gielinorcraft.client.gui.screen.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler {

	public static Minecraft mc = Minecraft.getInstance();
	public static KeyBinding[] keyBindings;
	
	public ClientEventHandler() {
		keyBindings = new KeyBinding[3];
		keyBindings[0] = new KeyBinding("Toggle Combat Styles screen", GLFW.GLFW_KEY_R, "Gielinorcraft - Menus");
		keyBindings[1] = new KeyBinding("Toggle skills screen", GLFW.GLFW_KEY_Y, "Gielinorcraft - Menus");
		keyBindings[2] = new KeyBinding("Toggle Equipment stats screen", GLFW.GLFW_KEY_U, "Gielinorcraft - Menus");
		
		for(int i = 0; i < keyBindings.length; i++) {
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onEventKeyInput(KeyInputEvent event) {
		
		if(keyBindings[0].isPressed() && mc.currentScreen == null) {
			mc.displayGuiScreen(new AttackStyleScreen());
		}
		
		if(keyBindings[1].isPressed() && mc.currentScreen == null) {
			mc.displayGuiScreen(new SkillsScreen());
		}
		
		if(keyBindings[2].isPressed() && mc.currentScreen == null) {
			mc.displayGuiScreen(new EquipmentStatsScreen());
		}
	}
	
	@SubscribeEvent
	public static void onRenderGui(RenderGameOverlayEvent.Post event) {
		if(event.getType().equals(ElementType.ALL)) {
			new GcIngameGui(mc, event.getMatrixStack());
		}
	}
	
	@SubscribeEvent
	public static void onLeftClick(ClickInputEvent event) {
		PlayerEntity player = mc.player;
		if(event.isAttack()) {
			if(player.getCooledAttackStrength(0) < 1) {
				event.setCanceled(true);
				event.setSwingHand(false);
			}
		}
	}
}
