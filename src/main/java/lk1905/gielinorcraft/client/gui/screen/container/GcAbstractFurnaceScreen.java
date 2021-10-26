package lk1905.gielinorcraft.client.gui.screen.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import lk1905.gielinorcraft.inventory.container.GcAbstractFurnaceContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class GcAbstractFurnaceScreen<T extends GcAbstractFurnaceContainer> extends ContainerScreen<T> {

	private final ResourceLocation guiTexture;

	public GcAbstractFurnaceScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation guiTextureIn) {
		super(screenContainer, inv, titleIn);
		this.guiTexture = guiTextureIn;
	}

	@Override
	public void init() {
		super.init();
		this.titleX = (this.xSize - this.font.getStringPropertyWidth(this.title)) / 2;
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		matrixStack.push();
		matrixStack.scale(1F, 1F, 1F);
		this.minecraft.getTextureManager().bindTexture(this.guiTexture);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
		if (this.container.isBurning()) {
			int k = this.container.getBurnLeftScaled();
			this.blit(matrixStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.container.getCookProgressionScaled();
		this.blit(matrixStack, i + 79, j + 34, 176, 14, l + 1, 16);
	}

	@Override
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
	}

	@Override
	public void onClose() {
		super.onClose();
	}
}
