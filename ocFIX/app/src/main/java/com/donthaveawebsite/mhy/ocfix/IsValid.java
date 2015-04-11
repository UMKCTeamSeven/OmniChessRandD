package com.donthaveawebsite.mhy.ocfix;

/**
 * Created by Matthew on 3/29/2015.
 */
public class IsValid
        //determines if move is valid
{
    public enum piecetype
    {
        pawn,
        king,
        knight,
        bishop,
        rook,
        queen,
        portal
    }
    piecetype piecetype;
         public boolean IsMoveValid(Piece piece, Spot Source, Spot Destination)
         {
            switch(piecetype)
            {
                case pawn:
                    //oh shit how does casting work in java
                    //This might have to change...
                    //pseudo code

                    //Still needs diagonal attacking, and en passant
                    if (piece.getMC() == 0)
                    {
                        return (((Destination.x - 2) == Source.x) || ((Destination.x - 1) == Source.x));
                    }

                    else
                    {
                        return (Destination.x - 1 == Source.x);
                    }

                case king:
                    //needs logic
                    break;
                case knight:
                    //needs logic
                    break;
                case bishop:
                    //needs logic
                    break;
                case rook:
                    //needs logic
                    break;
                case queen:
                    //need logic
                    break;
                case portal:
                    //Can't move portals, can't select portals either, HOW DID THIS HAPPEN? Go debug right meow
                    break;
                default:
                    break;

            }

            return false; //Default returns false, piece type not added, or shouldnt have been selected, off to the debug you go
         }


}
