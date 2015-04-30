package com.donthaveawebsite.mhy.ocfix;

public class Player
{
    private String color;
    private boolean isTurn;
    private int score;
    public Player(String color)
    {
        this.color = color;
        this.isTurn = false;
        this.score = 0;
    }
    public void setTurn(boolean yourturn)
    {
        this.isTurn = yourturn;
    }
    public boolean IsTurn()
    {
        return this.isTurn;
    }
    public void changeScore(int change)
    {
        this.score += change;
    }
    public String getColor()
    {
        return this.color;
    }
}
