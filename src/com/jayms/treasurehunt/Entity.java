package com.jayms.treasurehunt;

import java.util.UUID;

import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Location;

public abstract class Entity {

	protected UUID uuid = UUID.randomUUID();
	protected Location loc;
	protected ImageView texture;
	
	public Entity(TreasureGrid grid, ImageView texture) {
		this(new Location(grid), texture);
	}
	
	public Entity(Location loc, ImageView texture) {
		this.loc = loc;
		this.texture = texture;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public UUID getUniqueID() {
		return uuid;
	}
	
	
}
