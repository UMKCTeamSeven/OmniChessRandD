package com.donthaveawebsite.mhy.ocfix;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Matthew on 4/25/2015.
 */
public class Selector
{
    public Selector()
    {}

    public boolean Filter(Spot spotState)
    {
        if (spotState.isOccupied() && !spotState.piece.isAvailable())
        {return false;}
        if (spotState.isOccupied() && spotState.SpotState == 2)
        {return true;}
        else
        {return false;}
    }

    public boolean SelectedMode( Piece piece , Spot spotState, List<Spot> spots)
    {
        IsValid thetype = new IsValid(piece);
        spotState.SpotState = 3;
        for(Iterator<Spot> i = spots.iterator(); i.hasNext(); ) {
            Spot dest = i.next();

            if(thetype.Verify(spotState.piece, spotState, dest))
            {
                dest.SpotState = 0;
            }
        }

        return true;
    }




}
