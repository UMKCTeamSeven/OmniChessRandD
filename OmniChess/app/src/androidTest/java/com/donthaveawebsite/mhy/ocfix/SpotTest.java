package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

/**
 * Created by Matthew on 4/22/2015.
 */
public class SpotTest extends AndroidTestCase
{
    private Pawn thepawn = new Pawn(true,0,0,0);
    private Spot thespot = new Spot(0,0,0);

    public void testPlacePiece()
    {
        assertFalse(thespot.isOccupied());
        thespot.placePiece(thepawn);
        assertTrue(thespot.isOccupied());
        assertTrue(thespot.piece == thepawn);
    }

    public void testReleaseSpot()
    {
        thespot.placePiece(thepawn);
        assertTrue(thespot.piece == thepawn);
        thespot.releaseSpot();
        assertTrue(thespot.piece == null);
    }


}
