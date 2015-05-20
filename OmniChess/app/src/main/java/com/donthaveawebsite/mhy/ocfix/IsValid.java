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
    public boolean IsMoveValid(Piece piece, Spot Source, Spot Destination, Board theboard)
    {
        IsValid thetype = new IsValid(piece);
        return thetype.Verify(piece, Source, Destination, theboard);
    }



        public boolean Verify(Piece piece, Spot Source, Spot Destination, Board theboard)
         {
            switch(piecetypes)
            {
                case pawn:
                //Does not have enpasant
                return PawnLogicCheck(piece, Source, Destination, theboard);
                case king:
                    //needs logic
                    break;
                case knight:
                    return KnightLogicCheck(piece, Source, Destination);
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

    private boolean KnightLogicCheck(Piece piece, Spot Source, Spot Destination) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;
        return (    (( (Destination.x +1 == Source.x) || (Destination.x -1)  == Source.x) && ((Destination.y + 2 ==  Source.y) || Destination.y -2 == Source.y)) ||
                ( (Destination.x + 2 == Source.x) || (Destination.x - 2)  == Source.x) && ((Destination.y + 1 ==  Source.y) || Destination.y - 1 == Source.y));
    }

    private boolean PawnLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {

        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;
        if ('B' == piece.getcolor(piece)) {
            if (piece.getMC() == 0) {   //Going up the board
                if (!Destination.isOccupied())
                    return ((((Destination.y - 2) == Source.y) || ((Destination.y - 1) == Source.y)) && Destination.x == Source.x && !Destination.isOccupied() && (!((theboard.getSpot(Source.x, (Source.y +1))).isOccupied())));
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
            if (piece.getMC() == 0) {   //Going down the board
                if (!Destination.isOccupied())
                    return ((((Destination.y + 2) == Source.y) || ((Destination.y + 1) == Source.y)) && Destination.x == Source.x && !Destination.isOccupied() && (!((theboard.getSpot(Source.x, (Source.y -1))).isOccupied())));
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
