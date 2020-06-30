package com.lk1905.gielinorcraft.api.utils;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.minecraftforge.common.util.LazyOptional;

public class OptionalUtils {

	public static <T> Optional<T> toOptional(@Nonnull LazyOptional<T> lazyOptional){
		
		if(lazyOptional.isPresent()) {
			
			return Optional.of(lazyOptional.orElseThrow(() -> new RuntimeException("Failed to retreive value of lazy optiona.")));
		}
		
		return Optional.empty();
	}
}
