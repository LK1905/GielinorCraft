package lk1905.gielinorcraft.inventory.container;

import lk1905.gielinorcraft.inventory.container.slot.GcFurnaceFuelSlot;
import lk1905.gielinorcraft.inventory.container.slot.GcFurnaceResultSlot;
import lk1905.gielinorcraft.inventory.container.slot.MouldSlot;
import lk1905.gielinorcraft.tileentity.GcAbstractFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

/**This is mostly a copy of {@link AbstractFurnaceContainer}, but with a 2nd input slot.
 * It also uses {@link IItemHandler} instead of {@link IInventory}.*/
public abstract class GcAbstractFurnaceContainer extends Container{

	private final IItemHandler furnaceInventory;
	private final IIntArray furnaceData;
	protected final World world;
	private final IRecipeType<? extends AbstractCookingRecipe> recipeType;
	
	public GcAbstractFurnaceContainer(ContainerType<?> type, IRecipeType<? extends AbstractCookingRecipe> recipeType, int id, PlayerInventory player) {
		this(type, recipeType, id, player, new ItemStackHandler(5), new IntArray(5));
	}
	
	public GcAbstractFurnaceContainer(ContainerType<?> type, IRecipeType<? extends AbstractCookingRecipe> recipeType, int id, PlayerInventory player, IItemHandler inventory, IIntArray array) {
		super(type, id);
		assertIntArraySize(array, 5);
		this.furnaceInventory = inventory;
		this.furnaceData = array;
		this.world = player.player.world;
		this.recipeType = recipeType;
		this.addSlot(new SlotItemHandler(inventory, 0, 38, 17));
		this.addSlot(new SlotItemHandler(inventory, 1, 74, 17));
		this.addSlot(new GcFurnaceFuelSlot(this, inventory, 2, 56, 53));
		this.addSlot(new GcFurnaceResultSlot(player.player, inventory, 3, 116, 35));
		this.addSlot(new MouldSlot(this, inventory, 4, 18, 53));

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(player, k, 8 + k * 18, 142));
		}

		this.trackIntArray(array);
	}

	public void fillStackedContents(RecipeItemHelper itemHelperIn) {
		if (this.furnaceInventory instanceof IRecipeHelperPopulator) {
			((IRecipeHelperPopulator)this.furnaceInventory).fillStackedContents(itemHelperIn);
		}
	}

	public int getOutputSlot() {
		return 3;
	}

	public int getWidth() {
		return 1;
	}

	public int getHeight() {
		return 1;
	}

	@OnlyIn(Dist.CLIENT)
	public int getSize() {
		return 5;
	}
	
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 3) {
				if (!this.mergeItemStack(itemstack1, 4, 40, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0 && index != 2 && index != 4) {
				if(!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
				} else if (this.isFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 4 && index < 31) {
					if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected boolean hasRecipe(ItemStack stack) {
	      return this.world.getRecipeManager().getRecipe((IRecipeType)this.recipeType, new Inventory(stack), this.world).isPresent();
	   }

	public boolean isFuel(ItemStack stack) {
		return GcAbstractFurnaceTileEntity.isFuel(stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getCookProgressionScaled() {
		int i = this.furnaceData.get(2);
		int j = this.furnaceData.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnLeftScaled() {
		int i = this.furnaceData.get(1);
		if (i == 0) {
			i = 200;
		}

		return this.furnaceData.get(0) * 13 / i;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isBurning() {
		return this.furnaceData.get(0) > 0;
	}
}
