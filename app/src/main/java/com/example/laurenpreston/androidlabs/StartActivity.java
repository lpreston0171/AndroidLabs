package com.example.laurenpreston.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "StartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(ACTIVITY_NAME, "In onCreate()");
        Button startChat = (Button)findViewById(R.id.startChatButton);
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent2 = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivity(intent2);
            }
        });

        startChat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent startChat = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(startChat);
            }
        });
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        if(resultCode == 50) {
            Log.i(ACTIVITY_NAME, "Returned to Start Activity.onActivityResult");
        }
    }

    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
     super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}