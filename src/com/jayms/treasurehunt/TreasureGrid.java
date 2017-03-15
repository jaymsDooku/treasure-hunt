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
	
	/**
	 * Initializes the state of the game Treasure Hunt game.
	 * Creates all of the pre-determined entities and player character, and invokes the @see com.jayms.treasurehunt.TreasureGrid.randomizeEntities 
	 * @param game
	 * @param coinLabel
	 * @return
	 */
	public Player initGame(Game game, Label coinLabel) {
		for (int i = 0; i < treasureChests.getInt(); i++) {
			addEntity(new Chest(CHEST_WORTH));
		}
		for (int i = 0; i < bandits.getInt(); i++) {
			addEntity(new Bandit());
		}
		/*int numBandits = bandits.getInt();
		int i = 0;
		while (i < numBandits) {
			addEntity(new Bandit());
			i++;
		}*/
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
	
	/**
	 * Adds entity to <code>entities</code> if it is not on the grid.
	 * @param entity - entity to add.
	 */
	public void addEntity(Entity entity) {
		if (onGrid(entity)) {
			return;
		}
		entities.put(entity.getUniqueID(), entity);
	} 
	
	/**
	 * Removes entity from <code>entities</code> if it is on the grid.
	 * @param entity - entity to remove. 
	 */
	public void removeEntity(Entity entity) {
		if (onGrid(entity)) {
			entities.remove(entity.getUniqueID());
		}
	}

	/**
	 * Sets all of the entities to random positions on the grid.
	 */
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
	
	/**
	 * Returns <code>true</code> if the entity is on the grid.
	 * @param entity - the entity to check.
	 * @return <code>true</code> if the entity is on the grid;
	 *         <code>false</code> otherwise
	 */
	public boolean onGrid(Entity entity) {
		return entities.containsKey(entity.getUniqueID());
	}
	
	/**
	 * Moves an entity to the specified vector on the grid as long as the entity is on the grid.
	 * @param entity - the entity to move.
	 * @param pos - the vector to move the entity to.
	 */
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
			} else if (e instanceof Bandit) {
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
