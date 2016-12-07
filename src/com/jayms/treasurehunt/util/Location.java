package com.jayms.treasurehunt.util;

import com.jayms.treasurehunt.TreasureGrid;

public class Location {

	private TreasureGrid grid = null;
	private Vector2DInt pos;
	
	public Location() {
		this(new Vector2DInt());
	}
	
	public Location(Vector2DInt pos) {
		this.pos = pos;
	}
	
	public Location(TreasureGrid grid) {
		this(new Vector2DInt(), grid);
	}
	
	public Location(Vector2DInt pos, TreasureGrid grid) {
		this.grid = grid;
		this.pos = pos;
	}
	
	public void setPosition(Vector2DInt set) {
		if (this.pos.equals(set)) {
			return;
		}
		this.pos = set;
	}
	
	public void setTreasureGrid(TreasureGrid set) {
		if (grid != null) {
			if (this.grid.equals(set)) {
				return;
			}
		}
		this.grid = set;
	}
	
	public TreasureGrid getTreasureGrid() {
		return grid;
	}
	
	public Vector2DInt getPosition() {
		return pos;
	}
	
	@Override
	public String toString() {
		return "TreasureGrid: " + grid + "\nPosition: " + pos.toString();
	}
}
