package lk1905.gielinorcraft.inventory.container.slot;

import lk1905.gielinorcraft.inventory.container.AbstractAlloyFurnaceMenu;
import lk1905.gielinorcraft.item.MouldItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MouldSlot extends Slot{

	public MouldSlot(AbstractAlloyFurnaceMenu container, Container itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		if(stack.getItem() instanceof MouldItem) {
			return true;
		}else {
			return false;
		}
	}
}
