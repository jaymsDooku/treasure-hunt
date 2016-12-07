package com.jayms.treasurehunt;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GridSlot extends Pane {

	private Entity entity;
	
	public GridSlot() {
	}
	
	public void setEntity(Entity entity) {
		ObservableList<Node> children = this.getChildren();
		if (!children.isEmpty()) {
			children.clear();
		}
		children.add(entity.getTexture());
		this.entity = entity;
	}
	
	//TODO: empty texture
	public void empty() {
		entity = null;
		
	}
	
	public Entity getEntity() {
		return entity;
	}
}
