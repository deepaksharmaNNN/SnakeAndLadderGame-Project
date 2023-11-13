package com.example.snakeandladder;

import javafx.application.Application;
import javafx.scene.Scene;
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
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize + 50);

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