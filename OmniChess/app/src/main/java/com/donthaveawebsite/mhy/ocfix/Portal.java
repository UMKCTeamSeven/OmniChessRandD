package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 5/7/2016.
 */
public class Portal extends Piece
{
 public Portal(Boolean available, int x, int y, int z)                           //Dead end portal
 {
     super(available, x, y, z);
     this.type = new IsValid(IsValid.piecetype.portal);
 }

    public Portal(Boolean available, int x, int y, int z, Portal relatedPortal)  //Paired
    {
        super(available, x, y, z);
        this.type = new IsValid(IsValid.piecetype.portal);
        this.SetRelated(relatedPortal);
    }

    @Override
    public void OnMove(Piece portal, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
             return;
        }

        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(portal, source, destination, theboard);
        try
        {
            if (moved)
            {
                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(portal);
                } else {
                    destination.placePiece(portal);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}
