package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterScreen extends AppCompatActivity {

    EditText username,email,password,confirmPassword;
    String userNameText,emailText,passwordText,confirmPasswordText;
    DatabaseHelper databaseClass;
    CircleImageView profilePic;

    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    Uri filePath;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try
            {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilePic.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        databaseClass = new DatabaseHelper(getApplicationContext());

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        profilePic = (CircleImageView) findViewById(R.id.profilePic);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showFileChooser();
            }
        });




    }

    public void OpenLoginActivity(View view)
    {
        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(intent);
        finish();
    }
    public void RegisterUser(View view)
    {
        if(bitmap == null)
        {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
            return;
        }
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
            password.setError("Password Does not Match!");
            confirmPassword.setError("Password Does not Match!");

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
           if(databaseClass.insertData(userNameText,emailText,passwordText,databaseClass.getBytes(bitmap)))
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
    private void showFileChooser()
    {
        ActivityCompat.requestPermissions(RegisterScreen.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PICK_IMAGE_REQUEST);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}
