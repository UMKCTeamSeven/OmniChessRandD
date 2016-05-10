package com.donthaveawebsite.mhy.ocfix;

import android.test.AndroidTestCase;

import junit.framework.Assert;

/**
 * Created by Matthew on 4/25/2015.
 */
public class PieceTest extends AndroidTestCase

{


    public void testRelatedPieces()
    {
        Piece num1 = new Piece (true,0,0,0, 99);
        Piece num2 = new Piece (true, 7,7,7, 99);
        num1.SetRelated(num2);
        Assert.assertTrue(num1.GetRelated().getX() == 7);
        Assert.assertTrue(num2.GetRelated().getX() == 0);
    }

    public void testPieceAvailable()
    {
        Piece num1 = new Piece (true,0,0,0, 99);
        Assert.assertTrue(num1.isAvailable());
        num1.setAvailable(false);
        Assert.assertFalse(num1.isAvailable());
    }

    public void testPieceColor()
    {
        Piece num1 = new Piece (true,0,0,0, 99);
        Assert.assertTrue(num1.getcolor(num1) =='B');
        num1.switchcolor();
        Assert.assertTrue(num1.getcolor(num1) =='W');
        num1.switchcolor();
        Assert.assertTrue(num1.getcolor(num1) =='B');
    }

    public void testPieceXYZ()
    {
        Piece num1 = new Piece (true,0,0,0, 99);
        num1.setX(3);
        num1.setY(3);
        num1.setZ(3);
        assertTrue(num1.getX() == 3 && num1.getY() == 3 && num1.getZ() == 3);
    }

}
