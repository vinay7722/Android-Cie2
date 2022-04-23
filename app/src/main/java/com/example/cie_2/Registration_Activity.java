package com.example.cie_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registration_Activity extends AppCompatActivity {

    EditText FirstName,LastName,UserName,PassWord,CPassWord;
    DBHelper myDB = new DBHelper(this);;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration Page");
        setContentView(R.layout.activity_registration);
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref",0);
        editor = sharedPreferences.edit();

    }

    //This Method  redirected to Login Activity
    public void getlogin(View view) {

        Intent intent = new Intent(Registration_Activity.this,Log_in_Activity.class);
        startActivity(intent);
        finish();
    }

    //This Method For Registration details
    public void Register(View view) {

        FirstName = findViewById(R.id.firstname);
        LastName =  findViewById(R.id.lastname);
        UserName =  findViewById(R.id.username);
        PassWord  = findViewById(R.id.password);
        CPassWord = findViewById(R.id.cpassword);


        String firstname = FirstName.getText().toString();
        String lastname = LastName.getText().toString();
        String username = UserName.getText().toString();
        String password = PassWord.getText().toString();
        String cpassword = CPassWord.getText().toString();


        if (!firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            if (password.equals(cpassword))
            {
                Boolean usercheckresult = myDB.checkusername(username);
                if (usercheckresult == false)
                {
                    Boolean regresult = myDB.insertDriverData(firstname,lastname,username,password);
                    if (regresult == true)
                    {
                        editor.putString("username", username);
                        editor.commit();
                        Toast.makeText(Registration_Activity.this,"Registration successful.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Registration_Activity.this, Driver_Dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Registration_Activity.this,"Registration failed.",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(Registration_Activity.this,"User already exists.",Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(Registration_Activity.this,"Password not matched!!",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(Registration_Activity.this, "Please fill up details!!", Toast.LENGTH_SHORT).show();
        }
    }

}
