package com.donthaveawebsite.mhy.ocfix;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public List<Spot> spots = new ArrayList<Spot>();

    public Board(int BoardID, List<Spot> thespots)
    {
        spots = thespots;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                spots.add(new Spot(x, y, BoardID));  //1 for z right now only one board at a time.

            }
        }
    }

    public Spot getSpot(int x, int y)
    {

        int index = x*8 + y;
        return spots.get(index);

    }

    //State map  1 is empty spot, 2 has piece, 3 hasselectedpiece
    public int getSpotState (int x, int y)
    {
        return getSpot( x, y).SpotState;
    }
    public Piece getSpotPiece(int x, int y) {return getSpot( x, y).piece;}
    public Spot removePiece(int x, int y) {getSpot( x, y).releaseSpot(); return getSpot( x, y);} //TODO Debug once hooked to UI
}
