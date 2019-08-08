package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText loginUserName,loginPassword;
    String loginUserNameText,loginPasswordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);



        loginUserName = (EditText) findViewById(R.id.loginUserName);
        loginPassword  = (EditText) findViewById(R.id.loginPassword);



        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    public void OpenLoggedOnScreen(View view)
    {



        loginUserNameText = loginUserName.getText().toString();
        loginPasswordText = loginPassword.getText().toString();

        if(loginPasswordText.equals("") || loginUserNameText.equals(""))
        {
            Toast.makeText(this, "Enter Required Details First!", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor data = databaseHelper.getAllData();

        if(data.getCount() == 0)
        {
            Toast.makeText(this, "Invalid Login Details", Toast.LENGTH_SHORT).show();
        }
        else
        {


            while (data.moveToNext())
            {
                String userName = data.getString(1);
                String password = data.getString(3);

                if(userName.equals(loginUserNameText) && password.equals(loginPasswordText))
                {
                    Intent intent = new Intent(getApplicationContext(),SearchForTickets.class);
                    startActivity(intent);
                    return;
                }


            }
            Toast.makeText(this, "Invalid Login Details", Toast.LENGTH_SHORT).show();

        }


    }
}
