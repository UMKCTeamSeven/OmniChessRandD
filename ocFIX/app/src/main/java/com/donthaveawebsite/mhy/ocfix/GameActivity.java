package com.donthaveawebsite.mhy.ocfix;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import junit.framework.Assert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameActivity extends Activity

{
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
            spot.getAppearance().setImageResource(R.drawable.ni_tsquare);
           // String text = spot.getAppearance().toString() + " " + spot.getSelector().toString();

        }
        Pawn testpawn = new Pawn(true,1,1,1);
        theboard.getSpot(1,1).placePiece(testpawn);
        theboard.getSpot(1,1).getAppearance().setImageResource(R.drawable.ni_pawn);


        theboard.getSpot(3,1).placePiece(testpawn);
        theboard.getSpot(3,1).getAppearance().setImageResource(R.drawable.ni_pawn);

        theboard.getSpot(5,1).placePiece(testpawn);
        theboard.getSpot(5,1).getAppearance().setImageResource(R.drawable.ni_pawn);

    }








    }//endclass



