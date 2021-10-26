package lk1905.gielinorcraft.tileentity;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.IItemHandler;

public abstract class GcAbstractFurnaceTileEntity extends LockableTileEntity implements IItemHandler, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

	   protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
	   private int burnTime;
	   private int recipesUsed;
	   private int cookTime;
	   private int cookTimeTotal;
	   protected final IIntArray furnaceData = new IIntArray() {
	      public int get(int index) {
	         switch(index) {
	         case 0:
	            return GcAbstractFurnaceTileEntity.this.burnTime;
	         case 1:
	            return GcAbstractFurnaceTileEntity.this.recipesUsed;
	         case 2:
	            return GcAbstractFurnaceTileEntity.this.cookTime;
	         case 3:
	            return GcAbstractFurnaceTileEntity.this.cookTimeTotal;
	         default:
	            return 0;
	         }
	      }

	      public void set(int index, int value) {
	         switch(index) {
	         case 0:
	            GcAbstractFurnaceTileEntity.this.burnTime = value;
	            break;
	         case 1:
	            GcAbstractFurnaceTileEntity.this.recipesUsed = value;
	            break;
	         case 2:
	            GcAbstractFurnaceTileEntity.this.cookTime = value;
	            break;
	         case 3:
	            GcAbstractFurnaceTileEntity.this.cookTimeTotal = value;
	         }

	      }

	      public int size() {
	         return 5;
	      }
	   };
	private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
	protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;
	
	protected GcAbstractFurnaceTileEntity(TileEntityType<?> typeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(typeIn);
		this.recipeType = recipeTypeIn;
	}



	@Override
	public int getSlots() {
		return items.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if(isItemValid(slot, stack) == true) {
			return stack;
		}else {
			return null;
		}
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		ItemStack stack = getStackInSlot(slot);
		Item item = stack.getItem();
		if (slot == 2 && (item != Items.WATER_BUCKET && item != Items.BUCKET)) {
            return stack;
         }else {
        	 return null;
         }
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public boolean isItemValid(int slot, ItemStack stack) {
		if (slot == 2) {
	         return false;
	      } else if (slot != 1) {
	         return true;
	      } else {
	         ItemStack itemstack = this.items.get(1);
	         return isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
	      }
	}

	@Override
	public void setRecipeUsed(IRecipe<?> recipe) {
		if (recipe != null) {
	         ResourceLocation resourcelocation = recipe.getId();
	         this.recipes.addTo(resourcelocation, 1);
	      }
	}

	@Override
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void fillStackedContents(RecipeItemHelper helper) {
		for(ItemStack itemstack : this.items) {
	         helper.accountStack(itemstack);
	      }
	}

	private boolean isBurning() {
	      return this.burnTime > 0;
	   }
	
	@SuppressWarnings("unchecked")
	@Override
	public void tick() {
		boolean flag = this.isBurning();
	      boolean flag1 = false;
	      if (this.isBurning()) {
	         --this.burnTime;
	      }

	      if (!this.world.isRemote) {
	         ItemStack itemstack = this.items.get(1);
	         if (this.isBurning() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
	            IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).orElse(null);
	            if (!this.isBurning() && this.canSmelt(irecipe)) {
	               this.burnTime = this.getBurnTime(itemstack);
	               this.recipesUsed = this.burnTime;
	               if (this.isBurning()) {
	                  flag1 = true;
	                  if (itemstack.hasContainerItem())
	                      this.items.set(1, itemstack.getContainerItem());
	                  else
	                  if (!itemstack.isEmpty()) {
	                     itemstack.shrink(1);
	                     if (itemstack.isEmpty()) {
	                        this.items.set(1, itemstack.getContainerItem());
	                     }
	                  }
	               }
	            }

	            if (this.isBurning() && this.canSmelt(irecipe)) {
	               ++this.cookTime;
	               if (this.cookTime == this.cookTimeTotal) {
	                  this.cookTime = 0;
	                  this.cookTimeTotal = this.getCookTime();
	                  this.smelt(irecipe);
	                  flag1 = true;
	               }
	            } else {
	               this.cookTime = 0;
	            }
	         } else if (!this.isBurning() && this.cookTime > 0) {
	            this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
	         }

	         if (flag != this.isBurning()) {
	            flag1 = true;
	            this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isBurning())), 3);
	         }
	      }

	      if (flag1) {
	         this.markDirty();
	      }
	}
	
	protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
	      if (!this.items.get(0).isEmpty() && recipeIn != null) {
	         ItemStack itemstack = recipeIn.getRecipeOutput();
	         if (itemstack.isEmpty()) {
	            return false;
	         } else {
	            ItemStack itemstack1 = this.items.get(3);
	            if (itemstack1.isEmpty()) {
	               return true;
	            } else if (!itemstack1.isItemEqual(itemstack)) {
	               return false;
	            } else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
	               return true;
	            } else {
	               return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
	            }
	         }
	      } else {
	         return false;
	      }
	   }

	   private void smelt(@Nullable IRecipe<?> recipe) {
	      if (recipe != null && this.canSmelt(recipe)) {
	         ItemStack itemstack = this.items.get(0);
	         ItemStack itemstack1 = recipe.getRecipeOutput();
	         ItemStack itemstack2 = this.items.get(3);
	         ItemStack itemstack3 = this.items.get(1);
	         if (itemstack2.isEmpty()) {
	            this.items.set(3, itemstack1.copy());
	         } else if (itemstack2.getItem() == itemstack1.getItem()) {
	            itemstack2.grow(itemstack1.getCount());
	         }

	         if (!this.world.isRemote) {
	            this.setRecipeUsed(recipe);
	         }

	         if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(2).isEmpty() && this.items.get(2).getItem() == Items.BUCKET) {
	            this.items.set(2, new ItemStack(Items.WATER_BUCKET));
	         }

	        	itemstack.shrink(1);
	        	itemstack3.shrink(1);
	      }
	   }

	   protected int getBurnTime(ItemStack fuel) {
	      if (fuel.isEmpty()) {
	         return 0;
	      } else {
	         return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
	      }
	   }

	   @SuppressWarnings("unchecked")
	protected int getCookTime() {
	      return this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).map(AbstractCookingRecipe::getCookTime).orElse(200);
	   }

	   public static boolean isFuel(ItemStack stack) {
	      return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
	   }

	@Override
	public int getSizeInventory() {
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
	      boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
	      this.items.set(index, stack);
	      if (stack.getCount() > this.getInventoryStackLimit()) {
	         stack.setCount(this.getInventoryStackLimit());
	      }

	      if (index == 0 && !flag) {
	         this.cookTimeTotal = this.getCookTime();
	         this.cookTime = 0;
	         this.markDirty();
	      }
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
	         return false;
	      } else {
	         return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	      }
	}

	@Override
	public void clear() {
		items.clear();
	}
}
