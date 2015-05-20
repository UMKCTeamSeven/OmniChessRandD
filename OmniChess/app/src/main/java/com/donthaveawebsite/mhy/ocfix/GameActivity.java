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

        //Try Move
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
            return;
        }

        //Deselect piece
        if (clickedspot != null && theselector.InSelectedMode() && clickedspot.SpotState == 3)
        {
            selectedpiecespot = null;
            clickedspot.SpotState = 2;
            theselector.Deselector(clickedspot.getpiece(), clickedspot, listenspots, mainboard);

            return;
        }
        //Select piece
        if(theselector.Filter(clickedspot))
        {
            selectedpiecespot = clickedspot;
            theselector.SelectedMode(clickedspot.getpiece(), clickedspot, listenspots, mainboard);
            return;
        }
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

        Knight whitetestknight = new Knight(true,  2, 7,1);
        Knight testknight = new Knight(true,1,1,1);

        Pawn whitetestpawn2 = new Pawn(true, 4, 7,1);
        Pawn whitetestpawn3 = new Pawn(true, 5, 7,7);

        whitetestknight.switchcolor();
        whitetestpawn2.switchcolor();
        whitetestpawn3.switchcolor();

        theboard.getSpot(1,1).placePiece(testknight);
        theboard.getSpot(1,1).getAppearance().setImageResource(R.drawable.ni_bknight);
        theboard.getSpot(2,7).placePiece(whitetestknight);
        theboard.getSpot(2,7).getAppearance().setImageResource(R.drawable.ni_wknight);

        theboard.getSpot(4,7).placePiece(whitetestpawn2);
        theboard.getSpot(4,7).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(5,7).placePiece(whitetestpawn3);
        theboard.getSpot(5,7).getAppearance().setImageResource(R.drawable.ni_pawnw);




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



