package com.donthaveawebsite.mhy.ocfix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Selector
{   private List<Player> players = new ArrayList();
    private Updater theupdater;
    private boolean pieceselected;
    public Selector() {}
    public Selector(final List<Player>  players, List<Spot> toupdate )
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
    public boolean Filter(Spot spot)
    {
        if (pieceselected)
        {return false;}
        //Occupied and not available
        if (spot.isOccupied() && !spot.piece.isAvailable())
        {return false;}
        //Occupied and is the piece of the player whose turn it is
        return (spot.isOccupied() && spot.SpotState == 2 && spot.getpiece().getcolor(spot.getpiece()).equals(this.getCurrentPlayerColor()));

    }

    public boolean SelectedMode( Piece piece , Spot spot, List<Spot> spots)
    {
        pieceselected = true;
        IsValid thetype = new IsValid(piece);
        spot.SpotState = 3;
        for(Iterator<Spot> i = spots.iterator(); i.hasNext(); ) {
            Spot dest = i.next();

            if(thetype.Verify(spot.piece, spot, dest))
            {
                dest.SpotState = 0;
                theupdater.HighlightValid(dest);
            }
        }

        return true;
    }

    public boolean InSelectedMode()
    {
        return pieceselected;
    }

   public void Deselector(Piece piece , Spot spot, List<Spot> spots)
   {
       pieceselected = false;
       IsValid thetype = new IsValid(piece);
       for(Iterator<Spot> i = spots.iterator(); i.hasNext(); )
       {
           Spot dest = i.next();

           if(thetype.Verify(spot.piece, spot, dest))
           {
               if(dest.isOccupied())
               {dest.SpotState = 2;}  //has piece
               else
               {dest.SpotState = 1;}  //empty spot
               theupdater.UnHighlightValid(dest);
           }


        }


   }



}//endclass
