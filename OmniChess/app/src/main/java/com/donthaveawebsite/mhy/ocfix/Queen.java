
package com.donthaveawebsite.mhy.ocfix;
/**
 * Created by Matthew on 2/25/2016.
 */
public class Queen extends Piece


{

    public Queen (boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.queen);
    }

    public void OnMove(Piece queen, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }
        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(queen, source, destination, theboard);
        try
        {
            if (moved)
            {
                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(queen);
                } else {
                    destination.placePiece(queen);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}