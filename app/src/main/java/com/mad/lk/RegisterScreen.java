package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {

    EditText username,email,password,confirmPassword;
    String userNameText,emailText,passwordText,confirmPasswordText;
    DatabaseHelper databaseClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        databaseClass = new DatabaseHelper(getApplicationContext());

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);




    }

    public void OpenLoginActivity(View view)
    {
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
    }
    public void RegisterUser(View view)
    {
        userNameText = username.getText().toString();
        emailText = email.getText().toString();
        passwordText = password.getText().toString();
        confirmPasswordText = confirmPassword.getText().toString();


        if(userNameText.equals("") || emailText.equals("") || passwordText.equals("") || confirmPasswordText.equals(""))
        {
            Toast.makeText(this, "Fill required Fields First!", Toast.LENGTH_SHORT).show();

        }
        else if(!passwordText.equals(confirmPasswordText))
        {
            Toast.makeText(this, "Password Does not Match!", Toast.LENGTH_SHORT).show();

        }
        else if(passwordText.length() <= 5 || confirmPasswordText.length() <= 5 )
        {
            Toast.makeText(this, "Password is too small", Toast.LENGTH_SHORT).show();

        }
        else if(databaseClass.isUserNameExist(userNameText))
        {
            Toast.makeText(this, "Username Already Taken", Toast.LENGTH_SHORT).show();

        }
        else
        {
           if(databaseClass.insertData(userNameText,emailText,passwordText))
           {
               Toast.makeText(this, "Succefully Registered!", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
               startActivity(intent);

           }
           else
           {
               Toast.makeText(this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
           }
        }
    }
}
