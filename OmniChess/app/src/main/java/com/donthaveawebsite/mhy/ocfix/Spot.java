package com.donthaveawebsite.mhy.ocfix;
import android.widget.ImageView;
import junit.framework.Assert;
public class Spot {
    int x;
    int y;
    int z;
    Piece piece;
    public int SpotState; //  1 is empty spot, 2 has piece, 3 hasselectedpiece 0 is validmove

    private ImageView selector;
    private ImageView appearance;

    public Spot(int x, int y, int z) //Object oriented will make this an easy project.
    {
        //super();
        this.x = x;//X spots from orgin
        this.y = y;
        this.z = z;//Board number.
        piece = null;
        SpotState = 1; //flag for empty spot
    }

    public Piece getpiece()
    {
        return this.piece;
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

    public ImageView getAppearance() {
        return appearance;
    }

    public void TieAppearance(ImageView appearance) {
        this.appearance = appearance;
    }

    public ImageView getSelector() {
        return selector;
    }

    public void TieSelector(ImageView selector) {
        this.selector = selector;
    }
}