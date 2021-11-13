package lk1905.gielinorcraft.inventory.container.slot;

import lk1905.gielinorcraft.inventory.container.AbstractAlloyFurnaceMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;

/**This class does the same as {@link FurnaceFuelSlot}, only uses {@link IItemHandler} instead of {@link IInventory}.*/
public class AlloyFurnaceFuelSlot extends Slot{

	private final AbstractAlloyFurnaceMenu container;
	
	public AlloyFurnaceFuelSlot(AbstractAlloyFurnaceMenu container, Container inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.container = container;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return this.container.isFuel(stack) || isBucket(stack);
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getMaxStackSize(stack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}
