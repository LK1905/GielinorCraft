package lk1905.gielinorcraft.inventory.container;

import lk1905.gielinorcraft.init.GcContainerType;
import lk1905.gielinorcraft.init.GcRecipeType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.IItemHandler;

public class GcFurnaceContainer extends GcAbstractFurnaceContainer {

	public GcFurnaceContainer(int id, PlayerInventory player, IItemHandler inventory, IIntArray array) {
		super(GcContainerType.FURNACE.get(), GcRecipeType.ALLOY, id, player, inventory, array);
	}

	public GcFurnaceContainer(int id, PlayerInventory player, PacketBuffer data) {
		super(GcContainerType.FURNACE.get(), GcRecipeType.ALLOY, id, player);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
}
