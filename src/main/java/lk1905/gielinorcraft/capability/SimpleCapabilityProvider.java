package lk1905.gielinorcraft.capability;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

/**
 * A simple implementation of {@link ICapabilityProvider} that supports a single {@link Capability} handler instance.
 *
 * @author Choonster<p>
 * {@link https://github.com/Choonster-Minecraft-Mods/TestMod3/blob/1.17.x/src/main/java/choonster/testmod3/capability/SimpleCapabilityProvider.java}
 */
public class SimpleCapabilityProvider<HANDLER> implements ICapabilityProvider{

	/**
	 * The {@link Capability} instance to provide the handler for.
	 */
	protected final Capability<HANDLER> capability;

	/**
	 * The {@link Direction} to provide the handler for.
	 */
	protected final Direction facing;

	/**
	 * The handler instance to provide.
	 */
	protected final HANDLER instance;

	/**
	 * A lazy optional containing handler instance to provide.
	 */
	protected final LazyOptional<HANDLER> lazyOptional;

	public SimpleCapabilityProvider(final Capability<HANDLER> capability, @Nullable final Direction facing, final HANDLER instance) {
		this.capability = Preconditions.checkNotNull(capability, "capability");
		this.facing = facing;
		this.instance = Preconditions.checkNotNull(instance, "instance");

		lazyOptional = LazyOptional.of(() -> this.instance);
	}

	@Override
	public <T> LazyOptional<T> getCapability(final Capability<T> capability, @Nullable final Direction facing) {
		return getCapability().orEmpty(capability, lazyOptional);
	}

	/**
	 * Get the {@link Capability} instance to provide the handler for.
	 *
	 * @return The Capability instance
	 */
	public final Capability<HANDLER> getCapability() {
		return capability;
	}

	/**
	 * Get the {@link Direction} to provide the handler for.
	 *
	 * @return The Direction to provide the handler for
	 */
	@Nullable
	public Direction getFacing() {
		return facing;
	}

	/**
	 * Get the handler instance.
	 *
	 * @return A lazy optional containing the handler instance
	 */
	public final HANDLER getInstance() {
		return instance;
	}
}
