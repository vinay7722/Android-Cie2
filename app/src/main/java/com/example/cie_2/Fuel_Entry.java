package com.example.cie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fuel_Entry extends AppCompatActivity {

    EditText Volume,Rsperlt,PreviousodometerReading,Currodread;
    DBHelper MYdb = new DBHelper(this);
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Fuel Entry");
        setContentView(R.layout.activity_fuel_entry);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref" , 0 );
        editor = sharedPreferences.edit();
        username = sharedPreferences.getString("username", "");

    }


    public void addentry(View view) {

        Volume = findViewById(R.id.volume);
        Rsperlt = findViewById(R.id.rupeesPerLitre);
        PreviousodometerReading = findViewById(R.id.previousOdometerReading);
        Currodread = findViewById(R.id.currentOdometerReading);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String volumeS = Volume.getText().toString();
        String rsperlt = Rsperlt.getText().toString();
        String previousodometerReadingS = PreviousodometerReading.getText().toString();
        String currodreadS = Currodread.getText().toString();

        if(!username.equals("") && !date.equals("") && !volumeS.equals("") && !rsperlt.equals("")&& !previousodometerReadingS.equals("") && !currodreadS.equals(""))
        {
            double volume = Double.parseDouble(Volume.getText().toString());
            double previousodometerReading = Double.parseDouble(PreviousodometerReading.getText().toString());
            double currodread = Double.parseDouble(Currodread.getText().toString());
            double avg = (currodread - previousodometerReading) / volume;
            Boolean regresult = MYdb.insertDriverEntry(username,date,volume,rsperlt,previousodometerReading,currodread,avg);
            if(regresult)
            {
                Toast.makeText(Fuel_Entry.this,"Data Inserted successful...",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Fuel_Entry.this, Driver_Dashboard.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(Fuel_Entry.this,"Data Not Inserted...",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(Fuel_Entry.this,"Fill The All Details",Toast.LENGTH_LONG).show();
        }

    }
}