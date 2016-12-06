package com.jayms.treasurehunt.util;

import javafx.scene.image.Image;

public final class Util {

	public static int random(int minimum, int maximum) {
		return minimum + (int)(Math.random() * maximum); 
	}
	
	public static Image getImage(String path) {
		return new Image(Util.class.getResourceAsStream(path));
	}
}
