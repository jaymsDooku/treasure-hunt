package com.jayms.treasurehunt.util;

public class Vector2DInt {

	private int x;
	private int y;
	
	public Vector2DInt() {
		this(0, 0);
	}
	
	public Vector2DInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int set) {
		this.x = set;
	}
	
	public void setY(int set) {
		this.y = set;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2DInt) {
			Vector2DInt vector = (Vector2DInt) obj;
			return vector.x == this.x && vector.y == this.y;
		}
		return false;
	}
}
