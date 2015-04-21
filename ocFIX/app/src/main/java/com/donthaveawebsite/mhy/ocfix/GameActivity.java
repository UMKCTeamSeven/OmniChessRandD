package com.donthaveawebsite.mhy.ocfix;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import junit.framework.Assert;

public class GameActivity extends Activity implements View.OnClickListener

{
    final Spot mStarting2 = new Spot(0, 0, 0);
    final Spot space1 = new Spot(1, 0, 0);
    final Spot space2 = new Spot(2, 0, 0);
    final Spot space3 = new Spot(3, 0, 0);

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.StartingPoint:


            if (mStarting2.SpotState == 0)
            {
                //valid move
            }
            if (mStarting2.SpotState == 1)//Empty square that might someday have a piece

            {
                    //ifmovevalidmovehere
                    //ifinselectionmode do nothing
                break;
            }


            if (mStarting2.SpotState == 2) { //piece waiting to be selected
                mStarting2.SpotState = 3;
                space1.appearance.setImageResource(R.drawable.ni_greensquare);
                space2.appearance.setImageResource(R.drawable.ni_greensquare);
                space1.SpotState = 0;
                space2.SpotState = 0;

                break;
            }

            if (mStarting2.SpotState == 3) //selected
            {
                mStarting2.SpotState = 2;
                space1.appearance.setImageResource(R.drawable.ni_blacksquare);
                space2.appearance.setImageResource(R.drawable.ni_whitesquare);
                space1.SpotState = 1;
                space2.SpotState = 1;
                break;
            }


            case R.id.forward1:

            if (space1.SpotState == 0)//valid
            {
                mStarting2.SpotState = 1;
                space2.SpotState = 1;
                space1.SpotState = 2;
                mStarting2.appearance.setImageResource(R.drawable.ni_whitesquare);
                space1.appearance.setImageResource(R.drawable.ni_pawn);  //won't look right, as currently our graphic for pawn only has a white background
                space2.appearance.setImageResource(R.drawable.ni_whitesquare);

                break;

            }
            if (space1.SpotState == 1)//empty
            {
                break;

            }
            if(space1.SpotState == 2) //haspiece
            {

                space2.appearance.setImageResource(R.drawable.ni_greensquare);
                       space2.SpotState = 0;
                space1.SpotState = 3;
                break;
            }
            if (space1.SpotState == 3) { //selected
                space1.SpotState = 2;
                space2.appearance.setImageResource(R.drawable.ni_whitesquare);
                space2.SpotState = 1;
                break;
            }
                break;

            case R.id.forward2:

                if (space2.SpotState == 0)//valid
                {
                    mStarting2.SpotState = 1;
                    space1.SpotState = 1;
                    space2.SpotState = 2;

                    mStarting2.appearance.setImageResource(R.drawable.ni_whitesquare);
                    space1.appearance.setImageResource(R.drawable.ni_blacksquare);
                    space2.appearance.setImageResource(R.drawable.ni_pawn);

                    break;

                }
                if (space2.SpotState == 1)//empty
                {
                    break;

                }
                if(space2.SpotState == 2) //haspiece
                {

                    space3.appearance.setImageResource(R.drawable.ni_greensquare);
                    space3.SpotState = 0;
                    space2.SpotState = 3;
                    break;
                }
                if (space2.SpotState == 3) { //selected
                    space2.SpotState = 2;
                    space3.appearance.setImageResource(R.drawable.ni_blacksquare);
                    space3.SpotState = 1;
                    break;
                }
                break;

            case R.id.forward3:

                if (space3.SpotState == 0)//valid
                {

                    space2.SpotState = 1;
                    space3.SpotState = 2;
                    space2.appearance.setImageResource(R.drawable.ni_whitesquare);
                    space3.appearance.setImageResource(R.drawable.ni_pawn);
                    break;

                }
                if (space2.SpotState == 1)//empty
                {
                    break;

                }
                if(space2.SpotState == 2) //haspiece
                {

                    //TBD.appearance.setImageResource(R.drawable.ni_greensquare);
                    space2.SpotState = 3;
                    break;
                }
                if (space2.SpotState == 3) { //selected
                    space2.SpotState = 2;
                    //TBD

                    break;
                }

                break;

            default:
                break;


        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);


          //orgin for design 2 of orignal functionality below
        Pawn firstpawn = new Pawn(true, 0, 0, 0);



        mStarting2.piece = firstpawn;
        Assert.assertTrue(mStarting2.isOccupied() && mStarting2.piece == firstpawn); //verify that spot has the piece we think it has
        Assert.assertTrue(firstpawn.getX() == mStarting2.x && firstpawn.getY() == mStarting2.y && firstpawn.getZ() == mStarting2.z);  //verify that spot and piece coordinates match



        mStarting2.SpotState = 2;
        mStarting2.appearance = (ImageView) findViewById(R.id.StartingPoint);
            space1.appearance = (ImageView) findViewById(R.id.forward1);
            space2.appearance = (ImageView) findViewById(R.id.forward2);
            space3.appearance = (ImageView) findViewById(R.id.forward3);
        mStarting2.appearance.setOnClickListener(this);
            space1.appearance.setOnClickListener(this);
            space2.appearance.setOnClickListener(this);
            space3.appearance.setOnClickListener(this);

    }








    }//endclass



