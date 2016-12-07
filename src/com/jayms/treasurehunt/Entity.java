package com.jayms.treasurehunt;

import java.util.UUID;

import javafx.scene.image.ImageView;

import com.jayms.treasurehunt.util.Location;
import com.jayms.treasurehunt.util.Util;

public abstract class Entity {

	protected UUID uuid = UUID.randomUUID();
	protected Location loc = new Location();
	protected ImageView texture;
	
	public Entity(ImageView texture) {
		Util.fitImage(99, 99, false, texture);
		this.texture = texture;
	}
	
	public Entity(TreasureGrid grid, ImageView texture) {
		this(new Location(grid), texture);
	}
	
	public Entity(Location loc, ImageView texture) {
		this.loc = loc;
		Util.fitImage(99, 99, false, texture);
		this.texture = texture;
	}
	
	public ImageView getTexture() {
		return texture;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public UUID getUniqueID() {
		return uuid;
	}
	
	
}
