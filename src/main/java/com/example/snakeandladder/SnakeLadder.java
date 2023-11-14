package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 60 , height = 10 , width = 10;
    public static final int buttonLine = height*tileSize + 50 , infoLine = buttonLine - 30;
    private static Dice dice = new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize + 100);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        ImageView board = new ImageView();
        try {
            File file = new File("D:/Projects/SnakeAndLadder/src/main/img.png");
            Image img = new Image(new FileInputStream(file));
            board.setImage(img);
            board.setFitHeight(height * tileSize);
            board.setFitWidth(width * tileSize);

            root.getChildren().add(board);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Buttons
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(50);
        playerOneButton.setDisable(true);

        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(400);
        playerTwoButton.setDisable(true);

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(270);

        //info Display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start The Game !");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(50);

        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(400);

        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(250);

        playerOne = new Player(tileSize, Color.BLACK,"Luffy");
        playerTwo = new Player(tileSize - 5,Color.WHITE,"Zoro");

        //Player Action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getDiceRolledValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        playerOne.movePlayer(diceValue);
                        //Wining Condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is Player One");
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart !");
                            gameStarted = false;


                        }else{
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn : " +playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getDiceRolledValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        playerTwo.movePlayer(diceValue);
                        //Wining Condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is Player Two");
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart !");
                        }else{
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn : " +playerTwo.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn : "+playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();
                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(),playerTwo.getCoin()
        );
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}