package com.example.myfirstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
//import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by josephhaymaker on 11/27/17.
 */

public class CheckBoxTutorialActivity extends Activity {
//    private CheckBox chkIos, chkAndroid, chkWindows;
//    private Button btnDisplay;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_message);
//
//        addListenerOnChkIos();
//        addListenerOnButton();
//    }
//
//    public void addListenerOnChkIos() {
//
//        chkIos = (CheckBox) findViewById(R.id.chkIos);
//
//        chkIos.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //is chkIos checked?
//                if (((CheckBox) v).isChecked()) {
//                    Toast.makeText(CheckBoxTutorialActivity.this,
//                            "Bro, try Android :)", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//
//    }
//
//    public void addListenerOnButton() {
//        chkIos = (CheckBox) findViewById(R.id.chkIos);
//        chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
//        chkWindows = (CheckBox) findViewById(R.id.chkWindows);
//        btnDisplay = (Button) findViewById(R.id.btnDisplay);
//
//        btnDisplay.setOnClickListener(new OnClickListener() {
//
//            //Run when button is clicked
//            @Override
//            public void onClick(View v) {
//
//                StringBuffer result = new StringBuffer();
//                result.append("IPhone check : ").append(chkIos.isChecked());
//                result.append("\nAndroid check : ").append(chkAndroid.isChecked());
//                result.append("\nWindows Mobile check :").append(chkWindows.isChecked());
//
//                Toast.makeText(CheckBoxTutorialActivity.this, result.toString(),
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }
//// ===========================================
//    TextView tv;
//    CheckBox cbS;
//    CheckBox cbW;
//    View.OnClickListener checkBoxListener;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_message);
//
//        final CheckBox checkBox = (CheckBox) findViewById(R.id.tvDetails);
//        if (checkBox.isChecked()) {
//            checkBox.setChecked(false);
//        }

    //============================
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_message);
//        cbS = (CheckBox) findViewById(R.id.cbSuvendu);
//        cbW = (CheckBox) findViewById(R.id.cbWordPress);
//
//        checkBoxListener = new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//                tv = (TextView) findViewById(R.id.tvDetails);
//                tv.setText("I Like ");
//
//                if (cbS.isChecked()) {
//                    tv.setText(tv.getText().toString() + " " + cbS.getText().toString());
//                }
//
//                if (cbW.isChecked()) {
//                    tv.setText(tv.getText().toString() + " " + cbW.getText().toString());
//                }
//
////                if(!cbS.isChecked()&amp;&amp;!cbW.isChecked()) {
////                    tv.setText("");
////                }
//            }
//        };
//        cbS.setOnClickListener(checkBoxListener);
//        cbW.setOnClickListener(checkBoxListener);
//    }


}




