package com.donthaveawebsite.mhy.omnichess;


public class Board {
    private Spot[][] spots = new Spot[3][3]; //lets start with a 3 x 3 board

    public Board() {
        //super();
        for(int i=0; i<spots.length; i++){
            for(int j=0; j<spots.length; j++){
                this.spots[i][j] = new Spot(i, j, 1);  //1 for z right now only one board at a time.
            }
        }
    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }

}
