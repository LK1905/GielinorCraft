package lk1905.gielinorcraft.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Blocks;

public class GcCreativeTabs {

	public static final CreativeModeTab TAB_DECORATIONS = new CreativeModeTab(0, "decorations") {
		public ItemStack makeIcon() {
			return new ItemStack(Blocks.PEONY);
		}
	};
	
	public static final CreativeModeTab TAB_COMBAT = (new CreativeModeTab(1, "combat") {
		public ItemStack makeIcon() {
			return new ItemStack(Items.GOLDEN_SWORD);
		}
	}).setEnchantmentCategories(new EnchantmentCategory[]{EnchantmentCategory.VANISHABLE, EnchantmentCategory.ARMOR, EnchantmentCategory.ARMOR_FEET, EnchantmentCategory.ARMOR_HEAD, EnchantmentCategory.ARMOR_LEGS, EnchantmentCategory.ARMOR_CHEST, EnchantmentCategory.BOW, EnchantmentCategory.WEAPON, EnchantmentCategory.WEARABLE, EnchantmentCategory.BREAKABLE, EnchantmentCategory.TRIDENT, EnchantmentCategory.CROSSBOW});	

	public static final CreativeModeTab TAB_MISC = new CreativeModeTab(2, "misc") {
		public ItemStack makeIcon() {
			return new ItemStack(Items.LAVA_BUCKET);
		}
	};
}
