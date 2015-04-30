package com.donthaveawebsite.mhy.ocfix;


import android.app.Activity;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Updater extends Activity
{    //Updates piece layer after move is validated, takes care of between move logic.
    private Set<Spot> spotcache;
    public Updater (List<Spot> toupdate)
    {
        spotcache.addAll(toupdate);
    }

    public boolean OnMoveUpdate(Spot source, Spot destination)
    {
        return true;
        //update pieces layer
        //check logic alignment
    }

    public boolean AfterMoverUpdate()
    {

        //switch player
        RefreshPieceLayer();
        //set listeners
        return true;
    }

    public Set<Spot> getset()
    {
        return this.spotcache;
    }

    public boolean RefreshPieceLayer()
    {
        for (Iterator<Spot> i = this.spotcache.iterator(); i.hasNext();)
        {
            Spot spot = i.next();
            if (spot.SpotState == 1)
            {
                spot.getAppearance().setImageResource(R.drawable.ni_tsquare);
            }
            if (spot.SpotState == 2)
            {
                //get piece type
                //make sure appearances match
            }
        }
        return true;
    }


}
