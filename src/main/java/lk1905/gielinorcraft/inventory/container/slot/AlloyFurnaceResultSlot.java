package lk1905.gielinorcraft.inventory.container.slot;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraftforge.items.IItemHandler;

/**This class does the same as {@link FurnaceResultSlot}, only uses {@link IItemHandler} instead of {@link IInventory}.*/
public class AlloyFurnaceResultSlot extends Slot{
	private final Player player;
	private int removeCount;

	public AlloyFurnaceResultSlot(Player player, Container inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
		this.player = player;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack remove(int amount) {
		if (this.hasItem()) {
			this.removeCount += Math.min(amount, this.getItem().getCount());
		}

		return super.remove(amount);
	}

	@Override
	public void onTake(Player thePlayer, ItemStack stack) {
		super.onTake(thePlayer, stack);
		this.checkTakeAchievements(stack);
	}

	@Override
	protected void onQuickCraft(ItemStack stack, int amount) {
		this.removeCount += amount;
		this.checkTakeAchievements(stack);
	}
	
	@Override
	protected void checkTakeAchievements(ItemStack stack) {
	      stack.onCraftedBy(this.player.level, this.player, this.removeCount);
	      if (this.player instanceof ServerPlayer && this.container instanceof AbstractFurnaceBlockEntity) {
	         ((AbstractFurnaceBlockEntity)this.container).awardUsedRecipesAndPopExperience((ServerPlayer)this.player);
	      }

	      this.removeCount = 0;
	      net.minecraftforge.fmllegacy.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
	   }
}
