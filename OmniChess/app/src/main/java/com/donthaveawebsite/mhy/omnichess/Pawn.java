package com.donthaveawebsite.mhy.omnichess;

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


    public boolean OnMove(Pawn pawn, Spot source, Spot destination)
    {
        //if (checkvalid()) to be implamented
        mc++;
        return true;
        //else
//             if (relatedpiece)
//             {
//            //logic to fetch related pieces source
//            //return OnMove(pawn, source ,destination); //checks if the destination works for the related piece.
//             }



    }

}

