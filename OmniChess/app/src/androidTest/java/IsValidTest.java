import android.test.AndroidTestCase;

import com.donthaveawebsite.mhy.ocfix.Board;
import com.donthaveawebsite.mhy.ocfix.Pawn;
import com.donthaveawebsite.mhy.ocfix.Spot;

/**
 * Created by Matthew on 4/26/2015.
 */
public class IsValidTest extends AndroidTestCase
{
    public void testVerify()
    {//Todo fix unit test
        Board theboard;
        Pawn firstpawn = new Pawn(true,0,0,0);
        Pawn secondpawn = new Pawn(true, 1,1,0);
        Spot firstSpot = new Spot(0,0,0);
        firstSpot.placePiece(firstpawn);
        Spot secondSpot = new Spot(1,1,0);
        secondSpot.placePiece(secondpawn);//diag bot left to top right

       // firstpawn.OnMove(firstpawn, firstSpot, secondSpot, theboard);
    }
}
