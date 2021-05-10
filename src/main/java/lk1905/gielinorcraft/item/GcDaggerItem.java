package lk1905.gielinorcraft.item;

import lk1905.gielinorcraft.Gielinorcraft;
import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.stats.IStats;
import lk1905.gielinorcraft.capability.attackstyle.AttackStyleCapability;
import lk1905.gielinorcraft.capability.attackstyle.IAttackStyle;
import lk1905.gielinorcraft.capability.stats.StatCapability;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.StringPacket;
import lk1905.gielinorcraft.network.stat.AccuracyPacket;
import lk1905.gielinorcraft.network.stat.MeleeStrengthPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Gielinorcraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GcDaggerItem extends SwordItem{
	
	private int stab;
	private int slash;
	private int strength;
	
	public GcDaggerItem(IItemTier tier, int stab, int slash, int strength, Properties builderIn) {
		super(tier, 0, -2.9F, builderIn);
		this.stab = stab;
		this.slash = slash;
		this.strength = strength;
	}

	@SubscribeEvent
	public void onWield(LivingEquipmentChangeEvent event) {
		LivingEntity entity = event.getEntityLiving();
		IStats stat = entity.getCapability(StatCapability.STAT_CAP).orElse(null);
		IAttackStyle style = entity.getCapability(AttackStyleCapability.STYLE_CAP).orElse(null);
		
		if(this == event.getTo().getItem()) {
			stat.setAccuracy(0, stat.getAccuracy(0) + stab);
			stat.setAccuracy(1, stat.getAccuracy(1) + slash);
			stat.setMeleeStrength(stat.getMeleeStrength() + strength);
			style.setAttackStyle(0, AttackStyles.ACCURATE_STAB);
			style.setAttackStyle(1, AttackStyles.AGGRESSIVE_STAB);
			style.setAttackStyle(2, AttackStyles.AGGRESSIVE_SLASH);
			style.setAttackStyle(3, AttackStyles.DEFENSIVE_STAB);
			style.setAttackStyle(4, AttackStyles.EMPTY);
			style.setAttackStyle(5, AttackStyles.EMPTY);
			
			if(entity instanceof ServerPlayerEntity) {
				PacketHandler.sendTo(new AccuracyPacket(0, stab), (ServerPlayerEntity) entity);
				PacketHandler.sendTo(new AccuracyPacket(1, slash), (ServerPlayerEntity) entity);
				PacketHandler.sendTo(new MeleeStrengthPacket(strength), (ServerPlayerEntity) entity);
				stat.sync((ServerPlayerEntity) entity);
				style.sync((ServerPlayerEntity) entity);
			}
			
			if(event.getSlot() == EquipmentSlotType.OFFHAND) {
				event.setCanceled(true);
				if(entity instanceof ServerPlayerEntity) {
					PacketHandler.sendTo(new StringPacket("You cannot wield this item in your offhand."), (ServerPlayerEntity) entity);
				}
			}
		}
		
		if(this == event.getFrom().getItem()) {
			stat.setAccuracy(0, stat.getAccuracy(0) - stab);
			stat.setAccuracy(1, stat.getAccuracy(1) - slash);
			stat.setMeleeStrength(stat.getMeleeStrength() - strength);
			style.setAttackStyle(0, AttackStyles.ACCURATE_CRUSH);
			style.setAttackStyle(1, AttackStyles.AGGRESSIVE_CRUSH);
			style.setAttackStyle(2, AttackStyles.DEFENSIVE_CRUSH);
			style.setAttackStyle(3, AttackStyles.EMPTY);
			style.setAttackStyle(4, AttackStyles.EMPTY);
			style.setAttackStyle(5, AttackStyles.EMPTY);
			
			if(entity instanceof ServerPlayerEntity) {
				PacketHandler.sendTo(new AccuracyPacket(0, -stab), (ServerPlayerEntity) entity);
				PacketHandler.sendTo(new AccuracyPacket(1, -slash), (ServerPlayerEntity) entity);
				PacketHandler.sendTo(new MeleeStrengthPacket(-strength), (ServerPlayerEntity) entity);
				stat.sync((ServerPlayerEntity) entity);
				style.sync((ServerPlayerEntity) entity);
			}
		}
	}
}