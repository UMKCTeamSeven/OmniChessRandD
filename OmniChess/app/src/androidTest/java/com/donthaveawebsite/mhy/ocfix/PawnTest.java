package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * Created by Matthew on 4/21/2015.
 */
public class PawnTest extends AndroidTestCase

{
    private IsValid isValid;
    private Mover theMove;
    private Pawn thepawn;
    private Spot thespot;

    public void setUp() {

        theMove = new Mover();
        thepawn = new Pawn(true, 0, 0, 0);
        thespot = new Spot(0,0,0);
        thespot.placePiece(thepawn);
    }

    public void tearDown(  ) {
        // If class under test had any cleanup methods, we would call
        //   them here.

    }

   public void testPawnFirstMove()
    {
        Spot cango = new Spot(0,2,0);
        Assert.assertTrue(thepawn.FirstMove(thepawn));

    }
    public void testPawnMC()
    {//Tests MC getter and setter
        thepawn = new Pawn(true, 0, 0, 0);
        thepawn.setMC(4);
        Spot cantgo = new Spot(0,2,0);
        Assert.assertFalse(thepawn.getMC() == 0);
        Assert.assertTrue(thepawn.getMC() == 4);
        Assert.assertFalse(thepawn.FirstMove(thepawn));

     }
    public void testPawnOnMove()
    {
        thepawn = new Pawn(true, 0, 0, 0);
        thespot.placePiece(thepawn);
        thepawn.setMC(4);
        Spot cantgo = new Spot(0,2,0);
        thepawn.OnMove(thepawn, thespot, thespot);  //moves piece to same location
        thepawn.OnMove(thepawn, thespot, cantgo);
        Assert.assertFalse(cantgo.isOccupied());
        Spot cango = new Spot(0,1,0);
        thepawn.OnMove(thepawn,thespot,cango);
        Assert.assertTrue(cango.isOccupied());
    }

}