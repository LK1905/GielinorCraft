package lk1905.gielinorcraft.inventory.container;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class AlloyFurnaceMenu extends AbstractAlloyFurnaceMenu {

	public AlloyFurnaceMenu(int id, Inventory inventory) {
	      super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, id, inventory);
	   }

	   public AlloyFurnaceMenu(int id, Inventory inventory, Container container, ContainerData data) {
	      super(MenuType.FURNACE, RecipeType.SMELTING, RecipeBookType.FURNACE, id, inventory, container, data);
	   }
}
