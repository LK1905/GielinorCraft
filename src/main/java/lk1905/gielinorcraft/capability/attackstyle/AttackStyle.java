package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.combat.IAttackStyles;
import lk1905.gielinorcraft.api.events.AttackStyleEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleCapPacket;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleServerPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.MinecraftForge;

public class AttackStyle implements IAttackStyle{

	private final IAttackStyles[] style;
	private final LivingEntity entity;
	private int activeId;
	
	public AttackStyle(LivingEntity entity) {
		style = new IAttackStyles[6];
		this.entity = entity;
		
		style[0] = AttackStyles.ACCURATE_CRUSH;
		style[1] = AttackStyles.AGGRESSIVE_CRUSH;
		style[2] = AttackStyles.DEFENSIVE_CRUSH;
		style[3] = AttackStyles.EMPTY;
		style[4] = AttackStyles.EMPTY;
		style[5] = AttackStyles.EMPTY;
		
		setActiveSlot(0);
			
		if(style[activeId] == AttackStyles.EMPTY) {
			setActiveSlot(0);
		}
	}
	
	@Override
	public void setAttackStyle(int slot, IAttackStyles style) {
		if(this.style[slot] != style) {
			this.style[slot] = style;
			MinecraftForge.EVENT_BUS.post(new AttackStyleEvent(entity, slot, style));
		}
	}

	@Override
	public IAttackStyles getAttackStyle(int slot) {
		return style[slot];
	}

	@Override
	public void setActiveSlot(int slot) {
		activeId = slot;	
	}

	@Override
	public IAttackStyles getActiveStyle() {
		return style[activeId];
	}
	
	@Override
	public void setStyleName(int slot, String name) {
		name = style[slot].getName();
	}
	
	@Override
	public String getStyleName(int slot) {
		return style[slot].getName();
	}
	
	@Override
	public void setStyleDescription(int slot, String description) {
		description = style[slot].getDescription();
	}
	
	@Override
	public String getStyleDescription(int slot) {
		return style[slot].getDescription();
	}
	
	@Override
	public void setStyleId(int slot, int id) {
		id = style[slot].getStyleId();
	}
	
	@Override
	public int getStyleId(int slot) {
		return style[slot].getStyleId();
	}
	
	@Override
	public int getActiveSlot() {
		return activeId;
	}

	@Override
	public LivingEntity getEntity() {
		return entity;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		data.putInt("active_style", activeId);
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		setActiveSlot(data.getInt("active_style"));
	}

	@Override
	public void sync(ServerPlayerEntity player) {
		if(entity instanceof ServerPlayerEntity) {
			PacketHandler.sendTo(new AttackStyleCapPacket(serializeNBT()), player);
			PacketHandler.sendTo(new AttackStyleServerPacket(activeId), player);
		}
	}
}
