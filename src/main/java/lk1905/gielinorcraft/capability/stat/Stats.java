package lk1905.gielinorcraft.capability.stat;

import lk1905.gielinorcraft.event.stat.*;
import lk1905.gielinorcraft.network.PacketHandler;
import lk1905.gielinorcraft.network.stat.StatsPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.MinecraftForge;

public class Stats implements IStats{

	/**The Equipment slots.*/
	public static final int BASE = 0, MAINHAND = 1, OFFHAND = 2, HEAD = 3, CHEST = 4, LEGS = 5, FEET = 6;
	
	/**The attack type slots.*/
	public static final int STAB = 0, SLASH = 1, CRUSH = 2, MAGIC = 3, RANGED = 4;
	
	private final int[][] accuracy;
	private final int[][] defence;
	
	private final int[] melee;
	private final int[] ranged;
	private final double[] magic;
	
	private LivingEntity entity;
	
	public Stats(LivingEntity entity) {
		this.entity = entity;
		this.accuracy = new int[7][5];
		this.defence = new int[7][5];
		this.melee = new int[7];
		this.ranged = new int[7];
		this.magic = new double[7];
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT data = new CompoundNBT();
		
		for(int i = 0; i < 5; i++) {
			data.putInt("accuracy_" + i, accuracy[0][i]);
			data.putInt("defence_" + i, defence[0][i]);
		}
		
		data.putInt("melee_strength", melee[0]);
		data.putInt("ranged_strength", ranged[0]);
		data.putDouble("magic_strength", magic[0]);
		
		return data;
	}

	@Override
	public void deserializeNBT(CompoundNBT data) {
		
		for(int i = 0; i < 5; i++) {
			accuracy[0][i] = data.getInt("accuracy_" + i);
			defence[0][i] = data.getInt("defence_" + i);
		}
		
		melee[0] = data.getInt("melee_strength");
		ranged[0] = data.getInt("ranged_strength");
		magic[0] = data.getDouble("magic_strength");
	}
	
	@Override
	public void sync(ServerPlayerEntity player) {
		if(entity instanceof ServerPlayerEntity) {
			PacketHandler.sendTo(new StatsPacket(serializeNBT()), player);
		}
	}



	@Override
	public void setSlotAccuracy(int equipSlot, int typeSlot, int stat) {
		accuracy[equipSlot][typeSlot] = stat;
		MinecraftForge.EVENT_BUS.post(new AccuracyEvent(equipSlot, typeSlot, stat, entity));
	}



	@Override
	public void setSlotDefence(int equipSlot, int typeSlot, int stat) {
		defence[equipSlot][typeSlot] = stat;
		MinecraftForge.EVENT_BUS.post(new DefenceEvent(equipSlot, typeSlot, stat, entity));
	}



	@Override
	public void setSlotMeleeStrength(int equipSlot, int stat) {
		melee[equipSlot] = stat;
		MinecraftForge.EVENT_BUS.post(new MeleeEvent(equipSlot, stat, entity));
	}



	@Override
	public void setSlotRangedStrength(int equipSlot, int stat) {
		ranged[equipSlot] = stat;
		MinecraftForge.EVENT_BUS.post(new RangedEvent(equipSlot, stat, entity));
	}



	@Override
	public void setSlotMagicStrength(int equipSlot, double stat) {
		magic[equipSlot] = stat;
		MinecraftForge.EVENT_BUS.post(new MagicEvent(equipSlot, stat, entity));
	}



	@Override
	public int getSlotAccuracy(int equipSlot, int typeSlot) {
		return accuracy[equipSlot][typeSlot];
	}



	@Override
	public int getSlotDefence(int equipSlot, int typeSlot) {
		return defence[equipSlot][typeSlot];
	}



	@Override
	public int getSlotMeleeStrength(int equipSlot) {
		return melee[equipSlot];
	}



	@Override
	public int getSlotRangedStrength(int equipSlot) {
		return ranged[equipSlot];
	}



	@Override
	public double getSlotMagicStrength(int equipSlot) {
		return magic[equipSlot];
	}



	@Override
	public int getTotalAccuracy(int typeSlot) {
		int total = 0;
		for(int i = 0; i < 7; i++) {
			total += accuracy[i][typeSlot];
		}
		return total;
	}



	@Override
	public int getTotalDefence(int typeSlot) {
		int total = 0;
		for(int i = 0; i < 7; i++) {
			total += defence[i][typeSlot];
		}
		return total;
	}



	@Override
	public int getTotalMeleeStrength() {
		int total = 0;
		for(int i = 0; i < 7; i++) {
			total += melee[i];
		}
		return total;
	}



	@Override
	public int getTotalRangedStrength() {
		int total = 0;
		for(int i = 0; i < 7; i++) {
			total += ranged[i];
		}
		return total;
	}



	@Override
	public double getTotalMagicStrength() {
		double total = 0;
		for(int i = 0; i < 7; i++) {
			total += magic[i];
		}
		return total;
	}
}