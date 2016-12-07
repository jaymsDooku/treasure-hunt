package com.jayms.treasurehunt;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import com.jayms.treasurehunt.TreasureHuntApp.Anims;

public class Grid extends GridPane {
	
	public Grid(int rows, int columns) {
		this.getStyleClass().add("grid-pane");
		this.getStyleClass().add("game-grid");
		
		for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            this.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(100);
            this.getRowConstraints().add(row);
        }

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                GridSlot slot = new GridSlot();
                slot.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                	slot.getStyleClass().add("first-column");
                }
                if (j == 0) {
                	slot.getStyleClass().add("first-row");
                }
                this.add(slot, i, j);
            }
        }
	}
	
	public int getRowCount() {
		return this.getRowConstraints().size();
	}
	
	public int getColumnCount() {
		return this.getColumnConstraints().size();
	}

	public GridSlot getGridSlot(int row, int col) {
		GridSlot result = null;
		ObservableList<Node> childrens = this.getChildren();

	    for (Node node : childrens) {
	    	if (!(node instanceof GridSlot)) {
	    		continue;
	    	}
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
	            result = (GridSlot) node;
	            break;
	        }
	    }
	    return result;
	}
}
