package lk1905.gielinorcraft.item;

import lk1905.gielinorcraft.Gielinorcraft;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DaggerItem extends SwordItem{
	
	public DaggerItem(Tier tier, Properties builderIn) {
		super(tier, 0, -2.9F, builderIn);
	}
}