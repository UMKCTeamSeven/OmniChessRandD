package com.donthaveawebsite.mhy.ocfix;

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

    public boolean SelectedMode(Spot spotState)
    {

        return true;
    }




}
