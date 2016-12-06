package com.jayms.treasurehunt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jayms.treasurehunt.util.Util;
import com.jayms.treasurehunt.util.Vector2DInt;

public class TreasureGrid extends Grid {

	private int treasureChests;
	private int bandits;
	private Map<UUID, Entity> entities = new HashMap<>();
	
	public TreasureGrid(int rows, int columns, int treasureChests, int bandits) {
		super(rows, columns);
		this.treasureChests = treasureChests;
		this.bandits = bandits;
	}
	
	

	public void randomizeEntities() {
		int randomX = Util.random(0, this.getRowCount());
		int randomY = Util.random(0, this.getColumnCount());
		
	}
	
	public boolean onGrid(Entity entity) {
		return entities.containsKey(entity.getUniqueID());
	}
	
	public void moveEntity(Entity entity, Vector2DInt pos) {
		if (!onGrid(entity)) {
			return;
		}
	}
}
