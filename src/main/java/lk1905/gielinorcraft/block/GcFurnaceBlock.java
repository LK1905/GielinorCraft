package lk1905.gielinorcraft.block;

import lk1905.gielinorcraft.tileentity.GcFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class GcFurnaceBlock extends FurnaceBlock{

	public GcFurnaceBlock() {
		super(Block.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.5F).harvestTool(ToolType.PICKAXE));
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new GcFurnaceTileEntity();
	}

	@Override
	public void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tile_entity = worldIn.getTileEntity(pos);
		if(player instanceof ServerPlayerEntity && tile_entity instanceof GcFurnaceTileEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile_entity, pos);
		}
	}
}
