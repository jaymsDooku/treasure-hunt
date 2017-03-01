package com.jayms.treasurehunt;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;

public class Player extends Entity {
	
	private static final String COIN_FORMAT = "Coins: %i";
	
	private LabelIntBinder coins;
	private Game game;
	
	public Player(Game game, Label label) {
		super(new ImageView(Util.getImage("/resources/img/player.jpg")));
		coins = new LabelIntBinder(0, label, COIN_FORMAT);
		this.game = game;
	}
	
	public void addCoins(int coin) {
		coins.setInt(coins.getInt() + coin);
		if (coins.getInt() >= 100) {
			game.gameOver(true);
		}
	}
	
	public void resetCoins() {
		coins.setInt(0);
	}

	@Override
	public void onLand(Player p) {
	}
}

