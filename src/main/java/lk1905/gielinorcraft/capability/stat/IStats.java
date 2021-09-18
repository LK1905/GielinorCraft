package lk1905.gielinorcraft.capability.stat;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IStats {
	
	/**Determine the accuracy value for the specified equipment slot and attack type.
	 * @param equipSlot The equipment slot.
	 * @param typeSlot The attack type.
	 * @param stat The accuracy value.*/
	void setSlotAccuracy(int equipSlot, int typeSlot, int stat);
	
	/**Determine the defence value for the specified equipment slot and attack type.
	 * @param equipSlot The equipment slot.
	 * @param typeSlot The attack type.
	 * @param stat The defence value.*/
	void setSlotDefence(int equipSlot, int typeSlot, int stat);
	
	/**Determine the melee strength value for the specified equipment slot.
	 * @param equipSlot The equipment slot.
	 * @param stat The melee strength value.*/
	void setSlotMeleeStrength(int equipSlot, int stat);
	
	/**Determine the ranged strength value for the specified equipment slot.
	 * @param equipSlot The equipment slot.
	 * @param stat The ranged strength value.*/
	void setSlotRangedStrength(int equipSlot, int stat);
	
	/**Determine the magic strength value for the specified equipment slot. Unlike all the other values, this value is a double, not an int.
	 * @param equipSlot The equipment slot.
	 * @param stat The magic strength value.*/
	void setSlotMagicStrength(int equipSlot, double stat);
	
	/**Gets the accuracy value of the the specified equipment slot and attack type.
	 * @param equipSlot The equipment slot.
	 * @param typeSlot The attack type.
	 * @return The accuracy value.*/
	int getSlotAccuracy(int equipSlot, int typeSlot);
	
	/**Gets the defence value of the the specified equipment slot and attack type.
	 * @param equipSlot The equipment slot.
	 * @param typeSlot The attack type.
	 * @return The defence value.*/
	int getSlotDefence(int equipSlot, int typeSlot);
	
	/**Gets the melee strength value of the the specified equipment slot.
	 * @param equipSlot The equipment slot.
	 * @return The melee strength value.*/
	int getSlotMeleeStrength(int equipSlot);
	
	/**Gets the ranged strength value of the the specified equipment slot.
	 * @param equipSlot The equipment slot.
	 * @return The ranged strength value.*/
	int getSlotRangedStrength(int equipSlot);
	
	/**Gets the magic strength value of the the specified equipment slot. Unlike all the other values, this value is a double, not an int.
	 * @param equipSlot The equipment slot.
	 * @return The magic strength value.*/
	double getSlotMagicStrength(int equipSlot);
	
	/**Gets the total accuracy of the specified attack type.
	 * @param typeSlot The attack stype.
	 * @return The total accuracy.*/
	int getTotalAccuracy(int typeSlot);
	
	/**Gets the total defence of the specified attack type.
	 * @param typeSlot The attack stype.
	 * @return The total defence.*/
	int getTotalDefence(int typeSlot);
	
	/**Gets the total melee strength.
	 * @return The total melee strength.*/
	int getTotalMeleeStrength();
	
	/**Gets the total ranged strength.
	 * @return The total ranged strength.*/
	int getTotalRangedStrength();
	
	/**Gets the total magic strength. Unlike all the other values, this value is a double, not an int.
	 * @return The total magic strength.*/
	double getTotalMagicStrength();
	
	/**Serializes the stat data into nbt tag format. Only the base equipment slot (equipslot = 0) needs to be serialized.
	 * @return The CompoundNBT representing the stat data.*/
	CompoundNBT serializeNBT();
	
	/**Deserializes the stat data from nbt tag format. Only the base equipment slot (equipslot = 0) needs to be deserialized.
	 * @param data The CompoundNBT representing the stat data.*/
	void deserializeNBT(CompoundNBT data);
	
	/**Syncs any changes to the stat data to the client.
	 * @param player The ServerPlayerEntity sending the skill data.*/
	void sync(ServerPlayerEntity player);
}
