package com.jayms.treasurehunt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Util;
import com.jayms.treasurehunt.util.Vector2DInt;

public class TreasureGrid extends Grid {
	
	private int treasureChests;
	private int bandits;
	private Map<UUID, Entity> entities = new HashMap<>();
	private boolean initialized = false;
	
	public TreasureGrid(int rows, int columns, int treasureChests, int bandits) {
		super(rows, columns);
		this.treasureChests = treasureChests;
		this.bandits = bandits;
	}
	
	public Player initGame() {
		for (int i = 0; i < treasureChests; i++) {
			addEntity(new Chest());
		}
		for (int i = 0; i < bandits; i++) {
			addEntity(new Bandit());
		}
		Player p = new Player();
		addEntity(p);
		moveEntity(p, new Vector2DInt(0, 0));
		randomizeEntities();
		for (int i = 0; i < this.getRowCount(); i++) {
			for (int j = 0; j < this.getColumnCount(); j++) {
				GridSlot slot = this.getGridSlot(i, j);
				if (!slot.hasEntity()) {
					System.out.println("no entity");
					slot.empty();
				}
			}
		}
		initialized = true;
		return p;
	}
	
	public void addEntity(Entity entity) {
		if (onGrid(entity)) {
			return;
		}
		entities.put(entity.getUniqueID(), entity);
	}
	
	public void removeEntity(Entity entity) {
		if (onGrid(entity)) {
			entities.remove(entity.getUniqueID());
		}
	}

	public void randomizeEntities() {
		List<Vector2DInt> prevVecs = new ArrayList<>();
		ArrayList<Entity> ents = new ArrayList<>(entities.values());
		for (int i = 0; i < ents.size(); i++) {
			Entity e = ents.get(i);
			if (e instanceof Player) {
				ents.remove(e);
				continue;
			}
			int randomX = Util.random(0, this.getRowCount());
			int randomY = Util.random(0, this.getColumnCount());
			Vector2DInt vec = new Vector2DInt(randomX, randomY);
			if (Util.containsVector(prevVecs, vec)) {
				System.out.println("Contains: " + vec.toString());
				--i;
				continue;
			}
			prevVecs.add(vec);
			moveEntity(e, vec);
		}
	}
	
	public boolean onGrid(Entity entity) {
		return entities.containsKey(entity.getUniqueID());
	}
	
	public void moveEntity(Entity entity, Vector2DInt pos) {
		if (!onGrid(entity)) {
			return;
		}
		entity.getLocation().setTreasureGrid(this);
		entity.getLocation().setPosition(pos);
		GridSlot slot = this.getGridSlot(pos.getX(), pos.getY());
		Entity e = slot.getEntity();
		if (e instanceof Player) {
			return;
		}
		slot.setEntity(entity);
	}
	
	public boolean initialized() {
		return initialized;
	}
}
