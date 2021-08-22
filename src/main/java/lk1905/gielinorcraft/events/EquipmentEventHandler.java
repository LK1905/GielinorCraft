package lk1905.gielinorcraft.events;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
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
				for(int i = 0; i < 6; i++) {
					PacketHandler.sendTo(new StringPacket("Attack Style: "
							+ style.getStyleName(i) + ", " + style.getStyleDescription(i)), (ServerPlayerEntity) entity);
				}
				PacketHandler.sendTo(new StringPacket("Active Style: " + style.getActiveStyle().getName()
						+ ", " + style.getActiveStyle().getDescription()), (ServerPlayerEntity) entity);
				style.sync((ServerPlayerEntity) entity);
			}
			if(entity.getHeldItemMainhand().getItem() instanceof SwordItem) {
				style.setAttackStyle(0, AttackStyles.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyles.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyles.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);							
			}else if(entity.getHeldItemMainhand().getItem() instanceof AxeItem) {
				style.setAttackStyle(0, AttackStyles.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyles.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyles.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}else if(entity.getHeldItemMainhand().getItem() instanceof PickaxeItem) {
				style.setAttackStyle(0, AttackStyles.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyles.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyles.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}else if(entity.getHeldItemMainhand().getItem() instanceof ShovelItem) {
				style.setAttackStyle(0, AttackStyles.ACCURATE_CRUSH);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_CRUSH);
				style.setAttackStyle(2, AttackStyles.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyles.DEFENSIVE_CRUSH);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}else if(entity.getHeldItemMainhand().getItem() instanceof HoeItem) {
				style.setAttackStyle(0, AttackStyles.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyles.AGGRESSIVE_STAB);
				style.setAttackStyle(3, AttackStyles.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}else if(entity.getHeldItemMainhand().getItem() instanceof BowItem || entity.getHeldItemMainhand().getItem() instanceof CrossbowItem) {
				style.setAttackStyle(0, AttackStyles.RANGED_ACCURATE);
				style.setAttackStyle(1, AttackStyles.RANGED_RAPID);
				style.setAttackStyle(2, AttackStyles.RANGED_LONG);
				style.setAttackStyle(3, AttackStyles.EMPTY);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}else {
				style.setAttackStyle(0, AttackStyles.ACCURATE_CRUSH);
				style.setAttackStyle(1, AttackStyles.AGGRESSIVE_CRUSH);
				style.setAttackStyle(2, AttackStyles.DEFENSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyles.EMPTY);
				style.setAttackStyle(4, AttackStyles.EMPTY);
				style.setAttackStyle(5, AttackStyles.EMPTY);
			}
		}
		
		if(from instanceof TieredItem && !(to instanceof TieredItem)) {
			style.setAttackStyle(0, AttackStyles.ACCURATE_CRUSH);
			style.setAttackStyle(1, AttackStyles.AGGRESSIVE_CRUSH);
			style.setAttackStyle(2, AttackStyles.DEFENSIVE_CRUSH);
			style.setAttackStyle(3, AttackStyles.EMPTY);
			style.setAttackStyle(4, AttackStyles.EMPTY);
			style.setAttackStyle(5, AttackStyles.EMPTY);		
			if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
				style.sync((ServerPlayerEntity) entity);
				PacketHandler.sendTo(new StringPacket("Weapon is no longer being wielded."), (ServerPlayerEntity) entity);
			}
		}
	}
}
