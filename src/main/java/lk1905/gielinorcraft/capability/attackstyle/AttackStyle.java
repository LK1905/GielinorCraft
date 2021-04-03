package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.api.combat.AttackStyles;
import lk1905.gielinorcraft.api.combat.IAttackStyles;
import lk1905.gielinorcraft.network.AttackStyleCapPacket;
import lk1905.gielinorcraft.network.PacketHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;

public class AttackStyle implements IAttackStyle{

	private final IAttackStyles[] style;
	private IAttackStyles activeStyle;
	private final LivingEntity entity;
	private int activeId;
	
	public AttackStyle(LivingEntity entity) {
		style = new IAttackStyles[6];
		this.entity = entity;
		
		//NPE on below line, will deal with another time.
		/*if(entity.getHeldItemMainhand().isEmpty() == false) {
			Item heldItem = entity.getHeldItemMainhand().getItem();
		
			if(heldItem instanceof SwordItem) {
				style[0] = AttackStyles.ACCURATE_STAB;
				style[1] = AttackStyles.AGGRESSIVE_STAB;
				style[2] = AttackStyles.AGGRESSIVE_SLASH;
				style[3] = AttackStyles.DEFENSIVE_STAB;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else if(heldItem instanceof AxeItem) {
				style[0] = AttackStyles.ACCURATE_SLASH;
				style[1] = AttackStyles.AGGRESSIVE_SLASH;
				style[2] = AttackStyles.AGGRESSIVE_CRUSH;
				style[3] = AttackStyles.DEFENSIVE_SLASH;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else if(heldItem instanceof PickaxeItem) {
				style[0] = AttackStyles.ACCURATE_STAB;
				style[1] = AttackStyles.AGGRESSIVE_STAB;
				style[2] = AttackStyles.AGGRESSIVE_CRUSH;
				style[3] = AttackStyles.DEFENSIVE_STAB;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else if(heldItem instanceof ShovelItem) {
				style[0] = AttackStyles.ACCURATE_CRUSH;
				style[1] = AttackStyles.AGGRESSIVE_CRUSH;
				style[2] = AttackStyles.AGGRESSIVE_SLASH;
				style[3] = AttackStyles.DEFENSIVE_CRUSH;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else if(heldItem instanceof HoeItem) {
				style[0] = AttackStyles.ACCURATE_SLASH;
				style[1] = AttackStyles.AGGRESSIVE_SLASH;
				style[2] = AttackStyles.AGGRESSIVE_STAB;
				style[3] = AttackStyles.DEFENSIVE_SLASH;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else if(heldItem instanceof BowItem || heldItem instanceof CrossbowItem) {
				style[0] = AttackStyles.RANGED_ACCURATE;
				style[1] = AttackStyles.RANGED_RAPID;
				style[2] = AttackStyles.RANGED_LONG;
				style[3] = AttackStyles.EMPTY;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}else {
				style[0] = AttackStyles.ACCURATE_CRUSH;
				style[1] = AttackStyles.AGGRESSIVE_CRUSH;
				style[2] = AttackStyles.DEFENSIVE_CRUSH;
				style[3] = AttackStyles.EMPTY;
				style[4] = AttackStyles.EMPTY;
				style[5] = AttackStyles.EMPTY;
			}
		}else {*/
			style[0] = AttackStyles.ACCURATE_CRUSH;
			style[1] = AttackStyles.AGGRESSIVE_CRUSH;
			style[2] = AttackStyles.DEFENSIVE_CRUSH;
			style[3] = AttackStyles.EMPTY;
			style[4] = AttackStyles.EMPTY;
			style[5] = AttackStyles.EMPTY;
		//}
		setActiveStyle(0);
			
		if(activeStyle == AttackStyles.EMPTY) {
			setActiveStyle(0);
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
			data.putString("style_descript_" + i, getStyleDescription(i));
			data.putInt("style_id_" + i, getStyleId(i));
		}
		data.putInt("active_style", activeId);
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		for(int i = 0; i < 6; i++) {
			setStyleName(i, data.getString("style_name_" + i));
			setStyleDescription(i, data.getString("style_descript_" + i));
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
