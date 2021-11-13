package lk1905.gielinorcraft.inventory.container;

import lk1905.gielinorcraft.inventory.container.slot.AlloyFurnaceFuelSlot;
import lk1905.gielinorcraft.inventory.container.slot.AlloyFurnaceResultSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**This is mostly a copy of {@link AbstractFurnaceContainer}, but with a 2nd input slot.*/
public abstract class AbstractAlloyFurnaceMenu extends RecipeBookMenu<Container> {

	public static final int INGREDIENT_SLOT = 0;
	public static final int FUEL_SLOT = 1;
	public static final int RESULT_SLOT = 2;
	public static final int SLOT_COUNT = 3;
	public static final int DATA_COUNT = 4;
	private final Container container;
	private final ContainerData data;
	protected final Level level;
	private final RecipeType<? extends AbstractCookingRecipe> recipeType;
	private final RecipeBookType recipeBookType;

	public AbstractAlloyFurnaceMenu(MenuType<?> menu, RecipeType<? extends AbstractCookingRecipe> recipe, RecipeBookType book, int id, Inventory playerInventory) {
		this(menu, recipe, book, id, playerInventory, new SimpleContainer(3), new SimpleContainerData(4));
	}

	public AbstractAlloyFurnaceMenu(MenuType<?> menu, RecipeType<? extends AbstractCookingRecipe> recipe, RecipeBookType book, int id, Inventory inventory, Container container, ContainerData data) {
		super(menu, id);
		this.recipeType = recipe;
		this.recipeBookType = book;
		checkContainerSize(container, 3);
		checkContainerDataCount(data, 4);
		this.container = container;
		this.data = data;
		this.level = inventory.player.level;
		this.addSlot(new Slot(container, 0, 56, 17));
		this.addSlot(new AlloyFurnaceFuelSlot(this, container, 1, 56, 53));
		this.addSlot(new AlloyFurnaceResultSlot(inventory.player, container, 2, 116, 35));

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
		}
		this.addDataSlots(data);
	}

	public void fillCraftSlotsStackedContents(StackedContents contents) {
		if (this.container instanceof StackedContentsCompatible) {
			((StackedContentsCompatible)this.container).fillStackedContents(contents);
		}
	}

	public void clearCraftingContent() {
		this.getSlot(0).set(ItemStack.EMPTY);
		this.getSlot(2).set(ItemStack.EMPTY);
	}

	public boolean recipeMatches(Recipe<? super Container> recipe) {
		return recipe.matches(this.container, this.level);
	}

	public int getResultSlotIndex() {
		return 2;
	}

	public int getGridWidth() {
		return 1;
	}

	public int getGridHeight() {
		return 1;
	}

	public int getSize() {
		return 3;
	}

	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	public ItemStack quickMoveStack(Player player, int invSlot) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(invSlot);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (invSlot == 2) {
				if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (invSlot != 1 && invSlot != 0) {
				if (this.canSmelt(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (this.isFuel(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (invSlot >= 3 && invSlot < 30) {
					if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (invSlot >= 30 && invSlot < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@SuppressWarnings("unchecked")
	protected boolean canSmelt(ItemStack stack) {
		return this.level.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)this.recipeType, new SimpleContainer(stack), this.level).isPresent();
	}

	public boolean isFuel(ItemStack p_38989_) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(p_38989_, this.recipeType) > 0;
	}

	public int getBurnProgress() {
		int i = this.data.get(2);
		int j = this.data.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}

	public int getLitProgress() {
		int i = this.data.get(1);
		if (i == 0) {
			i = 200;
		}
		return this.data.get(0) * 13 / i;
	}

	public boolean isLit() {
		return this.data.get(0) > 0;
	}

	public RecipeBookType getRecipeBookType() {
		return this.recipeBookType;
	}

	public boolean shouldMoveToInventory(int p_150463_) {
		return p_150463_ != 1;
	}
}
