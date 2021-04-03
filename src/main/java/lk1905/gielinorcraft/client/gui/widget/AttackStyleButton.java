package lk1905.gielinorcraft.client.gui.widget;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.network.AttackStyleClientPacket;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;

public class AttackStyleButton extends ImageButton{
	
	private PlayerEntity player =  Minecraft.getInstance().player;
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
	private static int yTex = 0;
	
	public AttackStyleButton(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn,
			int yDiffTextIn, ResourceLocation resourceLocationIn, IPressable onPressIn) {
		super(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, onPressIn);
	}

	public AttackStyleButton(int xIn, int yIn, int slotId) {	
		this(xIn, yIn, 56, 20, 137, yTex, 0 , new ResourceLocation(Gielinorcraft.MODID, "textures/gui/combat.png"), (button) -> {
			PacketHandler.sendToServer(new AttackStyleClientPacket(slotId));
		});

		if(styleCap.getActiveStyleId() == slotId) {
			yTex = 23;
		}else {
			yTex = 0;
		}
	}
}
