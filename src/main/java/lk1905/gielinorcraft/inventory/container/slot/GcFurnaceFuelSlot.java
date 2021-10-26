package lk1905.gielinorcraft.inventory.container.slot;

import lk1905.gielinorcraft.inventory.container.GcAbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**This class does the same as {@link FurnaceFuelSlot}, only uses {@link IItemHandler} instead of {@link IInventory}.*/
public class GcFurnaceFuelSlot extends SlotItemHandler{

	private final GcAbstractFurnaceContainer container;
	
	public GcFurnaceFuelSlot(GcAbstractFurnaceContainer container, IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.container = container;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return this.container.isFuel(stack) || isBucket(stack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}
