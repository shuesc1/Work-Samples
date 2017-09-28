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
        if(savedInstanceState == null){ //if no data supplied --step 1 high delegation- way 3
            extras = getIntent().getExtras();
            if(extras != null){
                numLocations = extras.getInt("Number of locations"); //get info from MainActivity
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
//        ArrayList<> segArr;
        if (id == R.id.menu_clear) {
            GameView gv = (GameView)findViewById(R.id.gameView);
//            segArr = gv.segments.lineSegments ;
            gv.segments.lineSegments.clear(); //segments = arraylist of 2 point array
            gv.invalidate();
            return true;
        }
        else if (id == R.id.menu_quit) {
            finish();
            return true;
        } else if (id == R.id.menu_undo) {
            GameView gv = (GameView)findViewById(R.id.gameView);
            if (gv.segments.lineSegments.size() > 0) {
                //TODO maybe clean this up
                gv.segments.lineSegments.remove(gv.segments.lineSegments.get(gv.segments.lineSegments.size() - 1));
            } else {
                Toast.makeText(gv.getContext(), "There's nothing to undo.", Toast.LENGTH_LONG).show();
            }
            gv.invalidate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
