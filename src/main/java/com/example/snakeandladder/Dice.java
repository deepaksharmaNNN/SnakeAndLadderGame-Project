package com.example.snakeandladder;

public class Dice {
    public int getDiceRolledValue(){
        return (int) (Math.random() * 6) + 1;
    }
}
