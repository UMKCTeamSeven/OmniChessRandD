package com.donthaveawebsite.mhy.ocfix;

import java.util.PriorityQueue;

/**
 * Created by Matthew on 3/29/2015.
 */
public class Pawn extends Piece {

    public Pawn(Boolean available, int x, int y, int z) {
        super(available, x, y, z, R.drawable.ni_pawn);
        this.type = new IsValid(IsValid.piecetype.pawn);
    }

    public boolean FirstMove(Pawn pawn)  //because they have special move rules.
    {
        return pawn.getMC() == 0;
    }

    public Piece Promote(Piece pawn, int PromotionSelection) {
        if (PromotionSelection == 0)
        {
            pawn = new Bishop(true, pawn.getX(), pawn.getY(), pawn.getZ());
        } else if (PromotionSelection == 1) {
            pawn = new Rook(true, pawn.getX(), pawn.getY(), pawn.getZ());
        } else if (PromotionSelection == 2) {
            pawn = new Knight(true, pawn.getX(), pawn.getY(), pawn.getZ());
        } else if (PromotionSelection == 3) {
            pawn = new Queen(true, pawn.getX(), pawn.getY(), pawn.getZ());
        } else {
            return pawn;
        }
        return pawn;
    }

    @Override
    public void OnMove(Piece pawn, Spot source, Spot destination, Board theboard) {
        if (source == destination) {
            //exit selected mode
            return;
        }

        Mover theMove = new Mover();
        boolean moved = theMove.TryMove(pawn, source, destination, theboard);
        try {
            if (moved)
            {

                pawn.setMC(pawn.getMC() + 1);
                if (destination.isOccupied())
                {
                    destination.releaseSpot();
                    promotionCheck(pawn, destination);
                    destination.placePiece(pawn);

                }
                else
                {
                    promotionCheck(pawn, destination);
                    destination.placePiece(pawn);
                }
                //update view.
            } else {
            }
        }        //end if block      //end try block   //return to selected mode TODO fill else block with return to selected mode
        catch (NullPointerException objectmissing) {
        }

    }

    private void promotionCheck(Piece pawn, Spot destination) {
        if ((destination.y == 7 || destination.y == 0))
        {
            pawn = Promote(pawn, 1);
            if (destination.y ==7)
            {
                pawn.switchcolor();
            }
            destination.getAppearance().setImageResource(pawn.getIcon());
        }
    }
}