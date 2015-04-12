package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 3/29/2015.
 */
public class Pawn extends Piece
{
    private boolean relatedpiece;
    private int mc = 0;
    public Pawn (Boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
    }

    public boolean FirstMove(Pawn pawn)  //because they have special move rules.
    {
        return mc == 0;
    }

    public void SetRelated(boolean relatedpiece)
    {
        this.relatedpiece = relatedpiece;
    }
    public boolean GetRelated()
    {
        return relatedpiece;
    }


    public void OnMove(Pawn pawn, Spot source, Spot destination)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }

        Mover theMove = new Mover();
        if (theMove.TryMove(pawn, source, destination)) {
            mc++;
            if (destination.isOccupied()) {
                destination.releaseSpot();
                destination.placePiece(pawn);
            } else {
                destination.placePiece(pawn);
            }
            //update view.
        } else {
            //return to selected mode
        }
    }

        //else
//             if (relatedpiece)
//             {
//            //logic to fetch related pieces source
//            //return OnMove(pawn, source ,destination); //checks if the destination works for the related piece.
//             }


}



