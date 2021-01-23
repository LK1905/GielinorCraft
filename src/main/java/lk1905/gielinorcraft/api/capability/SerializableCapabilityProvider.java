package lk1905.gielinorcraft.api.capability;

import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
/**
 * An implementation of {@link ICapabilitySerializable}, that supports a single {@link Capability} handler instance.
 * <p>
 * Mostly copied from @author Choonster, here:
 * <p>
 * {@link https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.16.x/src/main/java/choonster/testmod3/capability/SimpleCapabilityProvider.java}
 * {@link https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.16.x/src/main/java/choonster/testmod3/capability/SerializableCapabilityProvider.java}
 * */
public class SerializableCapabilityProvider<HANDLER> implements ICapabilitySerializable<INBT>{

	/**The {@link Capability} instance to provide the handler for.*/
	protected final Capability<HANDLER> capability;
	
	/**The {@link Direction} to provide the handler for.*/
	protected final Direction side;
	
	/**The handler instance to provide.*/
	protected final HANDLER instance;
	
	/**A lazy optional containing the handler instance to provide.*/
	protected final LazyOptional<HANDLER> lo;
	
	/**
	 * Creates a provider for the default handler instance.
	 * @param capability 	The Capability instance to provide the handler for.
	 * @param side 			The Direction to provide the handler for.
	 * */
	public SerializableCapabilityProvider(final Capability<HANDLER> capability, @Nullable final Direction side) {
		this(capability, side, capability.getDefaultInstance());
	}
	
	/**
	 * Creates a provider for the specified handler instance.
	 * @param capability 	The capability to provide the handler for.
	 * @param side 			The Direction to provide the handler for.
	 * @param instance		The handler instance to provide.
	 * */
	public SerializableCapabilityProvider(final Capability<HANDLER> capability, @Nullable final Direction side, @Nullable final HANDLER instance) {
		super();
		this.capability = capability;
		this.side = side;
		this.instance = instance;
		
		if(this.instance != null) {
			lo = LazyOptional.of(() -> this.instance);
		}else {
			lo = LazyOptional.empty();
		}
	}
	
	/**
	 * Retrieves the handler for the capability requested on the specific side.
	 * The return value CAN be null if the object does not support the capability.
	 * The return value CAN be the same for multiple faces.
	 *
	 * @param capability 	The capability to check
	 * @param side     		The Side to check from. CAN BE NULL. Null is defined to represent 'internal' or 'self'
	 * @return A lazy optional containing the handler, if this object supports the capability.
	 */
	@Override
	public <T> LazyOptional<T> getCapability(final Capability<T> capability, @Nullable final Direction side) {
		return getCapability().orEmpty(capability, lo);
	}
	
	/**
	 * Gets the {@link Capability} instance to provide the handler for.
	 * @return The Capability instance.
	 * */
	public final Capability<HANDLER> getCapability(){
		return capability;
	}
	
	/**
	 * Gets the {@link Direction} to provide the handler for.
	 * @return The Direction to provide the handler for.
	 * */
	@Nullable
	public Direction getSide() {
		return side;
	}
	
	/**
	 * Gets the handler instance.
	 * @return A lazy optioanl containing the handler instance.
	 * */
	@Nullable
	public final HANDLER getInstance() {
		return instance;
	}
	
	@Nullable
	@Override
	public INBT serializeNBT() {
		final HANDLER instance = getInstance();
		
		if(instance == null) {
			return null;
		}
		return getCapability().writeNBT(instance, getSide());
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		final HANDLER instance = getInstance();
		
		if(instance == null) {
			return;
		}
		getCapability().readNBT(instance, getSide(), nbt);
	}

}
