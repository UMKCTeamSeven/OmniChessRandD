package com.donthaveawebsite.mhy.ocfix;

public class Board {
    private Spot[][] spots = new Spot[3][3]; //lets start with a 3 x 3 board

    public Board(int BoardID) {
        //super();
        for(int i=0; i<spots.length; i++){
            for(int j=0; j<spots.length; j++){
                this.spots[i][j] = new Spot(i, j, BoardID);  //1 for z right now only one board at a time.
            }
        }
    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }

    //State map  1 is empty spot, 2 has piece, 3 hasselectedpiece
    public int getSpotState (int x, int y) {return spots[x][y].SpotState;}
    public Piece getSpotPiece(int x, int y) {return spots[x][y].piece;}
    public Spot removePiece(int x, int y) {spots[x][y].releaseSpot(); return spots[x][y];} //TODO Debug once hooked to UI
}
