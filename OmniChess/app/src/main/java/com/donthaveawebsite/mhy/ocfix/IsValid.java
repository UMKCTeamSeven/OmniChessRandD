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
    piecetype piecetypes;
    //Constructors
    public IsValid(){}

    public IsValid(Piece piece)
    {this.piecetypes = piece.type.piecetypes;}

    public IsValid(piecetype piecetypes)
    {this.piecetypes = piecetypes;}
    //Methods
    public boolean IsMoveValid(Piece piece, Spot Source, Spot Destination)
    {
        IsValid thetype = new IsValid(piece);
        return thetype.Verify(piece, Source, Destination);
    }



        public boolean Verify(Piece piece, Spot Source, Spot Destination)
         {
            switch(piecetypes)
            {
                case pawn:
                    //oh shit how does casting work in java
                    //This might have to change...
                    //pseudo code

                    //Still needs diagonal attacking, and en passant
                    //Only works for pawns going from L to R


                    return PawnLogicCheck(piece, Source, Destination);




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

    private boolean PawnLogicCheck(Piece piece, Spot Source, Spot Destination) {
        if ('B' == piece.getcolor(piece)) {
            if (piece.getMC() == 0) {   //Going up the board TODO reverse logic for going up the board.
                if (!Destination.isOccupied())
                    return ((((Destination.y - 2) == Source.y) || ((Destination.y - 1) == Source.y)) && Destination.x == Source.x && !Destination.isOccupied() );
                else
                    return ((Destination.x - 1 == Source.x || Destination.x + 1 == Source.x) && ((Destination.y - 1) == Source.y));
            }   //above line is a diagonal attack

            else {
                if (!Destination.isOccupied())
                    return ((Destination.y - 1 == Source.y) && Destination.x == Source.x && !Destination.isOccupied());


                return ((Destination.y - 1 == Source.y) && ((Destination.x - 1 == Source.x || Destination.x + 1 == Source.x)));
            }
        }
        else //If ever 3+ colors make into switch
        {
            if (piece.getMC() == 0) {   //Going up the board TODO reverse logic for going up the board.
                if (!Destination.isOccupied())
                    return ((((Destination.y + 2) == Source.y) || ((Destination.y + 1) == Source.y)) && Destination.x == Source.x && !Destination.isOccupied());
                else
                    return ((Destination.x - 1 == Source.x || Destination.x + 1 == Source.x) && ((Destination.y + 1) == Source.y));
            }   //above line is a diagonal attack

            else {
                if (!Destination.isOccupied())
                    return ((Destination.y + 1 == Source.y) && Destination.x == Source.x);
                return ((Destination.y + 1 == Source.y) && ((Destination.x - 1 == Source.x || Destination.x + 1 == Source.x)));
        }
    }


}}
