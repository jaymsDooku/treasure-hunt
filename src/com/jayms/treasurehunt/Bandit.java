package com.jayms.treasurehunt;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;

public class Bandit extends Entity {

	public Bandit() {
		super(new ImageView(Util.getImage("/resources/img/bandit.jpg")));
	}

}
