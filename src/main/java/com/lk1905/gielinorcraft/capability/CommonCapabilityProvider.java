package com.lk1905.gielinorcraft.capability;

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
	
	public CommonCapabilityProvider(Capability<HANDLER> capability, Direction side, HANDLER instance) {
		
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
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		if(cap == getCapability()) {
			
			return getCapability().orEmpty(cap, lazyOptional);
		}
		
		return null;
	}
	
	public final Capability<HANDLER> getCapability(){
		return capability;
	}

	@Override
	public INBT serializeNBT() {
		// TODO Auto-generated method stub
		return getCapability().writeNBT(getInstance(), getFacing());
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		// TODO Auto-generated method stub
		getCapability().readNBT(getInstance(), getFacing(), nbt);
	}
	
	public Direction getFacing() {
		return side;
	}
	
	public HANDLER getInstance() {
		return instance;
	}
}
