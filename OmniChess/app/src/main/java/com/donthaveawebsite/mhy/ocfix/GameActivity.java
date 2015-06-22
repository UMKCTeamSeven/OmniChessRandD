package com.donthaveawebsite.mhy.ocfix;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameActivity extends Activity implements OnClickListener

{
    Selector theselector;
    List<Spot> listenspots;
    Spot selectedpiecespot;
    Board mainboard;


    public void onClick(View v)
    {Spot clickedspot = null;
        for(Iterator<Spot> i = listenspots.iterator(); i.hasNext(); )
        {
            Spot currspot = i.next();
            if (currspot.getSelector().getId() == v.getId()) {
                clickedspot = currspot;
                break;
            }
        }
        if (clickedspot == null) {return;}

        if (tryMove(clickedspot)) return;
        if (deselectPiece(clickedspot)) return;
        selectPiece(clickedspot);
    }

    private void selectPiece(Spot clickedspot) {
        if(theselector.Filter(clickedspot))
        {
            selectedpiecespot = clickedspot;
            theselector.SelectedMode(clickedspot.getpiece(), clickedspot, listenspots, mainboard);
            return;
        }
    }

    private boolean deselectPiece(Spot clickedspot) {
        if (clickedspot != null && theselector.InSelectedMode() && clickedspot.SpotState == 3)
        {
            selectedpiecespot = null;
            clickedspot.SpotState = 2;
            theselector.Deselector(clickedspot.getpiece(), clickedspot, listenspots, mainboard);

            return true;
        }
        return false;
    }

    private boolean tryMove(Spot clickedspot) {
        if (clickedspot.SpotState == 0)
        {

            theselector.Deselector(selectedpiecespot.getpiece(), selectedpiecespot, listenspots, mainboard);
            Drawable thepiece = selectedpiecespot.getAppearance().getDrawable();
            clickedspot.getAppearance().setImageDrawable(thepiece);
            selectedpiecespot.getpiece().OnMove(selectedpiecespot.getpiece(),selectedpiecespot, clickedspot, mainboard);
            selectedpiecespot.releaseSpot();
            selectedpiecespot.getAppearance().setImageResource(R.drawable.ni_tsquare);
            selectedpiecespot = null;
            clickedspot.SpotState = 2;
            theselector.getCurrentPlayerColor();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);




        List<Spot> ourspots = new ArrayList<Spot>();
        Board theboard = new Board(1, ourspots);
        for(Iterator<Spot> i = ourspots.iterator(); i.hasNext(); )
        {
            StringBuilder dest = new StringBuilder(2);
            String x = "1234567812345678123456781234567812345678123456781234567812345678";
            String y = "AAAAAAAABBBBBBBBCCCCCCCCDDDDDDDDEEEEEEEEFFFFFFFFGGGGGGGGHHHHHHHH";
            Spot spot = i.next();

               dest.append(y.charAt(ourspots.indexOf(spot)));
               dest.append(x.charAt(ourspots.indexOf(spot)));


            String ps = "P"+ dest.toString();
            String ss = "S"+ dest.toString();
            int piecelayertier = this.getResources().getIdentifier(ps,"id", getPackageName());
            int selectorlayertier = this.getResources().getIdentifier(ss,"id",getPackageName());
            ImageView test =(ImageView)findViewById(piecelayertier);

            spot.TieAppearance((ImageView)findViewById(piecelayertier));
            spot.TieSelector((ImageView)findViewById(selectorlayertier));
            spot.getSelector().setImageResource(R.drawable.ni_tsquare);

            spot.getSelector().setOnClickListener(this);

            spot.getAppearance().setImageResource(R.drawable.ni_tsquare);
           // String text = spot.getAppearance().toString() + " " + spot.getSelector().toString();

        }
    mainboard = theboard;
        Pawn testpawn2 = new Pawn(true,3,1,1);
        Pawn testpawn3 = new Pawn(true,5,1,1);

        Knight whitetestknight = new Knight(true,  1, 7,1);
        Knight testknight = new Knight(true,1,0,1);

        Knight whiteknight = new Knight(true,  6, 7,1);
        Knight blackknight = new Knight(true,6,0,1);


        Rook whitetestrook2 = new Rook(true, 0, 7,1);
        Rook whitetestrook3 = new Rook(true, 7, 7,1);

        Rook blackrook = new Rook(true, 0,0, 1);
        Rook blackrook2 = new Rook(true, 7,0, 1);

        Bishop blackbish = new Bishop(true, 2,0,1);
        Bishop blackbish2 = new Bishop(true, 5,0,1);

        Bishop whitebish = new Bishop(true, 2,7,1);
        Bishop whitebish2 = new Bishop(true, 5,7,1);

        whitetestknight.switchcolor();
        whitetestrook2.switchcolor();
        whitetestrook3.switchcolor();
        whiteknight.switchcolor();
        whitebish.switchcolor();
        whitebish2.switchcolor();

        theboard.getSpot(1,0).placePiece(testknight);
        theboard.getSpot(1,0).getAppearance().setImageResource(R.drawable.ni_bknight);
        theboard.getSpot(1,7).placePiece(whitetestknight);
        theboard.getSpot(1,7).getAppearance().setImageResource(R.drawable.ni_wknight);

        theboard.getSpot(6,0).placePiece(blackknight);
        theboard.getSpot(6,0).getAppearance().setImageResource(R.drawable.ni_bknight);
        theboard.getSpot(6,7).placePiece(whiteknight);
        theboard.getSpot(6,7).getAppearance().setImageResource(R.drawable.ni_wknight);

        theboard.getSpot(2,0).placePiece(blackbish);
        theboard.getSpot(2,0).getAppearance().setImageResource(R.drawable.ni_bishop);
        theboard.getSpot(5,0).placePiece(blackbish2);
        theboard.getSpot(5,0).getAppearance().setImageResource(R.drawable.ni_bishop);

        theboard.getSpot(2,7).placePiece(whitebish);
        theboard.getSpot(2,7).getAppearance().setImageResource(R.drawable.ni_bishopw);
        theboard.getSpot(5,7).placePiece(whitebish2);
        theboard.getSpot(5,7).getAppearance().setImageResource(R.drawable.ni_bishopw);


        theboard.getSpot(0,7).placePiece(whitetestrook2);
        theboard.getSpot(0,7).getAppearance().setImageResource(R.drawable.ni_rookw);
        theboard.getSpot(7,7).placePiece(whitetestrook3);
        theboard.getSpot(7,7).getAppearance().setImageResource(R.drawable.ni_rookw);

        theboard.getSpot(7,0).placePiece(blackrook2);
        theboard.getSpot(7,0).getAppearance().setImageResource(R.drawable.ni_rook);
        theboard.getSpot(0,0).placePiece(blackrook);
        theboard.getSpot(0,0).getAppearance().setImageResource(R.drawable.ni_rook);


        theboard.getSpot(3,1).placePiece(testpawn2);
        theboard.getSpot(3,1).getAppearance().setImageResource(R.drawable.ni_pawn);

        theboard.getSpot(5,1).placePiece(testpawn3);
        theboard.getSpot(5,1).getAppearance().setImageResource(R.drawable.ni_pawn);






        Player white = new Player('W');
        Player black = new Player('B');
        black.setTurn(true);
        List<Player> players = new ArrayList<Player>();
        //For now just the black player will be added
        players.add(black);
        players.add(white);
        listenspots = ourspots;
        theselector = new Selector(players, ourspots, theboard);


    }








    }//endclass



