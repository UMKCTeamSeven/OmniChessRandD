package com.donthaveawebsite.mhy.ocfix;

public class Player
{
    private char color;
    private boolean isTurn;
    private int score;
    public Player(char color)
    {
        this.color = color;
        this.isTurn = false;
        this.score = 0;
    }
    public void setTurn(boolean isTurn)
    {
        this.isTurn = isTurn;
    }
    public boolean IsTurn()
    {
        return this.isTurn;
    }
    public void changeScore(int change)
    {
        this.score += change;
    }
    public char getColor()
    {
        return this.color;
    }
}
