package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SnakeLadder extends Application {

    public final int  tileSize = 40;
    int height = 10;
    int width = 10;

    int diceValue;

    int yLine = 430;
    Group tileGroup = new Group();

    Player playerOne, playerTow;

    Label randResult;

    boolean gameStart = true, turnOnePlayer = true, turnTwoPlayer = false;
    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                tileGroup.getChildren().addAll(tile);
            }
        }
        // add label
        randResult = new Label("Start the Game");
        randResult.setTranslateX(150);
        randResult.setTranslateY(yLine-20);


        //add 3 buttons

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(yLine);
        playerOneButton.setOnAction(actionEvent -> {
            if(gameStart){
                if(turnOnePlayer){
                    getDiceValue();
                    playerOne.movePlayer(diceValue);
                    playerOne.playerAtSnakeOrLadder();
                    turnOnePlayer = false;
                    turnTwoPlayer = true;
                }
            }

        });

        Button gameButton = new Button("Start Game");
        gameButton.setTranslateX(160);
        gameButton.setTranslateY(yLine);

        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setTranslateY(yLine);
        playerTwoButton.setOnAction(actionEvent -> {
            if(gameStart){
                if(turnTwoPlayer){
                    getDiceValue();
                    playerTow.movePlayer(diceValue);
                    playerTow.playerAtSnakeOrLadder();
                    turnOnePlayer = true;
                    turnTwoPlayer = false;
                }
            }

        });

        playerOne = new Player(tileSize, Color.BLACK);
        playerTow = new Player(tileSize-10, Color.WHITE);


        Image img = new Image("file:D:/Projects/SnakeAndLadder/src/main/img.png");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        tileGroup.getChildren().addAll(boardImage,
                playerOneButton, gameButton,
                playerTwoButton, randResult,
                playerOne.getGamePiece(),
                playerTow.getGamePiece()
        );


        return root;
    }

    private void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);
        randResult.setText(Integer.toString(diceValue));
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}