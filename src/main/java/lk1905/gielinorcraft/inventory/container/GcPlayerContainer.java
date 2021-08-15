package lk1905.gielinorcraft.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class GcPlayerContainer extends PlayerContainer{

	public GcPlayerContainer(PlayerInventory playerInventory, boolean localWorld, PlayerEntity playerIn) {
		super(playerInventory, localWorld, playerIn);
		
		for(int i1 = 0; i1 < 9; ++i1) {
	         this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 142) {
	        	 public boolean isItemValid(ItemStack stack) {
	                 if(stack.getItem() instanceof ArmorItem) {
	                	 return false;
	                 }else {
	                	 return true;
	                 }
	              }
	         });
	    }
	}
}
