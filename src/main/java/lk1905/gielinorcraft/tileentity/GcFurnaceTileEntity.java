package lk1905.gielinorcraft.tileentity;

import lk1905.gielinorcraft.init.GcTileEntityType;
import lk1905.gielinorcraft.inventory.container.GcFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GcFurnaceTileEntity extends GcAbstractFurnaceTileEntity{
	
	public GcFurnaceTileEntity() {
		super(GcTileEntityType.FURNACE.get(), null);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new StringTextComponent("Furnace");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new GcFurnaceContainer(id, player, this, this.furnaceData);
	}
}
