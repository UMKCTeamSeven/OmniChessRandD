package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 5/14/2015.
 */
public class Knight extends Piece
{
    public Knight (boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.knight);
    }

    public void OnMove(Piece knight, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }
        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(knight, source, destination, theboard);
        try
        {
            if (moved)
            {


                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(knight);
                } else {
                    destination.placePiece(knight);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}
