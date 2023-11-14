package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCoordination;
    ArrayList<Integer> snakeLadderPosition;
    public Board(){
        positionCoordination = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeladder();
    }
    public int getNewPosition(int currentPosition){
        if (currentPosition >= 0 && currentPosition <= 100) {
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
    private void populateSnakeladder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.add(4,25);
        snakeLadderPosition.add(27,5);
        snakeLadderPosition.add(33,49);
        snakeLadderPosition.add(40,3);
        snakeLadderPosition.add(42,63);
        snakeLadderPosition.add(43,18);
        snakeLadderPosition.add(50,69);
        snakeLadderPosition.add(54,31);
        snakeLadderPosition.add(62,81);
        snakeLadderPosition.add(66,45);
        snakeLadderPosition.add(76,58);
        snakeLadderPosition.add(74,92);
        snakeLadderPosition.add(89,53);
        snakeLadderPosition.add(99,41);

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
    int getXCoordinates(int position){
        if(position >= 1 && position <= 100) return positionCoordination.get(position).getKey();
        return -1;
    }
    int getYCoordinates(int position){
        if(position >= 1 && position <= 100) return positionCoordination.get(position).getValue();
        return -1;
    }
}
