package com.jayms.treasurehunt;

import com.jayms.treasurehunt.util.Util;

import javafx.scene.image.ImageView;

public class Player extends Entity {
	
	public Player() {
		super(new ImageView(Util.getImage("/resources/img/player.jpg")));
	}
}
