package com.example.cie_2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome Page");
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();

        //This For Splash Screen
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent;
                boolean b = pref.getBoolean("isLoggedIn", false);
                if(!b)
                    intent = new Intent(MainActivity.this, Log_in_Activity.class);
                else
                    intent = new Intent(MainActivity.this, Driver_Dashboard.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
