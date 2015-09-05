package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 4/11/2015.
 */
public class Mover extends IsValid {

    public Mover() {};


    public boolean TryMove(Piece piece, Spot Source, Spot Destination, Board theboard) throws NullPointerException
    {
        if (piece == null || Destination == null || Source == null) // if any of the parameters are null, something is very wrong
        {
            NullPointerException objectmissing = new NullPointerException("A piece or coordinate(s) are missing");
            throw objectmissing;
        }
        //if related piece call is move valid with Sorce.r and Destination.r here (To be imeplamented)
        return(IsMoveValid(piece, Source, Destination, theboard));

    }


}





