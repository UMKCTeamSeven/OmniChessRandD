package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 4/11/2015.
 */
public class Mover extends IsValid {

    public Mover() {};


    public boolean TryMove(Piece piece, Spot Source, Spot Destination) throws NullPointerException
    {
        if (piece == null || Destination == null || Source == null)
        {
            NullPointerException objectmissing = new NullPointerException("A piece or coordinate(s) are missing");
            throw objectmissing;
        }
        try
        {
            //if related piece call is move valid with Sorce.r and Destination.r here (To be imeplamented)
            return(IsMoveValid(piece, Source, Destination));
        }
        catch (NullPointerException objectmissing)  // if any of the parameters are null, something is very wrong
        {  }

        return false;
    }


}





