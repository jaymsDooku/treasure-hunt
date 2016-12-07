package com.jayms.treasurehunt;

import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;

public class Chest extends Entity {

	public Chest() {
		super(new ImageView(Util.getImage("/resources/img/treasure_chest_gold.png")));
	}
}
