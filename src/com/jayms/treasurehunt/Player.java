package com.jayms.treasurehunt;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;

public class Player extends Entity {
	
	private static final String COIN_FORMAT = "Coins: %i";
	
	private LabelIntBinder coins;
	
	public Player(Label label) {
		super(new ImageView(Util.getImage("/resources/img/player.jpg")));
		coins = new LabelIntBinder(0, label, COIN_FORMAT);
	}
	
	public void addCoins(int coin) {
		coins.setInt(coins.getInt() + coin);
	}
	
	public void resetCoins() {
		coins.setInt(0);
	}
}

