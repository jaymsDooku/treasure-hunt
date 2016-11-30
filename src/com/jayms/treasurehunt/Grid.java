package com.jayms.treasurehunt;

import com.jayms.treasurehunt.TreasureHuntApp.Anims;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

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
                Pane pane = new Pane();
                pane.setOnMouseReleased(e -> {
                    pane.getChildren().add(Anims.getAtoms(1));
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                this.add(pane, i, j);
            }
        }
	}

}
