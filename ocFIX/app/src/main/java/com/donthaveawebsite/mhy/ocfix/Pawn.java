package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 3/29/2015.
 */
public class Pawn extends Piece
{


    public Pawn (Boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.pawn);
    }

    public boolean FirstMove(Pawn pawn)  //because they have special move rules.
    {
        return pawn.getMC() == 0;
    }

    public void OnMove(Pawn pawn, Spot source, Spot destination)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }

        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(pawn, source, destination);
        try
        {
        if (moved)
        {

            pawn.setMC(pawn.getMC() + 1);
            if (destination.isOccupied()) {
                destination.releaseSpot();
                destination.placePiece(pawn);
            } else {
                destination.placePiece(pawn);
            }
            //update view.
        } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

      }

        //else
//             if (relatedpiece)
//             {
//            //logic to fetch related pieces source
//            //return OnMove(pawn, source ,destination); //checks if the destination works for the related piece.
//             }


}//endclass



