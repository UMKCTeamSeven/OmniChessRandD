package com.donthaveawebsite.mhy.ocfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Selector
{   private List<Player> players = new ArrayList();
    private Updater theupdater;
    public Selector(List<Player> players, List<Spot> toupdate )
    {
        this.players = players;
        theupdater = new Updater(toupdate);
    }
    public String getCurrentPlayerColor()
    {             //Todo change method so it is not dependant on the amount of players
        if (players.get(0).IsTurn())
        {
            return players.get(0).getColor();
        }
        else
            return players.get(1).getColor();
    }
    //updates control layer when pieces are selected and deselected
    public boolean Filter(Spot spotState)
    {
        //Occupied and not available
        if (spotState.isOccupied() && !spotState.piece.isAvailable())
        {return false;}
        //Occupied and is the piece of the player whose turn it is
        return (spotState.isOccupied() && spotState.SpotState == 2 && spotState.getpiece().getcolor(spotState.getpiece()).equals(this.getCurrentPlayerColor()));

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
                //update dest
            }
        }

        return true;
    }

   public void Deselector(Piece piece , Spot spotState, List<Spot> spots)
   {


       if (spotState.SpotState != 3)
       {return;}
       IsValid thetype = new IsValid(piece);
       for(Iterator<Spot> i = spots.iterator(); i.hasNext(); )
       {
           Spot dest = i.next();

           if(thetype.Verify(spotState.piece, spotState, dest))
           {
               if(dest.isOccupied())
               {dest.SpotState = 2;}
               else
               {dest.SpotState = 1;}
               //update dest
           }


        }


   }



}//endclass
