package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

import junit.framework.Assert;

public class BoardTest extends AndroidTestCase
{
    public void testBoardConstructor()
    {
        Board theBoard = new Board(4);
        Pawn thepawn = new Pawn(true, 0, 0, 0);
        theBoard.getSpot(4,4).placePiece(thepawn);
        Assert.assertTrue(thepawn.getZ() == 4);
        Assert.assertTrue(theBoard.getSpotState(4,4) == 2);
        Assert.assertTrue( theBoard.getSpotPiece(4,4) == thepawn);
        Assert.assertTrue( theBoard.removePiece(4,4).piece == theBoard.getSpot(4,4).piece );

    }

}
