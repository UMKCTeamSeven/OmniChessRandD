package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 4/26/2015.
 */

import android.test.AndroidTestCase;

import junit.framework.Assert;

public class SelectorTest extends AndroidTestCase

{
    public void testSelectorFilter()
    {   //Filter should return false if the spot is empty or the piece is unavailable.
        Pawn pawn = new Pawn(true, 0,0,0);
        Spot firstSpot = new Spot(0,0,0);
        Selector selecter = new Selector();
        Assert.assertFalse(selecter.Filter(firstSpot));

        firstSpot.placePiece(pawn);
        Assert.assertTrue(selecter.Filter(firstSpot));  //Available piece on spot

        pawn.setAvailable(false);
        Assert.assertFalse(selecter.Filter(firstSpot));
    }

    public void testSelectorSelectedMode()
    {

    }
}
