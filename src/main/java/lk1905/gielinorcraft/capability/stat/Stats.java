package lk1905.gielinorcraft.capability.stat;

import lk1905.gielinorcraft.event.stat.AccuracyEvent;
import lk1905.gielinorcraft.event.stat.DefenceEvent;
import lk1905.gielinorcraft.event.stat.MagicEvent;
import lk1905.gielinorcraft.event.stat.MeleeEvent;
import lk1905.gielinorcraft.event.stat.RangedEvent;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.stat.StatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.MinecraftForge;

public class Stats implements IStats{

	public static final int STAB = 0, SLASH = 1, CRUSH = 2, MAGIC = 3, RANGED = 4;
	
	private final int[] accuracy = new int[5];
	private final int[] defence = new int[5];
	
	private int MELEE_STRENGTH = 0;
	private int RANGED_STRENGTH = 0;
	private double MAGIC_STRENGTH = 0;
	
	private LivingEntity entity;
	
	public Stats(LivingEntity entity) {
		this.entity = entity;
	}
	
	@Override
	public void setAccuracy(int slot, int stat) {
		accuracy[slot] = stat;
	}
	
	@Override
	public void setDefence(int slot, int stat) {
		defence[slot] = stat;
	}
	
	@Override
	public void setMeleeStrength(int stat) {
		stat = MELEE_STRENGTH;	
	}

	@Override
	public void setRangedStrength(int stat) {
		stat = RANGED_STRENGTH;	
	}

	@Override
	public void setMagicStrength(double stat) {
		stat = MAGIC_STRENGTH;	
	}
	
	@Override
	public int getAccuracy(int slot) {
		return accuracy[slot];
	}
	
	@Override
	public int getDefence(int slot) {
		return defence[slot];
	}

	@Override
	public int getMeleeStrength() {
		return MELEE_STRENGTH;
	}

	@Override
	public int getRangedStrength() {
		return RANGED_STRENGTH;
	}

	@Override
	public double getMagicStrength() {
		return MAGIC_STRENGTH;
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 5; i++) {
			data.putInt("accuracy_" + i, accuracy[i]);
			data.putInt("defence_" + i, defence[i]);
		}
		
		data.putInt("melee_strength", MELEE_STRENGTH);
		data.putInt("ranged_strength", RANGED_STRENGTH);
		data.putDouble("magic_strength", MAGIC_STRENGTH);
		
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		for(int i = 0; i < 5; i++) {
			accuracy[i] = data.getInt("accuracy_" + i);
			defence[i] = data.getInt("defence_" + i);
		}
		
		MELEE_STRENGTH = data.getInt("melee_strength");
		RANGED_STRENGTH = data.getInt("ranged_strength");
		MAGIC_STRENGTH = data.getDouble("magic_strength");
	}
	
	@Override
	public void sync(ServerPlayerEntity player) {
		PacketHandler.sendTo(new StatsPacket(serializeNBT()), player);
	}

	@Override
	public void addAccuracy(int slot, int value) {
		MinecraftForge.EVENT_BUS.post(new AccuracyEvent(slot, value, this.entity));
	}

	@Override
	public void removeAccuracy(int slot, int value) {
		addAccuracy(slot, -value);
	}
	
	@Override
	public void addDefence(int slot, int value) {
		MinecraftForge.EVENT_BUS.post(new DefenceEvent(slot, value, this.entity));
	}

	@Override
	public void removeDefence(int slot, int value) {
		addAccuracy(slot, -value);
	}

	@Override
	public void addMeleeStrength(int value) {
		MinecraftForge.EVENT_BUS.post(new MeleeEvent(value, this.entity));
	}

	@Override
	public void removeMeleeStrength(int value) {
		addMeleeStrength(-value);
	}

	@Override
	public void addRangedStrength(int value) {
		MinecraftForge.EVENT_BUS.post(new RangedEvent(value, this.entity));
	}

	@Override
	public void removeRangedStrength(int value) {
		addRangedStrength(-value);
	}

	@Override
	public void addMagicStrength(double value) {
		MinecraftForge.EVENT_BUS.post(new MagicEvent(value, this.entity));
	}

	@Override
	public void removeMagicStrength(double value) {
		addMagicStrength(-value);
	}
}
