package lk1905.gielinorcraft.client.gui.screen.container;

import lk1905.gielinorcraft.inventory.container.AlloyFurnaceMenu;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlloyFurnaceScreen extends AbstractAlloyFurnaceScreen<AlloyFurnaceMenu>{

	private static final ResourceLocation TEXTURE = new ResourceLocation("gielinorcraft:textures/gui/container/furnace.png");
	
	public AlloyFurnaceScreen(AlloyFurnaceMenu screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, new SmeltingRecipeBookComponent(), inv, titleIn, TEXTURE);
	}
}