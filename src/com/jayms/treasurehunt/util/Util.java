package com.jayms.treasurehunt.util;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class Util {

	public static int random(int minimum, int maximum) {
		return minimum + (int)(Math.random() * maximum); 
	}
	
	public static Image getImage(String path) {
		return new Image(Util.class.getResourceAsStream(path));
	}
	
	public static boolean containsVector(List<Vector2DInt> list, Vector2DInt v) {
		for (Vector2DInt vec : list) {
			if (vec.equals(v)) {
				return true;
			}
		}
		return false;
	}
	
	public static void fitImage(int width, int height, boolean preserveRatio, ImageView img) {
		img.setFitWidth(width);
		img.setFitHeight(height);
		img.preserveRatioProperty().set(preserveRatio);
	}
}
