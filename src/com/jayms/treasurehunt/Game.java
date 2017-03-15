package com.jayms.treasurehunt;

import java.util.ArrayList;

import com.jayms.treasurehunt.util.NumberTextField;
import com.jayms.treasurehunt.util.Util;
import com.jayms.treasurehunt.util.Vector2DInt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
	
	public static class GameInitData {
		
		private int rows;
		private int columns;
		private int treasure_chests;
		private int bandits;
		private int width;
		private int length;
		
		public GameInitData(int rows, int columns, int treasure_chests, int bandits, int width, int length) {
			this.rows = rows;
			this.columns = columns;
			this.treasure_chests = treasure_chests;
			this.bandits = bandits;
			this.width = width;
			this.length = length;
		}
		
		public int getRows() {
			return rows;
		}
		
		public int getColumns() {
			return columns;
		}
		
		public int getTreasureChests() {
			return treasure_chests;
		}
		
		public int getBandits() {
			return bandits;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getLength() {
			return length;
		}
	}
	
	private Scene gameOverScene;
	private VBox gameOverLayout;
	private Label youWin;
	private Label youLose;
	private Button backToMenu;

	private Scene gameScene;
	private VBox gameLayout;
	
	private HBox promptBox;
	private Button confirmButton;
	private ComboBox<String> dropDown;
	private NumberTextField numberTextField;
	private Label notification;
	
	private HBox displayBox;
	private Label coins;
	private Label treasureChests;
	private Label bandits;
	
	private TreasureGrid grid;
	private Player p;
	
	public Game(GameInitData data) {
		treasureChests = new Label("Treasure Chests: 10");
		treasureChests.getStyleClass().add("notification");
		bandits = new Label("Bandits: 5");
		bandits.getStyleClass().add("notification");
		grid = new TreasureGrid(data.getRows(), data.getColumns(), data.getTreasureChests(), data.getBandits(), treasureChests, bandits, this);
		promptBox = new HBox();
		promptBox.getStyleClass().add("promptbox");
		displayBox = new HBox();
		displayBox.getStyleClass().add("promptbox");
		displayBox.setAlignment(Pos.CENTER);
		displayBox.setPrefHeight(50);
		coins = new Label("Coins: 0");
		coins.getStyleClass().add("notification");
		confirmButton = new Button("Confirm");
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				performMove();
			}
			
		});
		dropDown = new ComboBox<String>();
		numberTextField = new NumberTextField();
		notification = new Label("Enter your move.");
		notification.getStyleClass().add("notification");
		promptBox.getChildren().addAll(dropDown, numberTextField, confirmButton, notification);
		promptBox.setAlignment(Pos.CENTER);
		promptBox.setPrefHeight(50);
		displayBox.getChildren().addAll(coins, treasureChests, bandits);
		gameLayout = new VBox();
		gameLayout.getChildren().addAll(grid, promptBox, displayBox);
		gameScene = new Scene(gameLayout, (data.getRows() * 100), ((data.getColumns() * 100) + promptBox.getPrefHeight() + displayBox.getPrefHeight()), Color.WHITE);
		TreasureHuntApp.setStyleSheet(gameScene, TreasureHuntApp.STYLE_SHEET);
		
		youWin = new Label("You win!");
		youWin.getStyleClass().add("you-win");
		youLose = new Label("You lose!");
		youLose.getStyleClass().add("you-lose");
		
		backToMenu = new Button("Back To Menu");
		backToMenu.getStyleClass().add("menu-button");
		backToMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				TreasureHuntApp.instance.backToMenu();
			}
			
		});
		
		gameOverLayout = new VBox();
		gameOverLayout.setAlignment(Pos.CENTER);
		gameOverScene = new Scene(gameOverLayout, 800, 500, Color.WHITE);
		TreasureHuntApp.setStyleSheet(gameOverScene, TreasureHuntApp.STYLE_SHEET);
	}
	
	private Stage stage;
	
	public void start(Stage s) {
		stage = s;
		gameOver(true);
		return;
		/*p = grid.initGame(this, coins);
		this.stage = s;
		stage.setScene(gameScene);
		enterMove();*/
	}
	
	public void gameOver(boolean won) {
		gameOverLayout.getChildren().clear();
		if (won) {
			backToMenu.getStyleClass().add("win-btm");
			gameOverLayout.getStyleClass().add("win-gameover");
			gameOverLayout.getChildren().addAll(youWin, backToMenu);
		} else {
			gameOverLayout.getStyleClass().add("lose-gameover");
			gameOverLayout.getChildren().addAll(youLose, backToMenu);
		}
		stage.setScene(gameOverScene);
	}
	
	private String[] directions = {"Left", "Right", "Up", "Down"};
	
	public void enterMove() {
		if (!dropDown.getItems().isEmpty()) {
			dropDown.getItems().clear();
		}
		Vector2DInt pos = p.getLocation().getPosition();
		int x = pos.getX();
		int y = pos.getY();
		int left = y == 0 ? -1 : 0;
		int right = y == 7 ? -1 : 1;
		int up = x == 0 ? -1 : 2;
		int down = x == 7 ? -1 : 3;
		ArrayList<Integer> indices = new ArrayList<>();
		Util.addIfNotNegative(down, indices);
		Util.addIfNotNegative(up, indices);
		Util.addIfNotNegative(left, indices);
		Util.addIfNotNegative(right, indices);
		ArrayList<String> options = new ArrayList<>();
		for (Integer i : indices) {
			options.add(directions[i]);
		}
		dropDown.getItems().addAll(options);
		dropDown.setValue(options.get(0));
	}
	
	public void performMove() {
		String direction = dropDown.getValue();
		int num = numberTextField.getValue();
		Vector2DInt pos = p.getLocation().getPosition();
		int x = pos.getX();
		int y = pos.getY();
		switch (direction) {
		case "Left":
			y = (y - num);
			if (y < 0) {
				System.out.println("too big");
			}
			
			break;
		case "Right":
			y = (y + num);
			if (y > 7) {
				System.out.println("too big");
			}
			break;
		case "Up":
			x = (x - num);
			System.out.println("up");
			if (x < 0) {
				System.out.println("too big");
			}
			break;
		case "Down":
			x = (x + num);
			if (x > 7) {
				System.out.println("too big");
			}
			break;
		default:
			throw new RuntimeException("gg");
		}
		Vector2DInt newPos = new Vector2DInt(x, y);
		Entity ent = grid.readyMovePlayer(newPos);
		if (ent != null) {
			ent.onLand(p);
		}
		grid.movePlayer(p, newPos);
		enterMove();
	}
}
