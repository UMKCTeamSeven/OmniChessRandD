package com.donthaveawebsite.mhy.omnichess;


import android.widget.ImageView;

public class Spot {
    int x;
    int y;
    int z;
    Piece piece;
    int SpotState; //  1 is empty spot, 2 has piece, 3 hasselectedpiece
    ImageView appearance;

    public Spot(int x, int y, int z) //Object oriented will make this an easy project.
    {
        //super();
        this.x = x;//X spots from orgin
        this.y = y;
        this.z = z;//Board number.
        piece = null;
        SpotState = 1;
    }



    public boolean isOccupied() //returns true if the spot has a piece
    {

        return (piece != null);

    }

    public Piece releaseSpot() {//REMOVES A PIECE!!  Returns null
        Piece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

}