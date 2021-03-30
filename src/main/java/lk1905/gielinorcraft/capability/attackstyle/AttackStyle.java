package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.combat.IAttackStyles;
import lk1905.gielinorcraft.network.AttackStyleCapPacket;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class AttackStyle implements IAttackStyle{

	private final IAttackStyles[] style;
	private IAttackStyles activeStyle;
	private final LivingEntity entity;
	
	private int activeId;
	
	public AttackStyle(LivingEntity entity) {
		style = new IAttackStyles[6];
		this.entity = entity;
		
		if(entity instanceof PlayerEntity) {
			style[0] = AttackStyles.ACCURATE_CRUSH;
			style[1] = AttackStyles.AGGRESSIVE_CRUSH;
			style[2] = AttackStyles.DEFENSIVE_CRUSH;
			style[3] = AttackStyles.EMPTY;
			style[4] = AttackStyles.EMPTY;
			style[5] = AttackStyles.EMPTY;
			
			setActiveStyle(0);
			
			if(activeStyle == AttackStyles.EMPTY) {
				setActiveStyle(0);
			}
		}
	}
	
	@Override
	public void setAttackStyle(int slot, IAttackStyles style) {
		this.style[slot] = style;
		
	}

	@Override
	public IAttackStyles getAttackStyle(int slot) {
		return style[slot];
	}

	@Override
	public void setActiveStyle(int slot) {
		activeStyle = style[slot];
		activeId = slot;	
	}

	@Override
	public IAttackStyles getActiveStyle() {
		return activeStyle;
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
	public void setStyleId(int slot, int id) {
		id = style[slot].getStyleId();
	}
	
	@Override
	public int getStyleId(int slot) {
		return style[slot].getStyleId();
	}
	
	@Override
	public int getActiveStyleId() {
		return activeId;
	}

	@Override
	public LivingEntity getEntity() {
		return entity;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 6; i++) {
			data.putString("style_name_" + i, getStyleName(i));
			data.putInt("style_id_" + i, getStyleId(i));
		}
		data.putInt("active_style", activeId);
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		for(int i = 0; i < 6; i++) {
			setStyleName(i, data.getString("style_name_" + i));
			setStyleId(i, data.getInt("style_id_" + i));
		}
		setActiveStyle(data.getInt("active_style"));
	}

	@Override
	public void sync(ServerPlayerEntity player) {
		if(entity instanceof ServerPlayerEntity) {
			PacketHandler.sendTo(new AttackStyleCapPacket(serializeNBT()), player);
		}
	}
}
