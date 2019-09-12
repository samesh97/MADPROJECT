package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    EditText updateUserName,updatePassword,updateConfirmPassword;
    String updatePasswordText,updateUsernameText,updateConfirmPasswordText;
    Button updateButton;
    DatabaseHelper helper;
    String sessionUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        helper = new DatabaseHelper(getApplicationContext());

        updateUserName = (EditText) findViewById(R.id.updateUserName);
        updatePassword = (EditText)findViewById(R.id.updatePassword);
        updateConfirmPassword = (EditText) findViewById(R.id.updateConfirmPassword);

        updateButton = (Button) findViewById(R.id.updateButton);

        getUserData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                updateUsernameText = updateUserName.getText().toString();
                updatePasswordText = updatePassword.getText().toString();
                updateConfirmPasswordText = updateConfirmPassword.getText().toString();

                if(updateUsernameText.equals("") || updatePasswordText.equals("") || updateConfirmPasswordText.equals(""))
                {
                    Toast.makeText(ProfileActivity.this, "Please fill out required fields", Toast.LENGTH_SHORT).show();
                }
                else if(!updateConfirmPasswordText.equals(updatePasswordText))
                {
                    Toast.makeText(ProfileActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                }
                else if(!sessionUserName.equals(updateUsernameText))
                {
                    if(helper.isUserNameExist(updateUsernameText))
                    {
                        Toast.makeText(ProfileActivity.this, "Username Already taken", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(helper.update(updateUsernameText,updatePasswordText))
                        {
                            sessionUserName = updateUsernameText;
                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor mEdit1 = sp.edit();
                            mEdit1.putString("username",sessionUserName);
                            mEdit1.commit();
                            Toast.makeText(ProfileActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(ProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                {

                }
                }


            }
        });


    }
    public void getUserData()
    {
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String uName = mSharedPreference1.getString(  "username", null);
        sessionUserName = uName;
        if(uName != null)
        {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            Cursor cursor = databaseHelper.getUserData(uName);

            while(cursor.moveToNext())
            {
                updateUserName.setText(cursor.getString(1));
                updatePassword.setText(cursor.getString(4));
                updateConfirmPassword.setText(cursor.getString(4));
            }

        }
    }

}
