package com.jayms.treasurehunt;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;

public class Chest extends Entity {
	
	private int worth;
	private int visitCount = 0;
	
	public Chest(int worth) {
		super(new ImageView(Util.getImage("/resources/img/treasure_chest_gold.png")));
		this.worth = worth;
	}
	
	public int getWorth() {
		return worth;
	}
	
	public boolean visit() {
		visitCount++;
		if (visitCount >= 3) {
			return true;
		}
		return false;
	}
}
