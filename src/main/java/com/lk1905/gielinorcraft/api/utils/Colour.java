package com.lk1905.gielinorcraft.api.utils;

public class Colour {

	private final int red;
	private final int green;
	private final int blue;
	private final int alpha;
	
	public Colour(int newRed, int newGreen, int newBlue, int newAlpha) {
		
		red = newRed;
		green = newGreen;
		blue = newBlue;
		alpha = newAlpha;
	}
	
	public int getIntValue() {
		
		return (red << 16) | (green << 8) | (blue) | (alpha << 24);
	}
}
