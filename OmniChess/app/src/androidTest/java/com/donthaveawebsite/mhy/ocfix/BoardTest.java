package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class BoardTest extends AndroidTestCase
{
    public void testBoardConstructor()
    {
        List<Spot> spots =  new ArrayList<Spot>();

        Board theBoard = new Board(4,spots);
        Pawn thepawn = new Pawn(true, 0, 0, 0);
        theBoard.getSpot(4,4).placePiece(thepawn);
        Assert.assertTrue(thepawn.getZ() == 4);
        Assert.assertTrue(theBoard.getSpotState(4,4) == 2);
        Assert.assertTrue( theBoard.getSpotPiece(4,4) == thepawn);
        Assert.assertTrue( theBoard.removePiece(4,4).piece == theBoard.getSpot(4,4).piece );

    }

}
