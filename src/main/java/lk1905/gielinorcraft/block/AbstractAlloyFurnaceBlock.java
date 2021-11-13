package lk1905.gielinorcraft.block;

import javax.annotation.Nullable;

import lk1905.gielinorcraft.block.entity.AbstractAlloyFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractAlloyFurnaceBlock extends BaseEntityBlock{

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	public AbstractAlloyFurnaceBlock(Properties p_49224_) {
		super(p_49224_);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.valueOf(false)));
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand p_48710_, BlockHitResult p_48711_) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			this.openContainer(world, pos, player);
			return InteractionResult.CONSUME;
		}
	}

	protected abstract void openContainer(Level world, BlockPos pos, Player player);

	public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
		return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
	}

	public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			BlockEntity blockentity = world.getBlockEntity(pos);
			if (blockentity instanceof AbstractFurnaceBlockEntity) {
				((AbstractFurnaceBlockEntity)blockentity).setCustomName(stack.getHoverName());
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean p_48717_) {
		if (!oldState.is(newState.getBlock())) {
			BlockEntity blockentity = world.getBlockEntity(pos);
			if (blockentity instanceof AbstractFurnaceBlockEntity) {
				if (world instanceof ServerLevel) {
					Containers.dropContents(world, pos, (AbstractFurnaceBlockEntity)blockentity);
					((AbstractFurnaceBlockEntity)blockentity).getRecipesToAwardAndPopExperience((ServerLevel)world, Vec3.atCenterOf(pos));
				}
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(oldState, world, pos, newState, p_48717_);
		}
	}

	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(world.getBlockEntity(pos));
	}

	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	public BlockState rotate(BlockState state, Rotation p_48723_) {
		return state.setValue(FACING, p_48723_.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror p_48720_) {
		return state.rotate(p_48720_.getRotation(state.getValue(FACING)));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
		p_48725_.add(FACING, LIT);
	}

	@Nullable
	public static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level world, BlockEntityType<T> type, BlockEntityType<? extends AbstractAlloyFurnaceBlockEntity> p_151990_) {
		return world.isClientSide ? null : createTickerHelper(type, p_151990_, AbstractAlloyFurnaceBlockEntity::serverTick);
	}
}
