package com.donthaveawebsite.mhy.ocfix;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.EXITTEXT:
                finish();
                break;

            case R.id.NEWGAMETEXT:
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                startActivity(intent);
                break;

            case R.id.ACHIEVETEXT:
                intent = new Intent(v.getContext(), AchievementsActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView mExitText = (TextView) findViewById(R.id.EXITTEXT);
        mExitText.setOnClickListener(this);

        TextView mStartGameText = (TextView) findViewById(R.id.NEWGAMETEXT);
        mStartGameText.setOnClickListener(this);

        TextView mAchieve = (TextView) findViewById(R.id.ACHIEVETEXT);
        mAchieve.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.about_settings) {
            Intent intent = new Intent(this, AboutOmniChess.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

}
