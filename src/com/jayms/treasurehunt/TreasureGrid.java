package com.jayms.treasurehunt;

public class TreasureGrid extends Grid {

	private int treasureChests;
	private int bandits;
	
	public TreasureGrid(int rows, int columns, int treasureChests, int bandits) {
		super(rows, columns);
		this.treasureChests = treasureChests;
		this.bandits = bandits;
	}

}
