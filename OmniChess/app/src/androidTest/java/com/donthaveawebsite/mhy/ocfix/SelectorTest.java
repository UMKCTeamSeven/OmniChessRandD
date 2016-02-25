package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 4/26/2015.
 */

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class SelectorTest extends AndroidTestCase

{
   /* public void testSelectorFilter()
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
        //SelectedMode should highlight valid moves for the selected piece.
        //SelectedMode should update the board if a valid move is selected then exit selected mode
        //For now we are testing just with pawns on first move with no attack options
        //This test will need to be expanded as pieces are added.
        List<Spot> spots = new ArrayList<Spot>();
        Board theboard = new Board(1, spots);

        Pawn pawn = new Pawn(true, 0,0,0);
        Spot firstSpot =  theboard.spots.get(0);
        IsValid typeofpiece = new IsValid(pawn);
        firstSpot.placePiece(pawn);
        Selector selecter = new Selector();
        Spot secondSpot = theboard.spots.get(1);
        Spot thirdSpot =  theboard.spots.get(2);
        if (selecter.Filter(firstSpot))
              {
                 //selecter.SelectedMode( pawn, firstSpot, theboard.spots);  //Todo fix unit test
              }
        else
        {
            Assert.assertTrue(1 == 2); //Fail test
        }

        Assert.assertTrue(secondSpot.SpotState == 0);
        Assert.assertTrue(thirdSpot.SpotState ==  0);

        Assert.assertTrue(firstSpot.SpotState ==  3);

    }*/
}
