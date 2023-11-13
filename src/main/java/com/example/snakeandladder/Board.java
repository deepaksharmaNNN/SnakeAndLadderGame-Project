package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCoordination;
    public Board(){
        positionCoordination = new ArrayList<>();
    }
    private void populatePositionCoordinates(){
        positionCoordination.add(new Pair<>(0,0));//dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //X coordinates
                int xCord = 0;
                if(i % 2 == 0){
                    xCord = j * SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }else{
                    xCord = SnakeLadder.tileSize * SnakeLadder.height - (j*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                }
                //Y coordinates
                int yCord = SnakeLadder.tileSize * SnakeLadder.height - (i*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;

                positionCoordination.add(new Pair<>(xCord,yCord));
            }
        }
    }
}
