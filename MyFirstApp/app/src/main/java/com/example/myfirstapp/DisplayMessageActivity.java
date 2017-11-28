package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup ;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {

    private CheckBox chkChinese, chkAsian, chkAmerican, chkIndian, chkMediterranean, chkMiddleEastern, chkHalal, chkBreakfast, chkMexican, chkVegVegan, chkHealthy;
    private Button saveBtnDisplay, goBackButtonDisplay;
    View.OnClickListener checkBoxListener, saveButtonListener, backButtonListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //checkboxes
        chkChinese = (CheckBox) findViewById(R.id.chkChinese);
        chkAmerican = (CheckBox) findViewById(R.id.chkAmerican);
        chkAsian = (CheckBox) findViewById(R.id.chkAsian);
        chkIndian = (CheckBox) findViewById(R.id.chkIndian);
        chkMediterranean = (CheckBox) findViewById(R.id.chkMediterranean);
        chkMiddleEastern = (CheckBox) findViewById(R.id.chkMdlEstrn);
        chkHalal = (CheckBox) findViewById(R.id.chkHalal);
        chkBreakfast = (CheckBox) findViewById(R.id.chkBreakfast);
        chkMexican = (CheckBox) findViewById(R.id.chkMexican);
        chkVegVegan = (CheckBox) findViewById(R.id.chkVegVegan);
        chkHealthy = (CheckBox) findViewById(R.id.chkHealthy);
        //save and back buttons
        saveBtnDisplay = (Button) findViewById(R.id.saveCategoriesButton);
        goBackButtonDisplay = (Button) findViewById(R.id.goBackButton);

        saveButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisplayMessageActivity.this, "Save successful", 4) ;
            }
        };

        backButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        saveBtnDisplay.setOnClickListener(saveButtonListener);
        goBackButtonDisplay.setOnClickListener(backButtonListener);

    }
}
