package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 5/17/2016.
 */
public class King extends Piece
{
    private char kingcolor = 'B';
    public King(boolean available, int x, int y, int z)

    {
        super(available, x, y, z, R.drawable.ni_bking);
        this.type = new IsValid(IsValid.piecetype.king);
    }

    @Override
    public void switchcolor()
    {
        if (kingcolor == 'W')
        {
            kingcolor = 'B';
            this.setIcon(R.drawable.ni_bking);

        }
        else
        {
            kingcolor ='W';
            this.setIcon(R.drawable.ni_wking);
        }

    }

    public void OnMove(Piece king, Spot source, Spot destination, Board theboard)
    {
        if (source == destination)
        {
            //exit selected mode
            return;
        }
        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(king, source, destination, theboard);
        try
        {
            if (moved)
            {
                if (destination.isOccupied()) {
                    destination.releaseSpot();
                    destination.placePiece(king);
                } else {
                    destination.placePiece(king);
                }
                //update view.
            } else {   } }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing)
        {  }

    }

}

