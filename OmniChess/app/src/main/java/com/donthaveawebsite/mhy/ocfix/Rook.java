package com.donthaveawebsite.mhy.ocfix;

public class Rook extends Piece
{
    public Rook (boolean available, int x, int y, int z)
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.rook);
    }

    public void OnMove(Piece rook, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }
        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(rook, source, destination, theboard);
        try
        {
            if (moved)
            {
                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(rook);
                } else {
                    destination.placePiece(rook);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}