package lk1905.gielinorcraft.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyle;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.ISkills;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.client.gui.widget.AttackStyleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class AttackStyleScreen extends Screen{

	private Minecraft mc = Minecraft.getInstance();
	private static final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/combat.png");
	private AttackStyleButton[] styleButton;
	private String[] styleName;
	
	private Player player = mc.player;
	private ISkills skillCap = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
	
	private final int xSize = 134;
	private final int ySize = 163;
	
	private int guiLeft;
	private int guiTop;
	
	public AttackStyleScreen() {
		super(new TextComponent("Combat styles"));
		styleButton = new AttackStyleButton[6];
		styleName = new String[6];
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public void init() {
		guiLeft = (width - xSize) / 2;
		guiTop = (height - ySize) / 2;
		for(int i = 0; i < 6; i++) {
			if(styleCap.getAttackStyle(i) != AttackStyle.EMPTY) {
				styleButton[0] = new AttackStyleButton((width / 2) - 57, height / 2 - 45, 0);
				styleButton[1] = new AttackStyleButton((width / 2) + 1, height / 2 - 45, 1);
				styleButton[2] = new AttackStyleButton((width / 2) - 57, height / 2 - 18, 2);
				styleButton[3] = new AttackStyleButton((width / 2) + 1, height / 2 - 18, 3);
				styleButton[4] = new AttackStyleButton((width / 2) - 57, height / 2 + 9, 4);
				styleButton[5] = new AttackStyleButton((width / 2) + 1, height / 2 + 9, 5);
				this.addWidget(styleButton[i]);
				styleName[i] = styleCap.getStyleName(i);
			}else {
				styleButton[i] = null;
				styleName[i] = null;
			}
		}
	}
	
	@Override
	public void renderBackground(final PoseStack stack) {
		super.renderBackground(stack);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	public void render(final PoseStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		stack.pushPose();

		int colour = 111111;
		String wieldedItem = player.getMainHandItem().getItem().getName(player.getMainHandItem()).getString();
		
		drawCenteredString(stack, font, wieldedItem, width / 2, (height / 2) - 70, colour);
		drawCenteredString(stack, font, "Combat level: " + skillCap.getCombatLevel(), width / 2, (height / 2) - 60, colour);

		for(int i = 0; i < 6; i++) {
			if(styleButton[i] != null) {
				styleButton[i].renderButton(stack, mouseX, mouseY, partialTicks);
				
				if(styleButton[i].isHoveredOrFocused()) {
					this.renderTooltip(stack, new TextComponent(styleCap.getStyleDescription(i)), mouseX, mouseY);
					this.clearWidgets();
					this.init();
				}
			}
			if(styleName[i] != null) {
				drawCenteredString(stack, font, styleName[0], width / 2 - 30, height / 2 - 40, 0xffffff);
				drawCenteredString(stack, font, styleName[1], width / 2 + 29, height / 2 - 40, 0xffffff);
				drawCenteredString(stack, font, styleName[2], width / 2 - 30, height / 2 - 13, 0xffffff);
				drawCenteredString(stack, font, styleName[3], width / 2 + 29, height / 2 - 13, 0xffffff);
				drawCenteredString(stack, font, styleName[4], width / 2 - 30, height / 2 + 14, 0xffffff);
				drawCenteredString(stack, font, styleName[5], width / 2 + 29, height / 2 + 14, 0xffffff);
			}
		}
		stack.popPose();
	}
}
