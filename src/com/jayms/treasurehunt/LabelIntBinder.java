package com.jayms.treasurehunt;

import javafx.scene.control.Label;

public class LabelIntBinder {

	private int i;
	private Label label;
	private String format;
	
	public LabelIntBinder(int i, Label label, String format) {
		this.i = i;
		this.label = label;
		this.format = format;
	}
	
	public void updateDisplay() {
		label.setText(format.replaceAll("%i", Integer.toString(i)));
	}
	
	public void setInt(int set) {
		this.i = set;
		updateDisplay();
	}
	
	public int getInt() {
		return i;
	}
}
