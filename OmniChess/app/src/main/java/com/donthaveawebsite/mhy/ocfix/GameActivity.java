package com.donthaveawebsite.mhy.ocfix;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameActivity extends FragmentActivity implements OnClickListener

{
    MyPageAdapter pageadapter;

    private List<Fragment> getFragments()
    {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(MyFragment.newInstance("Fragment 1"));
        fList.add(MyFragment.newInstance("Fragment 2"));
        fList.add(MyFragment.newInstance("Fragment 3"));
        return fList;
    }


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
        if (clickedspot != null && theselector.InSelectedMode() && clickedspot.SpotState == 2 && (selectedpiecespot.piece.getcolor(selectedpiecespot.piece) == clickedspot.piece.getcolor(clickedspot.piece)))
        {
            selectedpiecespot.SpotState = 2;
            theselector.Deselector(selectedpiecespot.getpiece(), selectedpiecespot, listenspots, mainboard);
            selectedpiecespot = null;
            selectPiece(clickedspot);
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

        List<Fragment> fragments = getFragments();
        pageadapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        ViewPager pager =
                (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageadapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        List<Spot> ourspots = new ArrayList<Spot>();
        Board theboard = new Board(1, ourspots);

        //Makes Three layer board
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
//Pieces
        //Portals
        Portal portalA = new Portal(true, 3, 4, 1);
        Portal portalB = new Portal(true, 5, 4, 1, portalA);
        portalA.SetRelated(portalB);

        Portal portalC = new Portal(true, 1, 3, 1);
        Portal portalD = new Portal(true, 6, 3, 1, portalC);
        portalC.SetRelated(portalD);

        theboard.getSpot(3,4).placePiece(portalA);
        theboard.getSpot(3,4).getAppearance().setImageResource(R.drawable.ni_portal);
        theboard.getSpot(5, 4).placePiece(portalB);
        theboard.getSpot(5, 4).getAppearance().setImageResource(R.drawable.ni_portal);

        theboard.getSpot(1,3).placePiece(portalC);
        theboard.getSpot(1,3).getAppearance().setImageResource(R.drawable.ni_fractal);
        theboard.getSpot(6, 3).placePiece(portalD);
        theboard.getSpot(6, 3).getAppearance().setImageResource(R.drawable.ni_fractal);

        
        //queens
        Queen blackQ = new Queen(true,4,0,1);
        Queen whiteQ = new Queen(true,4,7,1);
        whiteQ.switchcolor();
        theboard.getSpot(4,7).placePiece(whiteQ);
        theboard.getSpot(4,7).getAppearance().setImageResource(R.drawable.ni_queenw);
        theboard.getSpot(4,0).placePiece(blackQ);
        theboard.getSpot(4,0).getAppearance().setImageResource(R.drawable.ni_queen);
        //white pawns
        Pawn whitepawn1 = new Pawn(true, 0,6,1);
        Pawn whitepawn2 = new Pawn(true, 1,6,1);
        Pawn whitepawn3 = new Pawn(true, 2,6,1);
        Pawn whitepawn4 = new Pawn(true, 3,6,1);
        Pawn whitepawn5 = new Pawn(true, 4,6,1);
        Pawn whitepawn6 = new Pawn(true, 5,6,1);
        Pawn whitepawn7 = new Pawn(true, 6,6,1);
        Pawn whitepawn8 = new Pawn(true, 7,6,1);

        whitepawn1.switchcolor();
        whitepawn2.switchcolor();
        whitepawn3.switchcolor();
        whitepawn4.switchcolor();
        whitepawn5.switchcolor();
        whitepawn6.switchcolor();
        whitepawn7.switchcolor();
        whitepawn8.switchcolor();

        theboard.getSpot(0, 6).placePiece(whitepawn1);
        theboard.getSpot(0, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(1, 6).placePiece(whitepawn2);
        theboard.getSpot(1, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(2, 6).placePiece(whitepawn3);
        theboard.getSpot(2, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(3, 6).placePiece(whitepawn4);
        theboard.getSpot(3, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(4, 6).placePiece(whitepawn5);
        theboard.getSpot(4, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(5, 6).placePiece(whitepawn6);
        theboard.getSpot(5, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(6, 6).placePiece(whitepawn7);
        theboard.getSpot(6, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);
        theboard.getSpot(7, 6).placePiece(whitepawn8);
        theboard.getSpot(7, 6).getAppearance().setImageResource(R.drawable.ni_pawnw);


        //black pawns


        Pawn testpawn1 = new Pawn(true,0,1,1);
        Pawn testpawn2 = new Pawn(true,1,1,1);
        Pawn testpawn3 = new Pawn(true,2,1,1);
        Pawn testpawn4 = new Pawn(true,3,1,1);
        Pawn testpawn5 = new Pawn(true,4,1,1);
        Pawn testpawn6 = new Pawn(true,5,1,1);
        Pawn testpawn7 = new Pawn(true,6,1,1);
        Pawn testpawn8 = new Pawn(true,7,1,1);


        theboard.getSpot(0, 1).placePiece(testpawn1);
        theboard.getSpot(0,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(1, 1).placePiece(testpawn2);
        theboard.getSpot(1,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(2, 1).placePiece(testpawn3);
        theboard.getSpot(2,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(3, 1).placePiece(testpawn4);
        theboard.getSpot(3,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(4, 1).placePiece(testpawn5);
        theboard.getSpot(4,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(5, 1).placePiece(testpawn6);
        theboard.getSpot(5,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(6, 1).placePiece(testpawn7);
        theboard.getSpot(6,1).getAppearance().setImageResource(R.drawable.ni_pawn);
        theboard.getSpot(7, 1).placePiece(testpawn8);
        theboard.getSpot(7,1).getAppearance().setImageResource(R.drawable.ni_pawn);


        //knights
        Knight whitetestknight = new Knight(true,  1, 7,1);
        Knight testknight = new Knight(true,1,0,1);
        Knight whiteknight = new Knight(true,  6, 7,1);
        Knight blackknight = new Knight(true,6,0,1);

        //rooks
        Rook whitetestrook2 = new Rook(true, 0, 7,1);
        Rook whitetestrook3 = new Rook(true, 7, 7,1);
        Rook blackrook = new Rook(true, 0,0, 1);
        Rook blackrook2 = new Rook(true, 7,0, 1);

 //bishops
        Bishop blackbish = new Bishop(true, 2,0,1);
        Bishop blackbish2 = new Bishop(true, 5,0,1);
        Bishop whitebish = new Bishop(true, 2,7,1);
        Bishop whitebish2 = new Bishop(true, 5,7,1);

        //white pieces need color swap
        whitetestknight.switchcolor();
        whitetestrook2.switchcolor();
        whitetestrook3.switchcolor();
        whiteknight.switchcolor();
        whitebish.switchcolor();
        whitebish2.switchcolor();



        theboard.getSpot(1, 0).placePiece(testknight);
        theboard.getSpot(1,0).getAppearance().setImageResource(R.drawable.ni_bknight);
        theboard.getSpot(1, 7).placePiece(whitetestknight);
        theboard.getSpot(1,7).getAppearance().setImageResource(R.drawable.ni_wknight);

        theboard.getSpot(6, 0).placePiece(blackknight);
        theboard.getSpot(6,0).getAppearance().setImageResource(R.drawable.ni_bknight);
        theboard.getSpot(6, 7).placePiece(whiteknight);
        theboard.getSpot(6,7).getAppearance().setImageResource(R.drawable.ni_wknight);

        theboard.getSpot(2, 0).placePiece(blackbish);
        theboard.getSpot(2,0).getAppearance().setImageResource(R.drawable.ni_bishop);
        theboard.getSpot(5, 0).placePiece(blackbish2);
        theboard.getSpot(5,0).getAppearance().setImageResource(R.drawable.ni_bishop);

        theboard.getSpot(2, 7).placePiece(whitebish);
        theboard.getSpot(2,7).getAppearance().setImageResource(R.drawable.ni_bishopw);
        theboard.getSpot(5, 7).placePiece(whitebish2);
        theboard.getSpot(5,7).getAppearance().setImageResource(R.drawable.ni_bishopw);


        theboard.getSpot(0, 7).placePiece(whitetestrook2);
        theboard.getSpot(0,7).getAppearance().setImageResource(R.drawable.ni_rookw);
        theboard.getSpot(7, 7).placePiece(whitetestrook3);
        theboard.getSpot(7,7).getAppearance().setImageResource(R.drawable.ni_rookw);

        theboard.getSpot(7, 0).placePiece(blackrook2);
        theboard.getSpot(7,0).getAppearance().setImageResource(R.drawable.ni_rook);
        theboard.getSpot(0, 0).placePiece(blackrook);
        theboard.getSpot(0,0).getAppearance().setImageResource(R.drawable.ni_rook);


        Player white = new Player('W');
        Player black = new Player('B');

        Player portals = new Player('P');  //Portals are not actually players... just FYI NPCs



        black.setTurn(true);


        List<Player> players = new ArrayList<Player>();

        players.add(black);
        players.add(white);

        listenspots = ourspots;
        theselector = new Selector(players, ourspots, theboard);


    }








    }//endclass



