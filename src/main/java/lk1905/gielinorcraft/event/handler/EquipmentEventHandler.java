package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyle;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.stat.IStats;
import lk1905.gielinorcraft.capability.stat.StatCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EquipmentEventHandler {

	@SubscribeEvent
	public static void onWield(LivingEquipmentChangeEvent event) {
		LivingEntity entity = event.getEntityLiving();
		IAttackStyle style = entity.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		IStats stats = entity.getCapability(StatCapability.STAT_CAP).orElse(null);
		
		Item to = event.getTo().getItem();
		Item from = event.getFrom().getItem();
		
		if(to instanceof TieredItem) {
			if(event.getSlot() == EquipmentSlotType.OFFHAND && entity instanceof PlayerEntity) {
				if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
					PacketHandler.sendTo(new StringPacket("You cannot wield this item in your offhand."), (ServerPlayerEntity) entity);
				}
			}
		}
		
		if(event.getSlot() == EquipmentSlotType.MAINHAND && entity.getHeldItemMainhand().getItem() == to) {
			if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
				style.sync((ServerPlayerEntity) entity);
			}
			if(entity.getHeldItemMainhand().getItem() instanceof SwordItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 0, 6);
				stats.setSlotAccuracy(1, 1, 4);
				stats.setSlotMeleeStrength(1, 7);
			}else if(entity.getHeldItemMainhand().getItem() instanceof AxeItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 1, 5);
				stats.setSlotAccuracy(1, 2, 3);
				stats.setSlotMeleeStrength(1, 7);
			}else if(entity.getHeldItemMainhand().getItem() instanceof PickaxeItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 0, 5);
				stats.setSlotAccuracy(1, 2, 3);
				stats.setSlotMeleeStrength(1, 7);
			}else if(entity.getHeldItemMainhand().getItem() instanceof ShovelItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_CRUSH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_CRUSH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 1, 3);
				stats.setSlotAccuracy(1, 2, 5);
				stats.setSlotMeleeStrength(1, 7);
			}else if(entity.getHeldItemMainhand().getItem() instanceof HoeItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 0, 3);
				stats.setSlotAccuracy(1, 1, 5);
				stats.setSlotMeleeStrength(1, 7);
			}else if(entity.getHeldItemMainhand().getItem() instanceof BowItem || entity.getHeldItemMainhand().getItem() instanceof CrossbowItem) {
				style.setAttackStyle(0, AttackStyle.RANGED_ACCURATE);
				style.setAttackStyle(1, AttackStyle.RANGED_RAPID);
				style.setAttackStyle(2, AttackStyle.RANGED_LONG);
				style.setAttackStyle(3, AttackStyle.EMPTY);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
			}else {
				style.setAttackStyle(0, AttackStyle.ACCURATE_CRUSH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(2, AttackStyle.DEFENSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyle.EMPTY);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
			}
		}
		
		if(from instanceof TieredItem && !(to instanceof TieredItem)) {
			style.setAttackStyle(0, AttackStyle.ACCURATE_CRUSH);
			style.setAttackStyle(1, AttackStyle.AGGRESSIVE_CRUSH);
			style.setAttackStyle(2, AttackStyle.DEFENSIVE_CRUSH);
			style.setAttackStyle(3, AttackStyle.EMPTY);
			style.setAttackStyle(4, AttackStyle.EMPTY);
			style.setAttackStyle(5, AttackStyle.EMPTY);
			stats.setSlotAccuracy(1, 0, 0);
			stats.setSlotAccuracy(1, 1, 0);
			stats.setSlotAccuracy(1, 2, 0);
			stats.setSlotAccuracy(1, 3, 0);
			stats.setSlotAccuracy(1, 4, 0);
			stats.setSlotMeleeStrength(1, 0);
			stats.setSlotRangedStrength(1, 0);
			stats.setSlotMagicStrength(1, 0.0);
			if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
				style.sync((ServerPlayerEntity) entity);
				stats.sync((ServerPlayerEntity) entity);
			}
		}
	}
}
