package com.donthaveawebsite.mhy.ocfix;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Selector
{   private List<Player> players = new ArrayList();
    private Updater theupdater;
    private boolean pieceselected;
    private Board board;
    public Selector() {}
    public Selector(final List<Player>  players, List<Spot> toupdate, Board theboard )
    {
        this.players = players;
        theupdater = new Updater(toupdate);
        this.board = theboard;
    }
    public Player getCurrentPlayerColor()
    {             //Todo change method so it is not dependant on the amount of players
        if (this.players.get(0).IsTurn())
        {
            players.get(0).setTurn(false);
            players.get(1).setTurn(true);
           // whiteturnindicator
        return players.get(1);


        }
        else
            players.get(1).setTurn(false);
            players.get(0).setTurn(true);
         //  blackturnindicator
        return players.get(0);


    }
    //updates control layer when pieces are selected and deselected
    public boolean Filter(Spot spot)
    {
        if (pieceselected)
        {return false;}
        //Occupied and not available
        if (spot.isOccupied() && !spot.piece.isAvailable())
        {return false;}
         return VerifyPieceOwnership(spot);

    }

    private boolean VerifyPieceOwnership(Spot spot) {
        return (spot.isOccupied() && spot.SpotState == 2 && ((players.get(0).IsTurn() && spot.getpiece().getcolor(spot.getpiece()) == players.get(0).getColor())|| (players.get(1).IsTurn() && spot.getpiece().getcolor(spot.getpiece()) == players.get(1).getColor())));
    }

    public boolean SelectedMode( Piece piece , Spot spot, List<Spot> spots, Board theboard)
    {
        return Select(piece, spot, spots, theboard);
    }



    public boolean InSelectedMode()
    {
        return pieceselected;
    }

   public void Deselector(Piece piece , Spot spot, List<Spot> spots, Board theboard)
   {
       Deselect(piece, spot, spots, theboard);
   }

    private void FastDeselect(Piece piece, Spot spot)
    {
        pieceselected = false;
        spot.getSelector().setImageResource(R.drawable.ni_tsquare);
    }

    private void Deselect(Piece piece, Spot spot, List<Spot> spots, Board theboard) {
        pieceselected = false;
        IsValid thetype = new IsValid(piece);
        for(Iterator<Spot> i = spots.iterator(); i.hasNext(); )
        {
            Spot dest = i.next();

            if(thetype.Verify(spot.piece, spot, dest, theboard))
            {
                if(dest.isOccupied())
                {dest.SpotState = 2;}  //has piece
                else
                {dest.SpotState = 1;}  //empty spot
                theupdater.UnHighlightValid(dest);
            }


         }
        spot.getSelector().setImageResource(R.drawable.ni_tsquare);
    }

    private boolean Select(Piece piece, Spot spot, List<Spot> spots, Board theboard) {
        pieceselected = true;
        IsValid thetype = new IsValid(piece);
        spot.SpotState = 3;
        for(Iterator<Spot> i = spots.iterator(); i.hasNext(); ) {
            Spot dest = i.next();

            if(thetype.Verify(spot.piece, spot, dest, theboard))
            {
                dest.SpotState = 0;
                theupdater.HighlightValid(dest);
            }

        }
        if (theupdater.NoPossibleMoves())
        {
            FastDeselect(piece, spot);
            spot.SpotState =2;
            return false;
        }
        spot.getSelector().setImageResource(R.drawable.ni_bluesquare);
        return true;
    }


}//endclass
