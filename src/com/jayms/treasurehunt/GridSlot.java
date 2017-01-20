package com.jayms.treasurehunt;

import com.jayms.treasurehunt.util.Util;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GridSlot extends Pane {

	private static final String EMPTY_TEXTURE = "/resources/img/hammer_and_sickle.png";
	
	private Entity cached;
	private Entity entity;
	
	public GridSlot() {
	}
	
	public void setEntity(Entity entity) {
		setTexture(entity.getTexture());
		this.entity = entity;
	}
	
	public void setTexture(ImageView texture) {
		ObservableList<Node> children = this.getChildren();
		if (!children.isEmpty()) {
			children.clear();
		}
		children.add(texture);
	}
	
	//TODO: empty texture
	public void empty() {
		ImageView img = new ImageView(Util.getImage(EMPTY_TEXTURE));
		Util.fitImage(99, 99, false, img);
		setTexture(img);
		entity = null;
	}
	
	public void cacheCurrentEntity() {
		this.cached = entity;
	}
	
	public void restoreCachedEntity() {
		setEntity(cached);
	}
	
	public boolean cachedEntity() {
		return cached != null;
	}
	
	public Entity getCachedEntity() {
		return cached;
	}
	
	public boolean hasEntity() {
		return entity != null;
	}
	
	public Entity getEntity() {
		return entity;
	}
}
