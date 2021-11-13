package lk1905.gielinorcraft.block.entity;

import lk1905.gielinorcraft.init.GcBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.level.block.state.BlockState;

public class AlloyFurnaceBlockEntity extends AbstractAlloyFurnaceBlockEntity{
	
	public AlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
		super(GcBlockEntityType.FURNACE.get(), pos, state, null);
	}

	protected Component getDefaultName() {
		return new TranslatableComponent("container.furnace");
	}

	protected AbstractContainerMenu createMenu(int p_59293_, Inventory p_59294_) {
		return new FurnaceMenu(p_59293_, p_59294_, this, this.furnaceData);
	}
}
