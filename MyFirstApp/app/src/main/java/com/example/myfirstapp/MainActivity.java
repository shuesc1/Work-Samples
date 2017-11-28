package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sends a message when the user clicks the 'Send' button
     */
    public void sendMessage(View view){
        //do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class) ;
        EditText editText = (EditText) findViewById(R.id.editText) ;
        String message = editText.getText().toString() ;
        intent.putExtra(EXTRA_MESSAGE, message) ;
        startActivity(intent);
    }
}
