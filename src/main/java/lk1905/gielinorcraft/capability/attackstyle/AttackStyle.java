package lk1905.gielinorcraft.capability.attackstyle;

import lk1905.gielinorcraft.event.AttackStyleEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleCapPacket;
import lk1905.gielinorcraft.network.attackstyle.AttackStyleServerPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;

public class AttackStyle implements IAttackStyle {

	public static final int
		EMPTY = 0,
		ACCURATE_STAB = 1,
		ACCURATE_SLASH = 2,
		ACCURATE_CRUSH = 3,
		AGGRESSIVE_STAB = 4,
		AGGRESSIVE_SLASH = 5,
		AGGRESSIVE_CRUSH = 6,
		CONTROLLED_STAB = 7,
		CONTROLLED_SLASH = 8,
		CONTROLLED_CRUSH = 9,
		DEFENSIVE_STAB = 10,
		DEFENSIVE_SLASH = 11,
		DEFENSIVE_CRUSH = 12,
		RANGED_ACCURATE = 13,
		RANGED_RAPID = 14,
		RANGED_LONG = 15,
		SPELL_CAST = 16,
		SPELL_DEFENSIVE = 17;
	
	public static final	String[] STYLE_TYPE = {
			"Empty",
			"Accurate",
			"Accurate",
			"Accurate",
			"Aggressive",
			"Aggressive",
			"Aggressive",
			"Controlled",
			"Controlled",
			"Controlled",
			"Defensive",
			"Defensive",
			"Defensive",
			"Accurate",
			"Rapid",
			"Long Range",
			"Standard,",
			"Defensive"
		};
	
	public static final	String[] STYLE_NAME = {
		"Empty",
		"Stab",
		"Chop",
		"Pound",
		"Lunge",
		"Slash",
		"Smash",
		"Lunge",
		"Swipe",
		"Pound",
		"Block",
		"Block",
		"Block",
		"Accurate",
		"Rapid",
		"Long Range",
		"Standard,",
		"Defensive"
	};
	
	public static final String[] STYLE_DESCRIPT = {
		"Empty",
		"Stab - Attack xp",
		"Slash - Attack xp",
		"Crush - Attack xp",
		"Stab - Strength xp",
		"Slash - Strength xp",
		"Crush - Strength xp",
		"Stab - Split Attack/Defence/Strength xp",
		"Slash - Split Attack/Defence/Strength xp",
		"Crush - Split Attack/Defence/Strength xp",
		"Stab - Defence xp",
		"Slash - Defence xp",
		"Crush - Defence xp",
		"Level bonus - Ranged xp",
		"Speed bonus - Ranged xp",
		"Range bonus - Split Ranged/Defence xp",
		"Standard cast - Magic xp",
		"Defensive cast - Split Magic/Defence xp"
	};
	
	private final int[] styleSlot;
	private final LivingEntity entity;
	private int activeSlot;
	
	public AttackStyle(LivingEntity entity) {
		styleSlot = new int[6];
		this.entity = entity;
		
		styleSlot[0] = ACCURATE_CRUSH;
		styleSlot[1] = AGGRESSIVE_CRUSH;
		styleSlot[2] = DEFENSIVE_CRUSH;
		styleSlot[3] = EMPTY;
		styleSlot[4] = EMPTY;
		styleSlot[5] = EMPTY;
		
		setActiveSlot(0);
			
		if(styleSlot[activeSlot] == EMPTY) {
			setActiveSlot(0);
		}
	}
	
	@Override
	public void setAttackStyle(int slot, int style) {
		if(this.styleSlot[slot] != style) {
			this.styleSlot[slot] = style;
			MinecraftForge.EVENT_BUS.post(new AttackStyleEvent(entity, slot, style));
		}
	}

	@Override
	public int getAttackStyle(int slot) {
		return styleSlot[slot];
	}

	@Override
	public void setActiveSlot(int slot) {
		activeSlot = slot;	
	}

	@Override
	public int getActiveStyle() {
		return styleSlot[activeSlot];
	}
	
	@Override
	public String getStyleType(int slot) {
		return STYLE_TYPE[styleSlot[slot]];
	}
	
	@Override
	public String getStyleName(int slot) {
		return STYLE_NAME[styleSlot[slot]];
	}
	
	@Override
	public String getStyleDescription(int slot) {
		return STYLE_DESCRIPT[styleSlot[slot]];
	}
	
	@Override
	public int getActiveSlot() {
		return activeSlot;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag data = new CompoundTag();
		data.putInt("active_style", activeSlot);
		return data;
	}

	@Override
	public void deserializeNBT(CompoundTag data) {
		setActiveSlot(data.getInt("active_style"));
	}

	@Override
	public void sync(ServerPlayer player) {
		if(entity instanceof ServerPlayer) {
			PacketHandler.sendTo(new AttackStyleCapPacket(serializeNBT()), player);
			PacketHandler.sendTo(new AttackStyleServerPacket(activeSlot), player);
		}
	}
}
