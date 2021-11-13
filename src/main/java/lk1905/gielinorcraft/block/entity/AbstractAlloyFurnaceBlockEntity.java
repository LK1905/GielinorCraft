package lk1905.gielinorcraft.block.entity;

import java.util.List;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lk1905.gielinorcraft.block.AbstractAlloyFurnaceBlock;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/**This is mostly a copy of {@link AbstractFurnaceBlockEntity}, modified to allow for multiple item input furnace recipes.*/
public abstract class AbstractAlloyFurnaceBlockEntity extends BaseContainerBlockEntity implements RecipeHolder, WorldlyContainer, StackedContentsCompatible {

	protected static final int SLOT_INPUT = 0;
	protected static final int SLOT_FUEL = 1;
	protected static final int SLOT_RESULT = 2;
	public static final int DATA_LIT_TIME = 0;
	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
	private static final int[] SLOTS_FOR_SIDES = new int[]{1};
	public static final int DATA_LIT_DURATION = 1;
	public static final int DATA_COOKING_PROGRESS = 2;
	public static final int DATA_COOKING_TOTAL_TIME = 3;
	public static final int NUM_DATA_VALUES = 4;
	public static final int BURN_TIME_STANDARD = 200;
	public static final int BURN_COOL_SPEED = 2;
	protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);
	int litTime;
	int litDuration;
	private int cookingProgress;
	private int cookingTotalTime;
	protected final ContainerData furnaceData = new ContainerData() {
		public int get(int index) {
			switch(index) {
			case 0:
				return AbstractAlloyFurnaceBlockEntity.this.litTime;
			case 1:
				return AbstractAlloyFurnaceBlockEntity.this.litDuration;
			case 2:
				return AbstractAlloyFurnaceBlockEntity.this.cookingProgress;
			case 3:
				return AbstractAlloyFurnaceBlockEntity.this.cookingTotalTime;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch(index) {
			case 0:
				AbstractAlloyFurnaceBlockEntity.this.litTime = value;
	            break;
			case 1:
	            AbstractAlloyFurnaceBlockEntity.this.litDuration = value;
	            break;
			case 2:
	            AbstractAlloyFurnaceBlockEntity.this.cookingProgress = value;
	            break;
			case 3:
	            AbstractAlloyFurnaceBlockEntity.this.cookingTotalTime = value;
			}

		}

		@Override
		public int getCount() {
			return 5;
		}
	};
	private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
	protected final RecipeType<? extends AbstractCookingRecipe> recipeType;
	
	protected AbstractAlloyFurnaceBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(typeIn, pos, state);
		this.recipeType = recipeTypeIn;
	}

	private boolean isLit() {
		return this.litTime > 0;
	}

	public void load(CompoundTag p_155025_) {
		super.load(p_155025_);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(p_155025_, this.items);
		this.litTime = p_155025_.getInt("BurnTime");
		this.cookingProgress = p_155025_.getInt("CookTime");
		this.cookingTotalTime = p_155025_.getInt("CookTimeTotal");
		this.litDuration = this.getBurnDuration(this.items.get(1));
		CompoundTag compoundtag = p_155025_.getCompound("RecipesUsed");

		for(String s : compoundtag.getAllKeys()) {
			this.recipes.put(new ResourceLocation(s), compoundtag.getInt(s));
		}
	}

	public CompoundTag save(CompoundTag p_58379_) {
		super.save(p_58379_);
		p_58379_.putInt("BurnTime", this.litTime);
		p_58379_.putInt("CookTime", this.cookingProgress);
		p_58379_.putInt("CookTimeTotal", this.cookingTotalTime);
		ContainerHelper.saveAllItems(p_58379_, this.items);
		CompoundTag compoundtag = new CompoundTag();
		this.recipes.forEach((p_58382_, p_58383_) -> {
			compoundtag.putInt(p_58382_.toString(), p_58383_);
		});
		p_58379_.put("RecipesUsed", compoundtag);
		return p_58379_;
	}

	public static void serverTick(Level world, BlockPos pos, BlockState state, AbstractAlloyFurnaceBlockEntity blockEntity) {
		boolean flag = blockEntity.isLit();
		boolean flag1 = false;
		if (blockEntity.isLit()) {
			--blockEntity.litTime;
		}

		ItemStack itemstack = blockEntity.items.get(3);
		if (blockEntity.isLit() || !itemstack.isEmpty() && !blockEntity.items.get(0).isEmpty()) {
			@SuppressWarnings("unchecked")
			Recipe<?> recipe = world.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)blockEntity.recipeType, blockEntity, world).orElse(null);
			int i = blockEntity.getMaxStackSize();
			if (!blockEntity.isLit() && blockEntity.canBurn(recipe, blockEntity.items, i)) {
				blockEntity.litTime = blockEntity.getBurnDuration(itemstack);
				blockEntity.litDuration = blockEntity.litTime;
	            if (blockEntity.isLit()) {
	            	flag1 = true;
	            	if (itemstack.hasContainerItem())
	            		blockEntity.items.set(3, itemstack.getContainerItem());
	            	else if (!itemstack.isEmpty()) {
	            		//Item item = itemstack.getItem();
	            		itemstack.shrink(1);
	            		if (itemstack.isEmpty()) {
	            			blockEntity.items.set(3, itemstack.getContainerItem());
	            		}
	            	}
	            }
			}

			if (blockEntity.isLit() && blockEntity.canBurn(recipe, blockEntity.items, i)) {
				++blockEntity.cookingProgress;
	            if (blockEntity.cookingProgress == blockEntity.cookingTotalTime) {
	            	blockEntity.cookingProgress = 0;
	            	blockEntity.cookingTotalTime = getTotalCookTime(world, blockEntity.recipeType, blockEntity);
	            	if (blockEntity.burn(recipe, blockEntity.items, i)) {
	            		blockEntity.setRecipeUsed(recipe);
	            	}

	            	flag1 = true;
	            }
			} else {
				blockEntity.cookingProgress = 0;
			}
		} else if (!blockEntity.isLit() && blockEntity.cookingProgress > 0) {
			blockEntity.cookingProgress = Mth.clamp(blockEntity.cookingProgress - 2, 0, blockEntity.cookingTotalTime);
		}

		if (flag != blockEntity.isLit()) {
			flag1 = true;
			state = state.setValue(AbstractAlloyFurnaceBlock.LIT, Boolean.valueOf(blockEntity.isLit()));
			world.setBlock(pos, state, 3);
		}

		if (flag1) {
			setChanged(world, pos, state);
		}
	}

	private boolean canBurn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> itemList, int amount) {
		if (!itemList.get(0).isEmpty() && recipe != null) {
			@SuppressWarnings("unchecked")
			ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);
			if (itemstack.isEmpty()) {
				return false;
			} else {
	            ItemStack itemstack1 = itemList.get(2);
	            if (itemstack1.isEmpty()) {
	            	return true;
	            } else if (!itemstack1.sameItem(itemstack)) {
	            	return false;
	            } else if (itemstack1.getCount() + itemstack.getCount() <= amount && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
	            	return true;
	            } else {
	            	return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
	            }
	         }
		} else {
	         return false;
		}
	}

	private boolean burn(@Nullable Recipe<?> recipe, NonNullList<ItemStack> itemList, int amount) {
		if (recipe != null && this.canBurn(recipe, itemList, amount)) {
			ItemStack itemstack = itemList.get(0);
			@SuppressWarnings("unchecked")
			ItemStack itemstack1 = ((Recipe<WorldlyContainer>) recipe).assemble(this);
			ItemStack itemstack2 = itemList.get(2);
			if (itemstack2.isEmpty()) {
				itemList.set(2, itemstack1.copy());
			} else if (itemstack2.is(itemstack1.getItem())) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !itemList.get(1).isEmpty() && itemList.get(1).is(Items.BUCKET)) {
				itemList.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.shrink(1);
			return true;
		} else {
			return false;
		}
	}

	protected int getBurnDuration(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType);
		}
	}

	@SuppressWarnings("unchecked")
	private static int getTotalCookTime(Level world, RecipeType<? extends AbstractCookingRecipe> type, Container container) {
		return world.getRecipeManager().getRecipeFor((RecipeType<AbstractCookingRecipe>)type, container, world).map(AbstractCookingRecipe::getCookingTime).orElse(200);
	}

	public static boolean isFuel(ItemStack stack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0;
	}

	public int[] getSlotsForFace(Direction face) {
		if (face == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return face == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	public boolean canPlaceItemThroughFace(int p_58336_, ItemStack stack, @Nullable Direction face) {
		return this.canPlaceItem(p_58336_, stack);
	}

	public boolean canTakeItemThroughFace(int p_58392_, ItemStack stack, Direction face) {
		if (face == Direction.DOWN && p_58392_ == 1) {
			return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
		} else {
			return true;
		}
	}

	public int getContainerSize() {
		return this.items.size();
	}

	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public ItemStack getItem(int p_58328_) {
		return this.items.get(p_58328_);
	}

	public ItemStack removeItem(int p_58330_, int p_58331_) {
		return ContainerHelper.removeItem(this.items, p_58330_, p_58331_);
	}

	public ItemStack removeItemNoUpdate(int p_58387_) {
		return ContainerHelper.takeItem(this.items, p_58387_);
	}

	public void setItem(int p_58333_, ItemStack stack) {
		ItemStack itemstack = this.items.get(p_58333_);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack) && ItemStack.tagMatches(stack, itemstack);
		this.items.set(p_58333_, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}

		if (p_58333_ == 0 && !flag) {
			this.cookingTotalTime = getTotalCookTime(this.level, this.recipeType, this);
			this.cookingProgress = 0;
			this.setChanged();
		}
	}

	public boolean stillValid(Player player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	public boolean canPlaceItem(int p_58389_, ItemStack stack) {
		if (p_58389_ == 2) {
			return false;
		} else if (p_58389_ != 1) {
			return true;
		} else {
			ItemStack itemstack = this.items.get(1);
			return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
		}
	}

	public void clearContent() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable Recipe<?> p_58345_) {
		if (p_58345_ != null) {
			ResourceLocation resourcelocation = p_58345_.getId();
			this.recipes.addTo(resourcelocation, 1);
		}
	}

	@Nullable
	public Recipe<?> getRecipeUsed() {
		return null;
	}

	public void awardUsedRecipes(Player p_58396_) {
	}

	public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
		List<Recipe<?>> list = this.getRecipesToAwardAndPopExperience(player.getLevel(), player.position());
		player.awardRecipes(list);
		this.recipes.clear();
	}

	public List<Recipe<?>> getRecipesToAwardAndPopExperience(ServerLevel server, Vec3 p_154997_) {
		List<Recipe<?>> list = Lists.newArrayList();

		for(Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
			server.getRecipeManager().byKey(entry.getKey()).ifPresent((p_155023_) -> {
				list.add(p_155023_);
	            createExperience(server, p_154997_, entry.getIntValue(), ((AbstractCookingRecipe)p_155023_).getExperience());
			});
		}
		return list;
	}

	private static void createExperience(ServerLevel server, Vec3 p_155000_, int p_155001_, float p_155002_) {
		int i = Mth.floor((float)p_155001_ * p_155002_);
		float f = Mth.frac((float)p_155001_ * p_155002_);
		if (f != 0.0F && Math.random() < (double)f) {
			++i;
		}
		ExperienceOrb.award(server, p_155000_, i);
	}

	public void fillStackedContents(StackedContents p_58342_) {
		for(ItemStack itemstack : this.items) {
			p_58342_.accountStack(itemstack);
		}
	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
			net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
	            return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

	@Override
	public void reviveCaps() {
	      super.reviveCaps();
	      this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}
}