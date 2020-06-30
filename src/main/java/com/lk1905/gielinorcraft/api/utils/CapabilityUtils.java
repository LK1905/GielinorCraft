package com.lk1905.gielinorcraft.api.utils;

import javax.annotation.Nullable;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityUtils {

	@Nullable
	public static <T> LazyOptional<T> getCapability(@Nullable final ICapabilityProvider provider, final Capability<T> cap, @Nullable final Direction side) {
		
		if(provider == null || cap == null) {
			
			return LazyOptional.empty();
		}
		
		return provider.getCapability(cap, side);
	}
}
