package lk1905.gielinorcraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.skill.ISkills;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.skill.SkillCapability;
import lk1905.gielinorcraft.client.gui.widget.AttackStyleButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class AttackStyleScreen extends Screen{

	private Minecraft mc = Minecraft.getInstance();
	private final ResourceLocation TEXTURE = new ResourceLocation(Gielinorcraft.MODID, "textures/gui/combat.png");
	private AttackStyleButton[] styleButton;
	private String[] styleName;
	
	private PlayerEntity player = mc.player;
	private ISkills skillCap = player.getCapability(SkillCapability.SKILL_CAP).orElse(null);
	private IAttackStyle styleCap = player.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
	
	private final int xSize = 134;
	private final int ySize = 163;
	
	private int guiLeft;
	private int guiTop;
	
	public AttackStyleScreen() {
		super(new StringTextComponent("Combat styles"));
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
			if(styleCap.getAttackStyle(i) == AttackStyles.EMPTY) {
				styleButton[i] = null;
				styleName[i] = null;
			}else {
				styleButton[0] = new AttackStyleButton((width / 2) - 57, height / 2 - 45, 0);
				styleButton[1] = new AttackStyleButton((width / 2) + 1, height / 2 - 45, 1);
				styleButton[2] = new AttackStyleButton((width / 2) - 57, height / 2 - 18, 2);
				styleButton[3] = new AttackStyleButton((width / 2) + 1, height / 2 - 18, 3);
				styleButton[4] = new AttackStyleButton((width / 2) - 57, height / 2 + 9, 4);
				styleButton[5] = new AttackStyleButton((width / 2) + 1, height / 2 + 9, 5);
				
				styleName[i] = styleCap.getStyleName(i);
			}
			if(styleButton[i] != null) {
				this.addButton(styleButton[i]);
			}
		}
	}
	
	@Override
	public void render(final MatrixStack stack, final int mouseX, final int mouseY, final float partialTicks) {
		super.render(stack, mouseX, mouseY, partialTicks);
		renderBackground(stack);
		stack.push();
		stack.scale(1F, 1F, 1F);
		mc.getTextureManager().bindTexture(TEXTURE);
		this.blit(stack, guiLeft, guiTop, 0, 0, xSize, ySize);
		drawCenteredString(stack, font, "Combat level: " + skillCap.getCombatLevel(), width / 2, (height / 2) - 70, 111111);

		for(int i = 0; i < 6; i++) {
			if(styleButton[i] != null) {
				styleButton[i].renderButton(stack, mouseX, mouseY, partialTicks);
				
				if(styleButton[i].isHovered()) {
					this.renderTooltip(stack, new StringTextComponent(styleCap.getStyleDescription(i)), mouseX, mouseY);
				}
				
				if(styleButton[i].mouseClicked(mouseX, mouseY, 0)) {
					buttons.clear();
					this.init();
				}
			}
			if(styleName[i] != null) {
				drawCenteredString(stack, font, styleName[0], width / 2 - 30, height / 2 - 40, Integer.parseInt("FFFFFF", 16));
				drawCenteredString(stack, font, styleName[1], width / 2 + 29, height / 2 - 40, Integer.parseInt("FFFFFF", 16));
				drawCenteredString(stack, font, styleName[2], width / 2 - 30, height / 2 - 13, Integer.parseInt("FFFFFF", 16));
				drawCenteredString(stack, font, styleName[3], width / 2 + 29, height / 2 - 13, Integer.parseInt("FFFFFF", 16));
				drawCenteredString(stack, font, styleName[4], width / 2 - 30, height / 2 + 14, Integer.parseInt("FFFFFF", 16));
				drawCenteredString(stack, font, styleName[5], width / 2 + 29, height / 2 + 14, Integer.parseInt("FFFFFF", 16));
			}
		}
		stack.pop();
	}
}
