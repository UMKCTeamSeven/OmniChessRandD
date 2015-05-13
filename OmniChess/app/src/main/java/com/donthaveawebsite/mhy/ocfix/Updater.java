package com.donthaveawebsite.mhy.ocfix;


import android.app.Activity;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Updater extends Activity  {    //Updates piece layer after move is validated, takes care of between move logic.
    private Set<Spot> spotcache;
    //Constructors
    private Set<Spot> validMoves = new HashSet();
    public Updater (List<Spot> toupdate)
    {
        spotcache = new HashSet<>(toupdate);
    }

    public boolean OnMoveUpdate(Spot source, Spot destination)
    {
        return true;
        //update pieces layer
        //check logic alignment
    }

    public boolean HighlightValid(Spot destination)
    {
        destination.getSelector().setImageResource(R.drawable.ni_greesquare);
        validMoves.add(destination);
        return true;
    }
    public boolean UnHighlightValid(Spot destination)
    {
        destination.getSelector().setImageResource(R.drawable.ni_tsquare);
        validMoves.remove(destination);
        return true;
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
                //get piece type and color
                //make sure appearances match
            }
        }
        return true;
    }



}
