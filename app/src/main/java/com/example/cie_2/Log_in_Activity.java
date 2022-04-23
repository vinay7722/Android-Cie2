package com.example.cie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Log_in_Activity extends AppCompatActivity {


    EditText username,password;

    DBHelper myDB = new DBHelper(this);

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log in");
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        editor = sharedPreferences.edit();
    }

    //This Method  redirected to Registration Activity
    public void Getregister(View view) {

        Intent intent = new Intent(Log_in_Activity.this,Registration_Activity.class);
        startActivity(intent);
        finish();
    }

    //This Method is Checked For Login
    public void Login(View view) {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        String UserName = username.getText().toString();
        String PassWord = password.getText().toString();


            if (UserName.equals("") && PassWord.equals(""))
            {
                Toast.makeText(Log_in_Activity.this,"Please enter the credentials.",Toast.LENGTH_LONG).show();
            }
            else
            {
                Boolean loginresult = myDB.checkusernamepassword(UserName,PassWord);
                if (loginresult)
                {
                    editor.putString("username", UserName);
                    editor.commit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.commit();Intent in = new Intent(Log_in_Activity.this,Driver_Dashboard.class);
                    startActivity(in);
                    finish();
                }
                else
                {
                    Toast.makeText(Log_in_Activity.this,"Invalid credentials.",Toast.LENGTH_LONG).show();
                }

            }
    }
}