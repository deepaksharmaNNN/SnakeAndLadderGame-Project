package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40 , height = 10 , width = 10;
    public static final int buttonLine = height*tileSize + 50 , infoLine = buttonLine - 30;
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
        playerOneButton.setTranslateX(20);

        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);

        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(170);

        //info Display
        Label playerOneLabel = new Label("Your Turn !");
        Label playerTwoLabel = new Label("Your Turn !");
        Label diceLabel = new Label("Start The Game !");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);

        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);

        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(150);

        root.getChildren().addAll(playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel
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