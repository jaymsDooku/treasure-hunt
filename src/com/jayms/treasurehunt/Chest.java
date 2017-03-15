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
		return visitCount > 3;
	}

	@Override
	public void onLand(Player p) {
		if (!visit()) {
			p.addCoins(getWorth());
			if (visitCount == 3) {
				TreasureGrid grid = loc.getTreasureGrid();
				Bandit bandit = new Bandit();
				grid.addEntity(bandit);
				grid.getGridSlot(loc.getPosition().getX(), loc.getPosition().getY()).setCachedEntity(bandit);
				grid.removeEntity(this);
			}
		}
	}
}
