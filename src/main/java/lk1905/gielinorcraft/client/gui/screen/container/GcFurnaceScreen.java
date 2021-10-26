package lk1905.gielinorcraft.client.gui.screen.container;

import lk1905.gielinorcraft.inventory.container.GcFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GcFurnaceScreen extends GcAbstractFurnaceScreen<GcFurnaceContainer>{

	private static final ResourceLocation TEXTURE = new ResourceLocation("gielinorcraft:textures/gui/container/furnace.png");
	
	public GcFurnaceScreen(GcFurnaceContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn, TEXTURE);
	}
}