package lk1905.gielinorcraft.event.handler;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyle;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.stat.IStats;
import lk1905.gielinorcraft.capability.stat.StatCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleServerPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ShootableItem;
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
		
		if(style.getActiveStyle() == AttackStyle.EMPTY) {
			style.setActiveSlot(0);
			if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
				PacketHandler.sendTo(new AttackStyleServerPacket(0), (ServerPlayerEntity) entity);
			}
		}
		
		//-----Mainhand items-----//
		
		if(to instanceof TieredItem || to instanceof ArmorItem) {
			if(event.getSlot() == EquipmentSlotType.OFFHAND && entity instanceof PlayerEntity) {
				entity.entityDropItem(event.getTo());
				entity.setItemStackToSlot(event.getSlot(), ItemStack.EMPTY);
				if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {				
					PacketHandler.sendTo(new StringPacket("You cannot wield this item in your offhand."), (ServerPlayerEntity) entity);
				}
			}
		}
		
		if((from instanceof TieredItem || from instanceof ShootableItem) && !(to instanceof TieredItem || to instanceof ShootableItem)) {
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
			}
		}
		
		if(from instanceof TieredItem || from instanceof ShootableItem) {
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
			}
		}
		
		if(event.getSlot() == EquipmentSlotType.MAINHAND && entity.getHeldItemMainhand().getItem() == to) {
			if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
				style.sync((ServerPlayerEntity) entity);
			}
			Item mainhand = entity.getHeldItemMainhand().getItem();
			
			if(mainhand instanceof SwordItem) {
				if(mainhand == Items.WOODEN_SWORD) {
					stats.setSlotAccuracy(1, 0, 6);
					stats.setSlotAccuracy(1, 1, 4);
					stats.setSlotMeleeStrength(1, 7);
				}else if(mainhand == Items.STONE_SWORD) {
					stats.setSlotAccuracy(1, 0, 11);
					stats.setSlotAccuracy(1, 1, 8);
					stats.setSlotMeleeStrength(1, 12);
				}else if(mainhand == Items.IRON_SWORD) {
					stats.setSlotAccuracy(1, 0, 16);
					stats.setSlotAccuracy(1, 1, 11);
					stats.setSlotMeleeStrength(1, 17);
				}else if(mainhand == Items.GOLDEN_SWORD) {
					stats.setSlotAccuracy(1, 0, 23);
					stats.setSlotAccuracy(1, 1, 18);
					stats.setSlotMeleeStrength(1, 24);
				}else if(mainhand == Items.DIAMOND_SWORD) {
					stats.setSlotAccuracy(1, 0, 38);
					stats.setSlotAccuracy(1, 1, 26);
					stats.setSlotMeleeStrength(1, 39);
				}else if(mainhand == Items.NETHERITE_SWORD) {
					stats.setSlotAccuracy(1, 0, 65);
					stats.setSlotAccuracy(1, 1, 55);
					stats.setSlotMeleeStrength(1, 63);
				}
				style.setAttackStyle(0, AttackStyle.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);	
			}else if(mainhand instanceof AxeItem) {
				if(mainhand == Items.WOODEN_AXE) {
					stats.setSlotAccuracy(1, 1, 5);
					stats.setSlotAccuracy(1, 2, 3);
					stats.setSlotMeleeStrength(1, 7);
				}else if(mainhand == Items.STONE_AXE) {
					stats.setSlotAccuracy(1, 1, 8);
					stats.setSlotAccuracy(1, 2, 6);
					stats.setSlotMeleeStrength(1, 9);
				}else if(mainhand == Items.IRON_AXE) {
					stats.setSlotAccuracy(1, 1, 12);
					stats.setSlotAccuracy(1, 2, 10);
					stats.setSlotMeleeStrength(1, 13);
				}else if(mainhand == Items.GOLDEN_AXE) {
					stats.setSlotAccuracy(1, 1, 17);
					stats.setSlotAccuracy(1, 2, 15);
					stats.setSlotMeleeStrength(1, 19);
				}else if(mainhand == Items.DIAMOND_AXE) {
					stats.setSlotAccuracy(1, 1, 26);
					stats.setSlotAccuracy(1, 2, 24);
					stats.setSlotMeleeStrength(1, 29);
				}else if(mainhand == Items.NETHERITE_AXE) {
					stats.setSlotAccuracy(1, 1, 38);
					stats.setSlotAccuracy(1, 2, 32);
					stats.setSlotMeleeStrength(1, 42);
				}
				style.setAttackStyle(0, AttackStyle.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);			
			}else if(mainhand instanceof PickaxeItem) {
				if(mainhand == Items.WOODEN_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 5);
					stats.setSlotAccuracy(1, 2, 3);
					stats.setSlotMeleeStrength(1, 7);
				}else if(mainhand == Items.STONE_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 8);
					stats.setSlotAccuracy(1, 2, 6);
					stats.setSlotMeleeStrength(1, 9);
				}else if(mainhand == Items.IRON_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 12);
					stats.setSlotAccuracy(1, 2, 10);
					stats.setSlotMeleeStrength(1, 13);
				}else if(mainhand == Items.GOLDEN_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 17);
					stats.setSlotAccuracy(1, 2, 15);
					stats.setSlotMeleeStrength(1, 19);
				}else if(mainhand == Items.DIAMOND_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 26);
					stats.setSlotAccuracy(1, 2, 24);
					stats.setSlotMeleeStrength(1, 29);
				}else if(mainhand == Items.NETHERITE_PICKAXE) {
					stats.setSlotAccuracy(1, 0, 38);
					stats.setSlotAccuracy(1, 2, 32);
					stats.setSlotMeleeStrength(1, 42);
				}
				style.setAttackStyle(0, AttackStyle.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
			}else if(mainhand instanceof ShovelItem) {
				if(mainhand == Items.WOODEN_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 3);
					stats.setSlotAccuracy(1, 2, 5);
					stats.setSlotMeleeStrength(1, 7);
				}else if(mainhand == Items.STONE_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 6);
					stats.setSlotAccuracy(1, 2, 8);
					stats.setSlotMeleeStrength(1, 9);
				}else if(mainhand == Items.IRON_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 10);
					stats.setSlotAccuracy(1, 2, 12);
					stats.setSlotMeleeStrength(1, 13);
				}else if(mainhand == Items.GOLDEN_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 15);
					stats.setSlotAccuracy(1, 2, 17);
					stats.setSlotMeleeStrength(1, 19);
				}else if(mainhand == Items.DIAMOND_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 24);
					stats.setSlotAccuracy(1, 2, 26);
					stats.setSlotMeleeStrength(1, 29);
				}else if(mainhand == Items.NETHERITE_SHOVEL) {
					stats.setSlotAccuracy(1, 1, 32);
					stats.setSlotAccuracy(1, 2, 38);
					stats.setSlotMeleeStrength(1, 42);
				}
				style.setAttackStyle(0, AttackStyle.ACCURATE_CRUSH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_CRUSH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_CRUSH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);			
			}else if(mainhand instanceof HoeItem) {
				if(mainhand == Items.WOODEN_HOE) {
					stats.setSlotAccuracy(1, 0, 3);
					stats.setSlotAccuracy(1, 1, 5);
					stats.setSlotMeleeStrength(1, 7);
				}else if(mainhand == Items.STONE_HOE) {
					stats.setSlotAccuracy(1, 0, 6);
					stats.setSlotAccuracy(1, 1, 8);
					stats.setSlotMeleeStrength(1, 9);
				}else if(mainhand == Items.IRON_HOE) {
					stats.setSlotAccuracy(1, 0, 10);
					stats.setSlotAccuracy(1, 1, 12);
					stats.setSlotMeleeStrength(1, 13);
				}else if(mainhand == Items.GOLDEN_HOE) {
					stats.setSlotAccuracy(1, 0, 15);
					stats.setSlotAccuracy(1, 1, 17);
					stats.setSlotMeleeStrength(1, 19);
				}else if(mainhand == Items.DIAMOND_HOE) {
					stats.setSlotAccuracy(1, 0, 24);
					stats.setSlotAccuracy(1, 1, 26);
					stats.setSlotMeleeStrength(1, 29);
				}else if(mainhand == Items.NETHERITE_HOE) {
					stats.setSlotAccuracy(1, 0, 32);
					stats.setSlotAccuracy(1, 1, 38);
					stats.setSlotMeleeStrength(1, 42);
				}
				style.setAttackStyle(0, AttackStyle.ACCURATE_SLASH);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_SLASH);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
			}else if(mainhand instanceof ShootableItem) {
				if(mainhand == Items.BOW) {
					stats.setSlotAccuracy(1, 4, 8);
					stats.setSlotRangedStrength(1, 10);
				}else if(mainhand == Items.CROSSBOW) {
					stats.setSlotAccuracy(1, 4, 18);
					stats.setSlotRangedStrength(1, 10);
				}
				style.setAttackStyle(0, AttackStyle.RANGED_ACCURATE);
				style.setAttackStyle(1, AttackStyle.RANGED_RAPID);
				style.setAttackStyle(2, AttackStyle.RANGED_LONG);
				style.setAttackStyle(3, AttackStyle.EMPTY);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
			}else if(mainhand instanceof ShearsItem) {
				style.setAttackStyle(0, AttackStyle.ACCURATE_STAB);
				style.setAttackStyle(1, AttackStyle.AGGRESSIVE_STAB);
				style.setAttackStyle(2, AttackStyle.AGGRESSIVE_SLASH);
				style.setAttackStyle(3, AttackStyle.DEFENSIVE_STAB);
				style.setAttackStyle(4, AttackStyle.EMPTY);
				style.setAttackStyle(5, AttackStyle.EMPTY);
				stats.setSlotAccuracy(1, 0, 7);
				stats.setSlotAccuracy(1, 1, 9);
				stats.setSlotMeleeStrength(1, 1);
			}else {
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
			}
		}
		
		//-----Offhand items-----//
		
		if(to instanceof ShieldItem) {
			if(event.getSlot() == EquipmentSlotType.MAINHAND && entity instanceof PlayerEntity) {
				entity.entityDropItem(event.getTo());
				entity.setItemStackToSlot(event.getSlot(), ItemStack.EMPTY);
				if(entity instanceof ServerPlayerEntity && !(entity.world.isRemote)) {
					PacketHandler.sendTo(new StringPacket("You cannot wield this item in your mainhand."), (ServerPlayerEntity) entity);
				}
			}
		}		
		
		if((from instanceof ShieldItem) && !(to instanceof ShieldItem)) {
			stats.setSlotAccuracy(2, 0, 0);
			stats.setSlotAccuracy(2, 1, 0);
			stats.setSlotAccuracy(2, 2, 0);
			stats.setSlotAccuracy(2, 3, 0);
			stats.setSlotAccuracy(2, 4, 0);
			stats.setSlotDefence(2, 0, 0);
			stats.setSlotDefence(2, 1, 0);
			stats.setSlotDefence(2, 2, 0);
			stats.setSlotDefence(2, 3, 0);
			stats.setSlotDefence(2, 4, 0);
			stats.setSlotMeleeStrength(2, 0);
			stats.setSlotRangedStrength(2, 0);
			stats.setSlotMagicStrength(2, 0.0);
		}
		
		if(from instanceof ShieldItem) {
			stats.setSlotAccuracy(2, 0, 0);
			stats.setSlotAccuracy(2, 1, 0);
			stats.setSlotAccuracy(2, 2, 0);
			stats.setSlotAccuracy(2, 3, 0);
			stats.setSlotAccuracy(2, 4, 0);
			stats.setSlotDefence(2, 0, 0);
			stats.setSlotDefence(2, 1, 0);
			stats.setSlotDefence(2, 2, 0);
			stats.setSlotDefence(2, 3, 0);
			stats.setSlotDefence(2, 4, 0);
			stats.setSlotMeleeStrength(2, 0);
			stats.setSlotRangedStrength(2, 0);
			stats.setSlotMagicStrength(2, 0.0);
		}
		
		if(event.getSlot() == EquipmentSlotType.OFFHAND && entity.getHeldItemOffhand().getItem() == to) {

			Item offhand = entity.getHeldItemOffhand().getItem();
			
			if(offhand instanceof ShieldItem) {
				stats.setSlotAccuracy(2, 3, -6);
				stats.setSlotDefence(2, 0, 17);
				stats.setSlotDefence(2, 1, 19);
				stats.setSlotDefence(2, 2, 15);
				stats.setSlotDefence(2, 3, 0);
				stats.setSlotDefence(2, 4, 17);
			}
		}
		
		//-----head slot-----//
		
		if((from instanceof ArmorItem) && !(to instanceof ArmorItem) && event.getSlot() == EquipmentSlotType.HEAD) {
			stats.setSlotAccuracy(3, 0, 0);
			stats.setSlotAccuracy(3, 1, 0);
			stats.setSlotAccuracy(3, 2, 0);
			stats.setSlotAccuracy(3, 3, 0);
			stats.setSlotAccuracy(3, 4, 0);
			stats.setSlotDefence(3, 0, 0);
			stats.setSlotDefence(3, 1, 0);
			stats.setSlotDefence(3, 2, 0);
			stats.setSlotDefence(3, 3, 0);
			stats.setSlotDefence(3, 4, 0);
			stats.setSlotMeleeStrength(3, 0);
			stats.setSlotRangedStrength(3, 0);
			stats.setSlotMagicStrength(3, 0.0);
		}
		
		if(from instanceof ArmorItem && event.getSlot() == EquipmentSlotType.HEAD) {
			stats.setSlotAccuracy(3, 0, 0);
			stats.setSlotAccuracy(3, 1, 0);
			stats.setSlotAccuracy(3, 2, 0);
			stats.setSlotAccuracy(3, 3, 0);
			stats.setSlotAccuracy(3, 4, 0);
			stats.setSlotDefence(3, 0, 0);
			stats.setSlotDefence(3, 1, 0);
			stats.setSlotDefence(3, 2, 0);
			stats.setSlotDefence(3, 3, 0);
			stats.setSlotDefence(3, 4, 0);
			stats.setSlotMeleeStrength(3, 0);
			stats.setSlotRangedStrength(3, 0);
			stats.setSlotMagicStrength(3, 0.0);
		}
		
		if(event.getSlot() == EquipmentSlotType.HEAD && entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == to) {

			Item head = entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
			
			if(head == Items.LEATHER_HELMET) {
				stats.setSlotAccuracy(3, 3, -1);
				stats.setSlotAccuracy(3, 4, 2);
				stats.setSlotDefence(3, 0, 4);
				stats.setSlotDefence(3, 1, 6);
				stats.setSlotDefence(3, 2, 8);
				stats.setSlotDefence(3, 3, 4);
				stats.setSlotDefence(3, 4, 4);
			}else if(head == Items.TURTLE_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 4);
				stats.setSlotDefence(3, 1, 5);
				stats.setSlotDefence(3, 2, 3);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 4);
			}else if(head == Items.CHAINMAIL_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 7);
				stats.setSlotDefence(3, 1, 8);
				stats.setSlotDefence(3, 2, 6);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 7);
			}else if(head == Items.IRON_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 10);
				stats.setSlotDefence(3, 1, 11);
				stats.setSlotDefence(3, 2, 9);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 10);
			}else if(head == Items.GOLDEN_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 14);
				stats.setSlotDefence(3, 1, 15);
				stats.setSlotDefence(3, 2, 13);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 14);
			}else if(head == Items.DIAMOND_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 22);
				stats.setSlotDefence(3, 1, 23);
				stats.setSlotDefence(3, 2, 21);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 22);
			}else if(head == Items.NETHERITE_HELMET) {
				stats.setSlotAccuracy(3, 3, -3);
				stats.setSlotDefence(3, 0, 33);
				stats.setSlotDefence(3, 1, 35);
				stats.setSlotDefence(3, 2, 32);
				stats.setSlotDefence(3, 3, -1);
				stats.setSlotDefence(3, 4, 34);
			}
		}
		
		//-----chest  slot-----//
		
		if((from instanceof ArmorItem) && !(to instanceof ArmorItem) && event.getSlot() == EquipmentSlotType.CHEST) {
			stats.setSlotAccuracy(4, 0, 0);
			stats.setSlotAccuracy(4, 1, 0);
			stats.setSlotAccuracy(4, 2, 0);
			stats.setSlotAccuracy(4, 3, 0);
			stats.setSlotAccuracy(4, 4, 0);
			stats.setSlotDefence(4, 0, 0);
			stats.setSlotDefence(4, 1, 0);
			stats.setSlotDefence(4, 2, 0);
			stats.setSlotDefence(4, 3, 0);
			stats.setSlotDefence(4, 4, 0);
			stats.setSlotMeleeStrength(4, 0);
			stats.setSlotRangedStrength(4, 0);
			stats.setSlotMagicStrength(4, 0.0);
		}
		
		if(from instanceof ArmorItem && event.getSlot() == EquipmentSlotType.CHEST) {
			stats.setSlotAccuracy(4, 0, 0);
			stats.setSlotAccuracy(4, 1, 0);
			stats.setSlotAccuracy(4, 2, 0);
			stats.setSlotAccuracy(4, 3, 0);
			stats.setSlotAccuracy(4, 4, 0);
			stats.setSlotDefence(4, 0, 0);
			stats.setSlotDefence(4, 1, 0);
			stats.setSlotDefence(4, 2, 0);
			stats.setSlotDefence(4, 3, 0);
			stats.setSlotDefence(4, 4, 0);
			stats.setSlotMeleeStrength(4, 0);
			stats.setSlotRangedStrength(4, 0);
			stats.setSlotMagicStrength(4, 0.0);
		}
		
		if(event.getSlot() == EquipmentSlotType.CHEST && entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == to) {

			Item chest = entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
			
			if(chest == Items.LEATHER_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -4);
				stats.setSlotAccuracy(4, 4, 8);
				stats.setSlotDefence(4, 0, 12);
				stats.setSlotDefence(4, 1, 15);
				stats.setSlotDefence(4, 2, 18);
				stats.setSlotDefence(4, 3, 6);
				stats.setSlotDefence(4, 4, 15);
			}else if(chest == Items.CHAINMAIL_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -30);
				stats.setSlotAccuracy(4, 4, -15);
				stats.setSlotDefence(4, 0, 32);
				stats.setSlotDefence(4, 1, 31);
				stats.setSlotDefence(4, 2, 24);
				stats.setSlotDefence(4, 3, -6);
				stats.setSlotDefence(4, 4, 31);
			}else if(chest == Items.IRON_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -30);
				stats.setSlotAccuracy(4, 4, -15);
				stats.setSlotDefence(4, 0, 46);
				stats.setSlotDefence(4, 1, 44);
				stats.setSlotDefence(4, 2, 38);
				stats.setSlotDefence(4, 3, -6);
				stats.setSlotDefence(4, 4, 44);
			}else if(chest == Items.GOLDEN_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -30);
				stats.setSlotAccuracy(4, 4, -15);
				stats.setSlotDefence(4, 0, 65);
				stats.setSlotDefence(4, 1, 63);
				stats.setSlotDefence(4, 2, 55);
				stats.setSlotDefence(4, 3, -6);
				stats.setSlotDefence(4, 4, 63);
			}else if(chest == Items.DIAMOND_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -30);
				stats.setSlotAccuracy(4, 4, -15);
				stats.setSlotDefence(4, 0, 82);
				stats.setSlotDefence(4, 1, 80);
				stats.setSlotDefence(4, 2, 72);
				stats.setSlotDefence(4, 3, -6);
				stats.setSlotDefence(4, 4, 80);
			}else if(chest == Items.NETHERITE_CHESTPLATE) {
				stats.setSlotAccuracy(4, 3, -30);
				stats.setSlotAccuracy(4, 4, -15);
				stats.setSlotDefence(4, 0, 109);
				stats.setSlotDefence(4, 1, 107);
				stats.setSlotDefence(4, 2, 97);
				stats.setSlotDefence(4, 3, -6);
				stats.setSlotDefence(4, 4, 106);
			}
		}
		
		//-----legs slot-----//
		
		if((from instanceof ArmorItem) && !(to instanceof ArmorItem) && event.getSlot() == EquipmentSlotType.LEGS) {
			stats.setSlotAccuracy(5, 0, 0);
			stats.setSlotAccuracy(5, 1, 0);
			stats.setSlotAccuracy(5, 2, 0);
			stats.setSlotAccuracy(5, 3, 0);
			stats.setSlotAccuracy(5, 4, 0);
			stats.setSlotDefence(5, 0, 0);
			stats.setSlotDefence(5, 1, 0);
			stats.setSlotDefence(5, 2, 0);
			stats.setSlotDefence(5, 3, 0);
			stats.setSlotDefence(5, 4, 0);
			stats.setSlotMeleeStrength(5, 0);
			stats.setSlotRangedStrength(5, 0);
			stats.setSlotMagicStrength(5, 0.0);
		}
		
		if(from instanceof ArmorItem && event.getSlot() == EquipmentSlotType.LEGS) {
			stats.setSlotAccuracy(5, 0, 0);
			stats.setSlotAccuracy(5, 1, 0);
			stats.setSlotAccuracy(5, 2, 0);
			stats.setSlotAccuracy(5, 3, 0);
			stats.setSlotAccuracy(5, 4, 0);
			stats.setSlotDefence(5, 0, 0);
			stats.setSlotDefence(5, 1, 0);
			stats.setSlotDefence(5, 2, 0);
			stats.setSlotDefence(5, 3, 0);
			stats.setSlotDefence(5, 4, 0);
			stats.setSlotMeleeStrength(5, 0);
			stats.setSlotRangedStrength(5, 0);
			stats.setSlotMagicStrength(5, 0.0);
		}
		
		if(event.getSlot() == EquipmentSlotType.LEGS && entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == to) {

			Item legs = entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem();
			
			if(legs == Items.LEATHER_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -5);
				stats.setSlotAccuracy(5, 4, 6);
				stats.setSlotDefence(5, 0, 8);
				stats.setSlotDefence(5, 1, 8);
				stats.setSlotDefence(5, 2, 10);
				stats.setSlotDefence(5, 3, 4);
				stats.setSlotDefence(5, 4, 10);
			}else if(legs == Items.CHAINMAIL_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -21);
				stats.setSlotAccuracy(5, 4, -11);
				stats.setSlotDefence(5, 0, 17);
				stats.setSlotDefence(5, 1, 16);
				stats.setSlotDefence(5, 2, 15);
				stats.setSlotDefence(5, 3, -4);
				stats.setSlotDefence(5, 4, 16);
			}else if(legs == Items.IRON_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -21);
				stats.setSlotAccuracy(5, 4, -11);
				stats.setSlotDefence(5, 0, 24);
				stats.setSlotDefence(5, 1, 22);
				stats.setSlotDefence(5, 2, 20);
				stats.setSlotDefence(5, 3, -4);
				stats.setSlotDefence(5, 4, 22);
			}else if(legs == Items.GOLDEN_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -21);
				stats.setSlotAccuracy(5, 4, -11);
				stats.setSlotDefence(5, 0, 33);
				stats.setSlotDefence(5, 1, 31);
				stats.setSlotDefence(5, 2, 29);
				stats.setSlotDefence(5, 3, -4);
				stats.setSlotDefence(5, 4, 31);
			}else if(legs == Items.DIAMOND_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -21);
				stats.setSlotAccuracy(5, 4, -11);
				stats.setSlotDefence(5, 0, 51);
				stats.setSlotDefence(5, 1, 49);
				stats.setSlotDefence(5, 2, 47);
				stats.setSlotDefence(5, 3, -4);
				stats.setSlotDefence(5, 4, 49);
			}else if(legs == Items.NETHERITE_LEGGINGS) {
				stats.setSlotAccuracy(5, 3, -21);
				stats.setSlotAccuracy(5, 4, -11);
				stats.setSlotDefence(5, 0, 68);
				stats.setSlotDefence(5, 1, 66);
				stats.setSlotDefence(5, 1, 63);
				stats.setSlotDefence(5, 3, -5);
				stats.setSlotDefence(5, 4, 65);
			}
		}
		
		//-----feet slot-----//
		
		if((from instanceof ArmorItem) && !(to instanceof ArmorItem) && event.getSlot() == EquipmentSlotType.FEET) {
			stats.setSlotAccuracy(6, 0, 0);
			stats.setSlotAccuracy(6, 1, 0);
			stats.setSlotAccuracy(6, 2, 0);
			stats.setSlotAccuracy(6, 3, 0);
			stats.setSlotAccuracy(6, 4, 0);
			stats.setSlotDefence(6, 0, 0);
			stats.setSlotDefence(6, 1, 0);
			stats.setSlotDefence(6, 2, 0);
			stats.setSlotDefence(6, 3, 0);
			stats.setSlotDefence(6, 4, 0);
			stats.setSlotMeleeStrength(6, 0);
			stats.setSlotRangedStrength(6, 0);
			stats.setSlotMagicStrength(6, 0.0);
		}
		
		if(from instanceof ArmorItem && event.getSlot() == EquipmentSlotType.FEET) {
			stats.setSlotAccuracy(6, 0, 0);
			stats.setSlotAccuracy(6, 1, 0);
			stats.setSlotAccuracy(6, 2, 0);
			stats.setSlotAccuracy(6, 3, 0);
			stats.setSlotAccuracy(6, 4, 0);
			stats.setSlotDefence(6, 0, 0);
			stats.setSlotDefence(6, 1, 0);
			stats.setSlotDefence(6, 2, 0);
			stats.setSlotDefence(6, 3, 0);
			stats.setSlotDefence(6, 4, 0);
			stats.setSlotMeleeStrength(6, 0);
			stats.setSlotRangedStrength(6, 0);
			stats.setSlotMagicStrength(6, 0.0);
		}
		
		if(event.getSlot() == EquipmentSlotType.FEET && entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == to) {

			Item feet = entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem();
			
			if(feet == Items.LEATHER_BOOTS) {
				stats.setSlotAccuracy(6, 3, -10);
				stats.setSlotAccuracy(6, 4, 3);
				stats.setSlotDefence(6, 0, 1);
				stats.setSlotDefence(6, 1, 1);
				stats.setSlotDefence(6, 2, 2);
				stats.setSlotDefence(6, 3, 1);
				stats.setSlotDefence(6, 4, 0);
			}else if(feet == Items.CHAINMAIL_BOOTS) {
				stats.setSlotAccuracy(6, 3, -3);
				stats.setSlotAccuracy(6, 4, -1);
				stats.setSlotDefence(6, 0, 5);
				stats.setSlotDefence(6, 1, 6);
				stats.setSlotDefence(6, 2, 7);
			}else if(feet == Items.IRON_BOOTS) {
				stats.setSlotAccuracy(6, 3, -3);
				stats.setSlotAccuracy(6, 4, -1);
				stats.setSlotDefence(6, 0, 8);
				stats.setSlotDefence(6, 1, 9);
				stats.setSlotDefence(6, 2, 10);
			}else if(feet == Items.GOLDEN_BOOTS) {
				stats.setSlotAccuracy(6, 3, -3);
				stats.setSlotAccuracy(6, 4, -1);
				stats.setSlotDefence(6, 0, 10);
				stats.setSlotDefence(6, 1, 11);
				stats.setSlotDefence(6, 2, 12);
				stats.setSlotMeleeStrength(6, 1);
			}else if(feet == Items.DIAMOND_BOOTS) {
				stats.setSlotAccuracy(6, 3, -3);
				stats.setSlotAccuracy(6, 4, -1);
				stats.setSlotDefence(6, 0, 12);
				stats.setSlotDefence(6, 1, 13);
				stats.setSlotDefence(6, 2, 14);
				stats.setSlotMeleeStrength(6, 2);
			}else if(feet == Items.NETHERITE_BOOTS) {
				stats.setSlotAccuracy(6, 3, -3);
				stats.setSlotAccuracy(6, 4, -1);
				stats.setSlotDefence(6, 0, 16);
				stats.setSlotDefence(6, 1, 17);
				stats.setSlotDefence(6, 2, 18);
				stats.setSlotMeleeStrength(6, 4);
			}
		}
	}
}