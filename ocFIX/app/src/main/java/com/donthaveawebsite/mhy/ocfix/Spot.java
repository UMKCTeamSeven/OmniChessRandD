package com.donthaveawebsite.mhy.ocfix;
import android.widget.ImageView;
import junit.framework.Assert;
public class Spot {
    int x;
    int y;
    int z;
    Piece piece;
    public int SpotState; //  1 is empty spot, 2 has piece, 3 hasselectedpiece
    ImageView appearance;

    public Spot(int x, int y, int z) //Object oriented will make this an easy project.
    {
        //super();
        this.x = x;//X spots from orgin
        this.y = y;
        this.z = z;//Board number.
        piece = null;
        SpotState = 1; //flag for empty spot
    }

    public boolean isOccupied() //returns true if the spot has a piece
    {

        return (piece != null);

    }

    public void placePiece(Piece piece)
    {
        this.piece = piece;
        piece.setY(this.y);
        piece.setX(this.x);
        piece.setZ(this.z);
        this.SpotState = 2;
    }

    public void releaseSpot() {//REMOVES A PIECE!!
        this.piece = null;
        this.SpotState = 1;
        //log in pieces taken later? Treasure chest of fallen pieces?
        }

}