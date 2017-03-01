package com.jayms.treasurehunt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.jayms.treasurehunt.Game.GameInitData;

public class TreasureHuntApp extends Application {
	
	public static TreasureHuntApp instance;
	
	private static final int ROWS = 8;
    private static final int COLUMNS = 8;
    private static final int TREASURE_CHESTS = 10;
    private static final int BANDITS = 5;
    private static final int WIDTH = 100;
    private static final int LENGTH = 100;
    
    public static final String STYLE_SHEET = "styles.css";
    
    private Stage stage;
    private Scene menuScene;
    private Game game;
	
	@Override
	public void init() throws Exception {
		
		//menuScene
		VBox menuButtons = new VBox();
        menuButtons.getStyleClass().add("menu-vbox");
        
        Button playGame = new Button("Play Game");
        playGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				initGame();
			}
        	
        });
        
        Button quitGame = new Button("Quit");
        
        menuButtons.getChildren().addAll(playGame, quitGame);
        
        for (Node n : menuButtons.getChildren()) {
        	if (n instanceof Button) {
        		Button but = (Button) n;
        		but.getStyleClass().add("menu-button");
        	}
        }
        menuButtons.setAlignment(Pos.CENTER);
        menuScene = new Scene(menuButtons, 800, 500, Color.WHITE);
        setStyleSheet(menuScene, STYLE_SHEET);
		
	}

	@Override
    public void start(final Stage stage) throws Exception {
		instance = this;
		this.stage = stage;
        stage.setTitle("TreasureHunt");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/img/treasure_hunt_logo.png")));
        stage.setScene(menuScene);
        stage.show();
    }
	
	public void backToMenu() {
		stage.setScene(menuScene);
	}

    public static class Anims {

        public static Node getAtoms(final int number) {
            Circle circle = new Circle(50, 50f, 40);
            circle.setFill(Color.RED);
            Group group = new Group();
            group.getChildren().add(circle);
//            SubScene scene = new SubScene(group, 40, 40);
//            scene.setFill(Color.TRANSPARENT);
            return group;
        }
    }
    
    private void initGame() {
    	GameInitData data = new GameInitData(ROWS, COLUMNS, TREASURE_CHESTS, BANDITS, WIDTH, LENGTH);
    	game = new Game(data);
    	game.start(stage);
    }
    
    public static void setStyleSheet(Scene scene, String file) {
    	scene.getStylesheets().add(TreasureHuntApp.class.getResource(file).toExternalForm());
    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }
}
