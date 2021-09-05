package lk1905.gielinorcraft.client.gui.widget;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleClientPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AttackStyleButton extends ImageButton{
	
	private static int yTex(int slotId) {	
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player =  mc.player;
		IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		
		if(styleCap.getActiveSlot() == slotId) {
			return 23;
		}else {
			return 0;
		}
	}

	public AttackStyleButton(int xIn, int yIn, int slotId) {	
		super(xIn, yIn, 56, 20, 137, yTex(slotId), 0 , new ResourceLocation(Gielinorcraft.MODID, "textures/gui/combat.png"), (button) -> {
			PacketHandler.sendToServer(new AttackStyleClientPacket(slotId));
		});
	}
}
