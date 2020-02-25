package com.lk1905.gielinorcraft.capability;

import javax.annotation.Nullable;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CommonCapabilityProvider<HANDLER> implements ICapabilitySerializable<INBT>{

	protected final Capability<HANDLER> capability;
	protected final Direction side;
	protected final HANDLER instance;
	
	protected final LazyOptional<HANDLER> lazyOptional;
	
	public CommonCapabilityProvider(final Capability<HANDLER> capability, @Nullable final Direction side, @Nullable final HANDLER instance) {
		
		this.capability = capability;
		this.side = side;
		this.instance = instance;
		
		if(this.instance != null) {
			
			lazyOptional = LazyOptional.of(() -> this.instance);
			
		}else {
			
			lazyOptional = LazyOptional.empty();
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(final Capability<T> cap, @Nullable final Direction side) {
		
		return capability.orEmpty(cap, lazyOptional);
	}

	@Nullable
	@Override
	public INBT serializeNBT() {

		return capability.writeNBT(getInstance(), getFacing());//<-- THIS LINE
	}

	@Override
	public void deserializeNBT(INBT nbt) {

		capability.readNBT(getInstance(), getFacing(), nbt);
	}
	
	@Nullable
	public Direction getFacing() {
		return side;
	}
	
	@Nullable
	public HANDLER getInstance() {
		return instance;
	}
}
