package com.jayms.treasurehunt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jayms.treasurehunt.util.Location;
import com.jayms.treasurehunt.util.Util;
import com.jayms.treasurehunt.util.Vector2DInt;

import javafx.scene.control.Label;

public class TreasureGrid extends Grid {
	
	private static final String BANDIT_FORMAT = "Bandits: %i";
	private static final String CHEST_FORMAT = "Treasure Chests: %i";
	private static final int CHEST_WORTH = 10;
	
	private Game game;
	private LabelIntBinder treasureChests;
	private LabelIntBinder bandits;
	private Map<UUID, Entity> entities = new HashMap<>();
	private boolean initialized = false;
	
	public TreasureGrid(int rows, int columns, int treasureChests, int bandits, Label treasureLabel, Label banditsLabel, Game game) {
		super(rows, columns);
		this.game = game;
		this.treasureChests = new LabelIntBinder(treasureChests, treasureLabel, CHEST_FORMAT);
		this.treasureChests.setInt(treasureChests);
		this.bandits = new LabelIntBinder(bandits, banditsLabel, BANDIT_FORMAT);
		this.bandits.setInt(bandits);
	}
	
	public Player initGame(Game game, Label coinLabel) {
		for (int i = 0; i < treasureChests.getInt(); i++) {
			addEntity(new Chest(CHEST_WORTH));
		}
		for (int i = 0; i < bandits.getInt(); i++) {
			addEntity(new Bandit());
		}
		Player p = new Player(game, coinLabel);
		addEntity(p);
		movePlayer(p, new Vector2DInt(0, 0));
		randomizeEntities();
		initialized = true;
		return p;
	}
	
	public Entity readyMovePlayer(Vector2DInt pos) {
		GridSlot slot = this.getGridSlot(pos.getX(), pos.getY());
		if (!slot.hasEntity()) {
			return null;
		}
		slot.cacheCurrentEntity();
		return slot.getEntity();
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
		if (e instanceof Player && !(entity instanceof Player)) {
			return;
		}
		slot.setEntity(entity);
		evalIntBinders();
	}
	
	public void movePlayer(Player p, Vector2DInt pos) {
		if (!onGrid(p)) {
			return;
		}
		Vector2DInt currentPos = p.getLocation().getPosition();
		GridSlot currentSlot = this.getGridSlot(currentPos.getX(), currentPos.getY());
		currentSlot.restoreCachedEntity();
		if (!currentSlot.cachedEntity() && !currentSlot.hasEntity()) {
			currentSlot.empty();
		}
		moveEntity(p, pos);
	}
	
	public void evalIntBinders() {
		int chests = 0;
		int bandits = 0;
		for (Entity e : entities.values()) {
			if (e instanceof Chest) {
				chests++;
			}
			if (e instanceof Bandit) {
				bandits++;
			}
		}
		this.treasureChests.setInt(chests);
		this.bandits.setInt(bandits);
		
		if (treasureChests.getInt() <= 0) {
			game.gameOver(false);
		}
	}
	
	public boolean initialized() {
		return initialized;
	}
}
