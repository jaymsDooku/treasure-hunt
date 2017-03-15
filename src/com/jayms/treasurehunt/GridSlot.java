package com.jayms.treasurehunt;

import com.jayms.treasurehunt.util.Util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Represents a slot on the <code>com.jayms.treasurehunt.TreasureGrid</code>.
 * @author james
 */
public class GridSlot extends Pane {

	private static final String EMPTY_TEXTURE = "/resources/img/hammer_and_sickle.png";
	
	/**
	 * {@value #cached} is the cached entity for the Grid Slot; currently inactive in the program.
	 */
	private Entity cached;
	/**
	 * {@value #entity} is the entity currently displayed on the Grid Slot.
	 */
	private Entity entity;
	
	/**
	 * Instantiates a new <code>com.jayms.treasurehunt.GridSlot</code>
	 */
	public GridSlot() {
	}
	
	/**
	 * Sets the entity of <code>com.jayms.treasurehunt.GridSlot</code> by setting the image displayed by the <code>javafx.scene.layout.Pane</code> and setting the <code>entity</code> property.
	 * @param entity - the entity to set.
	 */
	public void setEntity(Entity entity) {
		if (entity instanceof Player) {
			setTexture(entity.getTexture());
		}
		this.entity = entity;
	}
	
	/**
	 * Sets the image displayed by the <code>javafx.scene.layout.Pane</code>.
	 * @param texture - the <code>javafx.scene.image.ImageView</code> to set.
	 */
	public void setTexture(ImageView texture) {
		ObservableList<Node> children = this.getChildren();
		if (!children.isEmpty()) {
			children.clear();
		}
		children.add(texture);
	}
	
	/**
	 * Empties the <code>com.jayms.treasurehunt.GridSlot</code> completely, replacing the image displayed by the <code>javafx.scene.layout.Pane</code> with a default {@value #EMPTY_TEXTURE}.
	 */
	public void empty() {
		ImageView img = new ImageView(Util.getImage(EMPTY_TEXTURE));
		Util.fitImage(99, 99, false, img);
		setTexture(img);
		entity = null;
	}
	
	/**
	 * Caches the current entity by setting the <code>cached</code> to <code>entity</code>.
	 */
	public void cacheCurrentEntity() {
		this.cached = entity;
	}
	
	/**
	 * Restors the cached entity by setting the <code>entity</code> to <code>cached</code>.
	 */
	public void restoreCachedEntity() {
		if (cached != null) {
			setEntity(cached);
		}
	}
	
	/**
	 * Returns <code>true</code> if <code>cached</code> is not equal to <code>null</code>.
	 * @return <code>true</code> if if <code>cached</code> is not equal to <code>null</code>; <code>false</code> otherwise
	 */
	public boolean cachedEntity() {
		return cached != null;
	}
	
	/**
	 * Returns the <code>cached</code> entity.
	 * @return <code>cached</code>
	 */
	public Entity getCachedEntity() {
		return cached;
	}
	
	/**
	 * Sets the <code>cached</code> entity to the entity <code>e</code>.
	 * @param e - the entity to set.
	 */
	public void setCachedEntity(Entity e) {
		this.cached = e;
	}
	
	/**
	 * Returns <code>true</code> if <code>entity</code> is not equal to <code>null</code>
	 * @return <code>true</code> if <code>entity</code> is not equal to <code>null</code>; otherwise <code>false</code>
	 */
	public boolean hasEntity() {
		return entity != null;
	}
	
	/**
	 * Returns the entity displayed on the grid slot.
	 * @return <code>entity</code>
	 */
	public Entity getEntity() {
		return entity;
	}
}
