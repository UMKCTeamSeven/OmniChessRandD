package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 6/20/2015.
 */
public class Bishop extends Piece
{
    public Bishop(boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.bishop);
    }

    public void OnMove(Piece bishop, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }
        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(bishop, source, destination, theboard);
        try
        {
            if (moved)
            {
                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(bishop);
                } else {
                    destination.placePiece(bishop);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}
