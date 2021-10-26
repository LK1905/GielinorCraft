package lk1905.gielinorcraft.item;

import lk1905.gielinorcraft.Gielinorcraft;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GcDaggerItem extends SwordItem{
	
	public GcDaggerItem(IItemTier tier, Properties builderIn) {
		super(tier, 0, -2.9F, builderIn);
	}
}