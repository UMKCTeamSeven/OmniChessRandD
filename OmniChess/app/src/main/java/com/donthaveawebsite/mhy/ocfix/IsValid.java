package com.donthaveawebsite.mhy.ocfix;

import android.support.annotation.Nullable;

import java.util.SortedMap;

/**
 * Created by Matthew on 3/29/2015.
 */

public class IsValid
        //determines if move is valid
{
    public enum piecetype {
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
    public IsValid() {
    }

    public IsValid(Piece piece) {
        this.piecetypes = piece.type.piecetypes;
    }

    public IsValid(piecetype piecetypes) {
        this.piecetypes = piecetypes;
    }

    //Methods
    public boolean IsMoveValid(Piece piece, Spot Source, Spot Destination, Board theboard) {
        IsValid thetype = new IsValid(piece);
        return thetype.Verify(piece, Source, Destination, theboard);
    }

    public boolean Verify(Piece piece, Spot Source, Spot Destination, Board theboard) {
        switch (piecetypes) {
            case pawn:
                //Does not have enpasant
                return PawnLogic(piece, Source, Destination, theboard);
            //return PawnLogicCheck(piece, Source, Destination, theboard);  //original method before portals
            case king:
                return KingMe(piece, Source, Destination, theboard);

            case knight:
                return KnightLogicCheck(piece, Source, Destination);
            case bishop:
                return BishopLogicCheck(piece, Source, Destination, theboard);
            case rook:
                try
                {
                    return RookLogicCheck(piece, Source, Destination, theboard);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            case queen:
                try {
                    return (((BishopLogicCheck(piece, Source, Destination, theboard)) || (RookLogicCheck(piece, Source, Destination, theboard))));
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            case portal:
                //portals can be moved to an empty space during your turn, but cost your turn. For testing mainly
                return portalLogicCheck(piece, Source, Destination, theboard);
            default:
                break;

        }

        return false; //Default returns false, piece type not added, or shouldnt have been selected, off to the debug you go
    }

    private boolean KingMe(Piece piece, Spot source, Spot destination, Board theboard)
    {
        if (destination.isOccupied() && (destination.getpiece().getcolor(destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        if  (source.y != 7)
        {
            if (OneN(source, theboard) == destination)
                    return true;
            if (source.x != 0 && (OneLN(source, theboard) == destination))
                    return true;
            if (source.x != 7 && (OneRN(source, theboard) == destination))
                    return true;
        }
            if (source.y != 0)
            {
                if ((OneS(source, theboard) == destination))
                    return true;

                if (source.x != 0 && (OneLS(source, theboard) == destination))
                    return true;

                if (source.x != 7 && (OneRS(source, theboard) == destination))
                    return true;
            }

            if (source.x != 7) {
                if (OneR(source, theboard) == destination)
                    return true;
                if (source.y != 0 && (OneRS(source, theboard) == destination))
                    return true;

                if (source.y != 7 && (OneRN(source, theboard) == destination))
                    return true;

            }

            if (source.x != 0) {
                if (OneL(source, theboard) == destination)
                    return true;
                if (source.y != 0 && (OneLS(source, theboard) == destination))
                    return true;

                if (source.y != 7 && (OneLN(source, theboard) == destination))
                    return true;

            }
    return false;
    }





    public Spot OneN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x, (Source.y + 1))))
        {
            if(theboard.getSpot(Source.x, (Source.y + 1)).getpiece().GetRelated().getY() == 7 )
            {return null;}
            return (OneN(theboard.getSpot(Source.x, (Source.y + 1)).getpiece().GetRelated().getCurrentlocation(), theboard));
        }
        //original logic before portals
        return (theboard.getSpot(Source.x, (Source.y + 1)));
    }

    public Spot OneS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x, (Source.y - 1))))
        {
            if(theboard.getSpot(Source.x, (Source.y - 1)).getpiece().GetRelated().getY() == 0)
            {
                return null;
            }
            return (OneS(theboard.getSpot(Source.x, (Source.y - 1)).getpiece().GetRelated().getCurrentlocation(), theboard));
        }
        //original logic before portals
        return (theboard.getSpot(Source.x, (Source.y - 1)));
    }

    public Spot OneR(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x + 1, (Source.y))))
        {
            if(theboard.getSpot(Source.x +1, (Source.y)).getpiece().GetRelated().getX() == 7 )
            {return null;}
            return OneR(theboard.getSpot(Source.x + 1, (Source.y)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x + 1, (Source.y)));
    }

    public Spot OneL(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x - 1, (Source.y))))
        {
            if(theboard.getSpot(Source.x -1, (Source.y)).getpiece().GetRelated().getX() == 0 )
            {return null;}
            return OneL(theboard.getSpot(Source.x - 1, (Source.y)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y)));
    }

    public Spot OneLN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x - 1, (Source.y + 1)))) {
            return OneLN(theboard.getSpot(Source.x - 1, (Source.y + 1)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y + 1)));
    }

    public Spot OneLS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {

        if (IsPortal(theboard.getSpot(Source.x - 1, (Source.y - 1)))) {
            return OneLS(theboard.getSpot(Source.x - 1, (Source.y - 1)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y - 1)));
    }

    public Spot OneRS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x + 1, (Source.y - 1))))
        {
            if(theboard.getSpot(Source.x +1 , (Source.y - 1)).getpiece().GetRelated().getY() == 0 || theboard.getSpot(Source.x+1, (Source.y - 1)).getpiece().GetRelated().getX() == 7  )
            {return null;}
            return OneRS(theboard.getSpot(Source.x + 1, (Source.y - 1)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x + 1, (Source.y - 1)));
    }

    public Spot OneRN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x + 1, (Source.y + 1))))
        {
            if(theboard.getSpot(Source.x +1 , (Source.y + 1)).getpiece().GetRelated().getY() == 7 || theboard.getSpot(Source.x+1, (Source.y + 1)).getpiece().GetRelated().getX() == 7  )
            {return null;}
            return OneRN(theboard.getSpot(Source.x + 1, (Source.y + 1)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x + 1, (Source.y + 1)));
    }

    public boolean IsEdge(Spot spot, Board theboard) {
        if ((spot.x == 0 || spot.x == 7)) {
            return true;
        }
        if ((spot.y == 0 || spot.y == 7)) {
            return true;
        }
        return false;
    }

    public boolean IsPortal(Spot spot) {
        if (spot.isOccupied())
        {
            return spot.getpiece().getcolor(spot.getpiece()) == 'P';
        }
        return false;
    }

    private boolean portalLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {
        if (Destination.isOccupied())
            return false;
        return true;
    }

    private boolean RookLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        return rookPortal(Source, Destination, theboard);
    }


    private Boolean bishopPortal(Spot Source, Spot Destination, Board theboard)
    {
        Spot Sspot = Source;
        if (Source.x != 0 && Source.y != 7)
        {
            while (true) {

                if (OneLN(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneLN(Sspot, theboard).isOccupied()  || IsEdge(OneLN(Sspot, theboard), theboard)  )
                {
                    break;
                }
                Sspot = OneLN(Sspot, theboard);
            }
        }

        Sspot = Source;
        if (Source.x != 7 && Source.y != 0)
        {
            while (true) {

                if (OneRS(Sspot, theboard) == null)
                {
                    break;
                }
                if (OneRS(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneRS(Sspot, theboard).isOccupied()  || IsEdge(OneRS(Sspot, theboard), theboard))
                {
                    break;
                }
                Sspot = OneRS(Sspot, theboard);
            }
        }

        Sspot = Source;
        if (Source.x != 7 && Source.y != 7)
        {
            while (true) {

                if (OneRN(Sspot, theboard) == null)
                {
                    break;
                }
                if (OneRN(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneRN(Sspot, theboard).isOccupied() || IsEdge(OneRN(Sspot, theboard), theboard))
                {
                    break;
                }
                Sspot = OneRN(Sspot, theboard);
            }
        }
        Sspot = Source;
        if (Source.x != 0 && Source.y != 0)
        {
            while (true) {

                if (OneLS(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneLS(Sspot, theboard).isOccupied() || IsEdge(OneLS(Sspot, theboard), theboard)) {
                    break;
                }
                Sspot = OneLS(Sspot, theboard);
            }
        }




        return false;
    }

    private Boolean rookPortal(Spot Source, Spot Destination, Board theboard)  {
        Spot Sspot = Source;
        if (Source.y != 7)
        {
            while (true)
            {
                if (OneN(Sspot, theboard) == null)
                {
                    break;
                }
                if (OneN(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneN(Sspot, theboard).isOccupied() || OneN(Sspot, theboard).y == 7) {
                    break;
                }
                Sspot = OneN(Sspot, theboard);
            }
        }

        Sspot = Source;
        if (Source.y != 0)
        {
            while(true)
            {
                if (OneS(Sspot, theboard) == null)
                {
                    break;
                }
                if (OneS(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneS(Sspot, theboard).isOccupied() || OneS(Sspot, theboard).y == 0) {
                    break;
                }
                Sspot = OneS(Sspot, theboard);
            }
        }
        Sspot = Source;
        if (Source.x != 0)
        {
            while(true)
            {
                if(OneL(Sspot, theboard)== null)
                {
                    break;
                }
                if (OneL(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneL(Sspot, theboard).isOccupied() || OneL(Sspot, theboard).x == 0) {
                    break;
                }
                Sspot = OneL(Sspot, theboard);
            }
        }
        Sspot = Source;
        if (Source.x != 7)
        {
            while(true)
            {
                if (OneR(Sspot, theboard) == null)
                {
                    break;
                }
                if (OneR(Sspot, theboard) == Destination) {
                    return true;
                }
                if (OneR(Sspot, theboard).isOccupied() || OneR(Sspot, theboard).x == 7) {
                    break;
                }
                Sspot = OneR(Sspot, theboard);
            }
        }
        return false;
    }

    private boolean BishopLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        try {
            return bishopPortal(Source, Destination, theboard);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }


    private boolean KnightLogicCheck(Piece piece, Spot Source, Spot Destination) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;
        return ((((Destination.x + 1 == Source.x) || (Destination.x - 1) == Source.x) && ((Destination.y + 2 == Source.y) || Destination.y - 2 == Source.y)) ||
                ((Destination.x + 2 == Source.x) || (Destination.x - 2) == Source.x) && ((Destination.y + 1 == Source.y) || Destination.y - 1 == Source.y));
    }

    private boolean PawnLogic(Piece piece, Spot Source, Spot Destination, Board theboard)//todo enpasant
    {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        if ('B' == piece.getcolor(piece)) {
            if (Destination == OneN(Source, theboard) && !(Destination.isOccupied())) //No matter what pawns can move forward one empty square
            {
                return true;
            }
            if (BPawnAttack(Source, Destination, theboard)) return true;

            if (piece.getMC() == 0) {   //Going up the board
                if (!Destination.isOccupied()) {
                    if (Destination == OneN(OneN(Source, theboard), theboard) && !(OneN(Source, theboard).isOccupied())) {
                        return true;
                    }
                }
            }
            return false;
        }
        if ('W' == piece.getcolor(piece)) {
            if (Destination == OneS(Source, theboard) && !(Destination.isOccupied())) //No matter what pawns can move forward one empty square
            {
                return true;
            }
            if (WPawnAttack(Source, Destination, theboard)) return true;

            if (piece.getMC() == 0) {   //Going down the board
                if (!Destination.isOccupied()) {
                    if (Destination == OneS(OneS(Source, theboard), theboard) && !(OneS(Source, theboard).isOccupied())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private boolean BPawnAttack(Spot Source, Spot Destination, Board theboard) {
        if (!(IsEdge(Source, theboard))) {
            if ((Destination == OneLN(Source, theboard) || Destination == OneRN(Source, theboard)) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {

                return true;
            } //diagonal attack nonedge
        } else {
            //Edge diagonal logic for pawns
            if (Source.x == 0) {
                if (Destination == OneRN(Source, theboard) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {
                    return true;
                } //diagonal attack
            } else {
                if (Destination == OneLN(Source, theboard) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {
                    return true;
                } //diagonal attack

            }
        }
        return false;
    }

    private boolean WPawnAttack(Spot Source, Spot Destination, Board theboard) {
        if (!(IsEdge(Source, theboard))) {
            if ((Destination == OneLS(Source, theboard) || Destination == OneRS(Source, theboard)) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {

                return true;
            } //diagonal attack nonedge
        } else {
            //Edge diagonal logic for pawns
            if (Source.x == 0) {
                if (Destination == OneRS(Source, theboard) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {
                    return true;
                } //diagonal attack
            } else {
                if (Destination == OneLS(Source, theboard) && Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) != 'P')) {
                    return true;
                } //diagonal attack

            }
        }
        return false;
    }}

