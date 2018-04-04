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

public class LoginActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Button button1 = (Button)findViewById(R.id.loginButton);
        EditText editText = (EditText)findViewById(R.id.loginName);

        SharedPreferences preferences = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);

        String emailAddress = preferences.getString("DefaultEmail", "email@domain.com");
        editText.setText(emailAddress);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences preferences = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                EditText editText = (EditText)findViewById(R.id.loginName);
                edit.putString("DefaultEmail", editText.getText().toString());
                edit.commit();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                    startActivity(intent);
            }
        });
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