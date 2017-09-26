package edu.upenn.cis573.travelingsalesman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

public class MainActivity extends ActionBarActivity implements OnItemSelectedListener {

    // represents the number of locations in the game map
//    protected static int numLocations = 4;
    public int numLocations ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //saves state if orientation changes for ex
        setContentView(R.layout.activity_main); //layout resource defining UI to be inflated

        // Code to create the spinner (drop-down list)
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /*
    This method is called when the user clicks the "Play" button
     */
    public void onButtonClick(View button) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("Number of locations", numLocations) ;
        startActivity(i);
    }

    /*
    This method is called when the user chooses something from the Spinner -- sets number of locations
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        numLocations = Integer.parseInt(parent.getItemAtPosition(pos).toString());
//        Intent intent1 = new Intent() ;//step 1
//        intent1.putExtra("Number of locations", numLocations) ;//step 1
//        setResult(RESULT_OK, intent1);//step 1
    }

    /*
    Not used but need it for the interface.
    */
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
