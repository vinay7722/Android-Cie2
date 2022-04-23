package com.example.cie_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Driver_Dashboard extends AppCompatActivity {

    RecyclerView listData;
    ArrayList<FuelItemEntry> list;
    RecyclerViewAdapter adapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);
        setTitle("Dashboard");

        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref" , 0 );
        editor = sharedPreferences.edit();
        String Username = sharedPreferences.getString("username", "");

        DBHelper database = new DBHelper(this);
        list = database.driverFetchEntry(Username);
        listData = findViewById(R.id.listdata);
        adapter = new RecyclerViewAdapter(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listData.setLayoutManager(layoutManager);
        listData.setItemAnimator(new DefaultItemAnimator());
        listData.addItemDecoration(new DividerItemDecoration(Driver_Dashboard.this, DividerItemDecoration.VERTICAL));
        listData.setAdapter(adapter);
    }

    //For FuelEntry Details
    public void FuelEntry(View view) {

        Intent intent = new Intent(Driver_Dashboard.this,Fuel_Entry.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.welcome_menu, menu);
        return true;
    }


    //Use For Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_logout:
                editor.putBoolean("isLoggedIn", false);
                editor.commit();
                Intent intent = new Intent(Driver_Dashboard.this, Log_in_Activity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}