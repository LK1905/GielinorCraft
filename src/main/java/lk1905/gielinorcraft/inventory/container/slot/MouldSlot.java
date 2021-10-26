package lk1905.gielinorcraft.inventory.container.slot;

import lk1905.gielinorcraft.inventory.container.GcAbstractFurnaceContainer;
import lk1905.gielinorcraft.item.MouldItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MouldSlot extends SlotItemHandler{

	public MouldSlot(GcAbstractFurnaceContainer container, IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack.getItem() instanceof MouldItem) {
			return true;
		}else {
			return false;
		}
	}
}
