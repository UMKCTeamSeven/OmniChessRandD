package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * Created by Matthew on 4/21/2015.
 */
public class PawnTest extends AndroidTestCase

{

    private Mover theMove;
    private Pawn thepawn;
    private Spot thespot;

    public void setUp() {
        thepawn = new Pawn(true, 0, 0, 0);
        theMove = new Mover();
        thespot = new Spot(0,0,0);
        thespot.placePiece(thepawn);
    }

    public void tearDown(  ) {
        // If class under test had any cleanup methods, we would call
        //   them here.

    }

   public void PawnFirstMove()
    {
        Spot cango = new Spot(2,0,0);
        Assert.assertTrue(thepawn.FirstMove(thepawn));
        thepawn.OnMove(thepawn,thespot,cango);
        Assert.assertTrue(cango.isOccupied() && cango.piece == thepawn);
    }

}