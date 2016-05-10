package com.donthaveawebsite.mhy.ocfix;

import android.support.annotation.Nullable;

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
                //needs logic
                break;
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

    public Spot OneN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x, (Source.y + 1))))
        {
            return (OneN(theboard.getSpot(Source.x, (Source.y + 1)).getpiece().GetRelated().getCurrentlocation(), theboard));
        }
        //original logic before portals
        return (theboard.getSpot(Source.x, (Source.y + 1)));
    }

    public Spot OneS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x, (Source.y - 1))))
        {
            return (OneS(theboard.getSpot(Source.x, (Source.y - 1)).getpiece().GetRelated().getCurrentlocation(), theboard));
        }
        //original logic before portals
        return (theboard.getSpot(Source.x, (Source.y - 1)));
    }

    public Spot OneR(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x + 1, (Source.y))))
        {
         return OneR(theboard.getSpot(Source.x + 1, (Source.y)).getpiece().GetRelated().getCurrentlocation(), theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x + 1, (Source.y)));
    }

    public Spot OneL(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (IsPortal(theboard.getSpot(Source.x - 1, (Source.y))))
        {
          return OneL(theboard.getSpot(Source.x - 1, (Source.y)).getpiece().GetRelated().getCurrentlocation() , theboard);
        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y)));
    }


    public Spot OneLN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (theboard.getSpot(Source.x - 1, (Source.y + 1)).isOccupied()) {
            if (theboard.getSpot(Source.x - 1, (Source.y + 1)).getpiece().getcolor(theboard.getSpot(Source.x - 1, (Source.y + 1)).getpiece()) == 'P') {
                return OneLN(theboard.getSpot(theboard.getSpot(Source.x - 1, (Source.y + 1)).getpiece().GetRelated().getX(), theboard.getSpot(Source.x - 1, (Source.y + 1)).getpiece().GetRelated().getY()), theboard);
            }

        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y + 1)));
    }

    public Spot OneLS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {

        if (theboard.getSpot(Source.x - 1, (Source.y - 1)).isOccupied()) {
            if (theboard.getSpot(Source.x - 1, (Source.y - 1)).getpiece().getcolor(theboard.getSpot(Source.x - 1, (Source.y - 1)).getpiece()) == 'P') {
                return OneLS(theboard.getSpot(theboard.getSpot(Source.x - 1, (Source.y - 1)).getpiece().GetRelated().getX(), theboard.getSpot(Source.x - 1, (Source.y - 1)).getpiece().GetRelated().getY()), theboard);
            }

        }
        //original logic before portals
        return (theboard.getSpot(Source.x - 1, (Source.y - 1)));
    }

    public Spot OneRS(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (theboard.getSpot(Source.x + 1, (Source.y - 1)).isOccupied()) {
            if (theboard.getSpot(Source.x + 1, (Source.y - 1)).getpiece().getcolor(theboard.getSpot(Source.x + 1, (Source.y - 1)).getpiece()) == 'P') {
                return OneRS(theboard.getSpot(theboard.getSpot(Source.x + 1, (Source.y - 1)).getpiece().GetRelated().getX(), theboard.getSpot(Source.x + 1, (Source.y - 1)).getpiece().GetRelated().getY()), theboard);
            }

        }
        //original logic before portals
        return (theboard.getSpot(Source.x + 1, (Source.y - 1)));
    }

    public Spot OneRN(Spot Source, Board theboard) throws ArrayIndexOutOfBoundsException {
        if (theboard.getSpot(Source.x + 1, (Source.y + 1)).isOccupied()) {
            if (theboard.getSpot(Source.x + 1, (Source.y + 1)).getpiece().getcolor(theboard.getSpot(Source.x + 1, (Source.y + 1)).getpiece()) == 'P') {
                return OneRN(theboard.getSpot(theboard.getSpot(Source.x + 1, (Source.y + 1)).getpiece().GetRelated().getX(), theboard.getSpot(Source.x + 1, (Source.y + 1)).getpiece().GetRelated().getY()), theboard);
            }
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
        if (Destination.isOccupied() || IsEdge(Destination, theboard))
            return false;
        return true;
    }


    private boolean RookLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        return rookPortal(Source, Destination, theboard);
    }


    private Boolean rookPortal(Spot Source, Spot Destination, Board theboard)
    {
        Spot Sspot = Source;
        while (Sspot.y != 7)
        {
            if (OneN(Sspot, theboard) == Destination)
            {
                return true;
            }
            if (OneN(Sspot, theboard).isOccupied())
            {
                break;
            }
            Sspot = OneN(Sspot, theboard);
        }

        Sspot = Source;
        while (Sspot.y != 0)
        {
            if (OneS(Sspot, theboard) == Destination)
            {
                return true;
            }
            if (OneS(Sspot, theboard).isOccupied())
            {
                break;
            }
            Sspot = OneS(Sspot, theboard);
        }
        Sspot = Source;
        while (Sspot.x != 0) {
            if (OneL(Sspot, theboard) == Destination)
            {
                return true;
            }
            if (OneL(Sspot, theboard).isOccupied())
            {
                break;
            }
            Sspot = OneL(Sspot, theboard);
        }
        Sspot = Source;
        while (Sspot.x != 7)
        {
            if (OneR(Sspot, theboard) == Destination)
            {
                return true;
            }
            if (OneR(Sspot, theboard).isOccupied())
            {
                break;
            }
            Sspot = OneR(Sspot, theboard);
        }
        return false;
    }





    private boolean BishopLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {
        if (Destination.isOccupied() && (Destination.getpiece().getcolor(Destination.getpiece()) == piece.getcolor(piece))) //pieces are same color
            return false;

        try {
            return InBishopPath(Source, Destination, theboard);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }

    private Boolean InBishopPath(Spot Source, Spot Destination, Board theboard) {
        if (Source.y - Destination.y < 0) {
            //NR or NL
            if (Source.x - Destination.x < 0) //going NR
            {
                Spot Sspot = Source;
                while (Sspot != Destination) {
                    if (IsEdge(Sspot, theboard) && Sspot != Source)
                        return false;
                    if (OneRN(Sspot, theboard).isOccupied()) {
                        if (OneRN(Sspot, theboard) == Destination) {
                            return true;
                        }
                        return false;
                    }
                    Sspot = OneRN(Sspot, theboard);
                }
                return true;
            }
            if (Source.x - Destination.x > 0) //going NL
            {
                Spot Sspot = Source;

                while (Sspot != Destination) {
                    if (IsEdge(Sspot, theboard) && Sspot != Source)
                        return false;
                    if (OneLN(Sspot, theboard).isOccupied()) {
                        if (OneLN(Sspot, theboard) == Destination) {
                            return true;
                        }
                        return false;
                    }
                    Sspot = OneLN(Sspot, theboard);
                }
                return true;
            }
        }
        if (Source.y - Destination.y > 0) {
            //SR or SL
            if (Source.x - Destination.x < 0) //going SR
            {
                Spot Sspot = Source;

                while (Sspot != Destination) {
                    if (IsEdge(Sspot, theboard) && Sspot != Source)
                        return false;
                    if (OneRS(Sspot, theboard).isOccupied()) {
                        if (OneRS(Sspot, theboard) == Destination) {
                            return true;
                        }
                        return false;
                    }
                    Sspot = OneRS(Sspot, theboard);
                }
                return true;
            }
            if (Source.x - Destination.x > 0) {
                Spot Sspot = Source;

                while (Sspot != Destination) {
                    if (IsEdge(Sspot, theboard) && Sspot != Source)
                        return false;
                    if (OneLS(Sspot, theboard).isOccupied()) {
                        if (OneLS(Sspot, theboard) == Destination) {
                            return true;
                        }
                        return false;
                    }
                    Sspot = OneLS(Sspot, theboard);
                }
                return true;
            }

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

   /* private boolean PawnLogicCheck(Piece piece, Spot Source, Spot Destination, Board theboard) {

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
*/