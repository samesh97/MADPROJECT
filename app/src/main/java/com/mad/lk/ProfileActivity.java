package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    EditText updateUserName,updatePassword,updateConfirmPassword,updateEmail;
    String updatePasswordText,updateUsernameText,updateConfirmPasswordText,updateEmailText;
    Button updateButton;
    DatabaseHelper helper;
    String sessionUserName;
    CircleImageView imageView7;

    String userName,userEmail,userPassword;

    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap = null;
    Uri filePath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try
            {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView7.setImageBitmap(bitmap);


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        helper = new DatabaseHelper(getApplicationContext());

        updateUserName = (EditText) findViewById(R.id.updateUserName);
        updatePassword = (EditText)findViewById(R.id.updatePassword);
        updateConfirmPassword = (EditText) findViewById(R.id.updateConfirmPassword);
        updateEmail = (EditText) findViewById(R.id.updateEmail);
        imageView7 = (CircleImageView) findViewById(R.id.imageView7);

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

        updateButton = (Button) findViewById(R.id.updateButton);

        getUserData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                updateUsernameText = updateUserName.getText().toString();
                updatePasswordText = updatePassword.getText().toString();
                updateConfirmPasswordText = updateConfirmPassword.getText().toString();
                updateEmailText = updateEmail.getText().toString();

                if(updateUsernameText.equals("") || updatePasswordText.equals("") || updateConfirmPasswordText.equals("") || updateEmailText.equals(""))
                {
                    Toast.makeText(ProfileActivity.this, "Please fill out required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!updateConfirmPasswordText.equals(updatePasswordText))
                {
                    Toast.makeText(ProfileActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(updateEmailText.equals(userEmail) && updatePasswordText.equals(userPassword) &&updateUsernameText.equals(userName) && bitmap == null)
                {
                    Toast.makeText(ProfileActivity.this, "Please make some changes to Update the Profile", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    if(helper.isUserNameExist(updateUsernameText) && !sessionUserName.equals(updateUsernameText))
                    {
                        Toast.makeText(ProfileActivity.this, "Username Already taken", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {

                        if(helper.updateUserData(sessionUserName,updateUsernameText,updatePasswordText,updateEmailText,helper.getBytes(bitmap)))
                        {
                            sessionUserName = updateUsernameText;
                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor mEdit1 = sp.edit();
                            mEdit1.putString("username",sessionUserName);
                            mEdit1.commit();
                            Toast.makeText(ProfileActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SearchForTickets.class));
                            finish();
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
                imageView7.setImageBitmap(helper.getImage(cursor.getBlob(2)));
                updateEmail.setText(cursor.getString(3));
                updatePassword.setText(cursor.getString(4));
                updateConfirmPassword.setText(cursor.getString(4));

                userName = cursor.getString(1);
                userEmail = cursor.getString(3);
                userPassword = cursor.getString(4);

            }

        }
    }
    private void showFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

}
