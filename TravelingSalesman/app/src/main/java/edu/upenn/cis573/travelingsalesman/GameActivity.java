package edu.upenn.cis573.travelingsalesman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class GameActivity extends ActionBarActivity {

    public int numLocations; //step 1 -- high delegation
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //step 1 - receiver of Intent info
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        //=========way 1====================
//        numLocations = getIntent().getIntExtra("Number of locations", 4) ; step 1 - way 1
        //=========way 2=====================
//        Intent i = getIntent() ; //step 1 - way 2
//        numLocations = i.getIntExtra("Number of locations", 4) ; //step 1 -- way 2
        //==========way 3=====================
        if(savedInstanceState == null){ //if no data supplied --step 1 high delegation- way 3
            extras = getIntent().getExtras();
            if(extras != null){
                numLocations = extras.getInt("Number of locations");
            }
        }else{
            numLocations = (int) savedInstanceState.getSerializable("Number of locations");
        }
        GameView gv = (GameView)findViewById(R.id.gameView); //gets the View that was created above by setContentView
        gv.setSpinnerNum(numLocations);
        gv.drawInitialPoints();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    /*
    This method is called when the user chooses something in the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_clear) {
            GameView gv = (GameView)findViewById(R.id.gameView);
            gv.segments.clear(); //segments = arraylist of 2 point array
            gv.invalidate();
            return true;
        }
        else if (id == R.id.menu_quit) {
            finish();
            return true;
        } else if (id == R.id.menu_undo) {
            GameView gv = (GameView)findViewById(R.id.gameView);
            if (gv.segments.size() > 0) {
                gv.segments.remove(gv.segments.get(gv.segments.size() - 1));
            } else {
                Toast.makeText(gv.getContext(), "There's nothing to undo.", Toast.LENGTH_LONG).show();
            }
            gv.invalidate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
