package com.donthaveawebsite.mhy.ocfix;

public class Piece {
    private Piece relatedpiece;
    private boolean available;
    private int x;
    private int y;
    private int z; // for board id. Right now only one board.

    public IsValid type;

    private char color = 'B';
    public int mc = 0;

    private int icon;
    public Piece(boolean available, int x, int y, int z, int icon)
    {
        super();
        this.available = available;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = null;
        this.icon = icon;

    }

    public void SetRelated(Piece relatedpiece)
    {
        this.relatedpiece = relatedpiece;
        relatedpiece.relatedpiece = this;
    }
    public Piece GetRelated()
    {
        return relatedpiece;
    }

    public boolean isAvailable() {  //IE The can this piece move function
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getMC() {return mc;}
    public void setMC(int mc) {this.mc = mc;}
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getZ() {
        return z;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public void switchcolor() {if (this.color == 'W') this.color ='B'; else this.color='W';}
    public char getcolor(Piece piece) {return this.color;}
    public int getIcon() { return icon; }
    public void setIcon(int icon) { this.icon = icon; }

    public void OnMove(Piece piece, Spot source, Spot destination, Board theboard)
    {

    }


    // public boolean isValid(Board board, int fromX, int fromY, int toX, int toY){
   //     if(toX == fromX && toY == fromY)
   //         return false; //cannot move nothing
  //      if(toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY <0 || fromY > 7)
   //         return false;
  //      return true;
   // }  On a 3 x 3 board an 8 x 8 border doesn't do much good.

}