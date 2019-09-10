package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.drawable.BitmapDrawable;

import org.w3c.dom.Text;

import com.mad.lk.Film_Details_Maintain.Utils.Utils;
import com.mad.lk.Film_Details_Maintain.db_film_details_maintain;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class film_details_maintain extends AppCompatActivity {

    db_film_details_maintain dbHelper;

    Button btnimage1;
    Button btnimage2;
    Uri imageUri1;

    Button btnadd;
    Button btnviewall;
    Button btnupdate;
    Button btndelete;
    Button select1;
    Button select2;

    EditText edittxtfilmname;
    EditText edittxtrole1;
    EditText edittxtrole2;
    EditText edittxtrole3;
    EditText edittxtrole4;
    EditText edittxtdirector;

    ImageView imageview1;
    ImageView imageview2;

    private static  final int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        dbHelper = new db_film_details_maintain(this);

        btnadd = findViewById(R.id.idbtnAdd);
        btnviewall = findViewById(R.id.idbtnview);
        btnupdate = findViewById(R.id.idbtnUpdate);
        btndelete = findViewById(R.id.idbtnDelete);

        select1 = findViewById(R.id.idbtnSelect1);
        select2 = findViewById(R.id.idbtnSelect2);

        edittxtfilmname = findViewById(R.id.idtxtFilmName);
        edittxtrole1 = findViewById(R.id.idtxtRole1);
        edittxtrole2 = findViewById(R.id.idtxtRole2);
        edittxtrole3 = findViewById(R.id.idtxtRole3);
        edittxtrole4 = findViewById(R.id.idtxtRole4);
        edittxtdirector = findViewById(R.id.idtxtDirector);

        imageview1 = findViewById(R.id.idviewimage1);
        imageview2 = findViewById(R.id.idviewimage1);


        viewAll();


        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openGallery();
            // Intent intent = new Intent(Intent.ACTION_PICK);
             //intent.setType("Image/*");
             //startActivityForResult(intent,SELECT_PHOTO);

            }
        });
//------------------------------Adding button----------------------------


        btnadd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String Film_Name = edittxtfilmname.getText().toString();
              String Role1 = edittxtrole1.getText().toString();
              String Role2 = edittxtrole2.getText().toString();
              String Role3 = edittxtrole3.getText().toString();
              String Role4 = edittxtrole4.getText().toString();
              String Director_Name = edittxtdirector.getText().toString();
              final Bitmap Photo1 = ((BitmapDrawable)imageview1.getDrawable()).getBitmap();
             final Bitmap Photo2 = ((BitmapDrawable)imageview2.getDrawable()).getBitmap();



              if (Film_Name.isEmpty()) {
                  Toast.makeText(getApplicationContext(), "filmname cannot be empty", Toast.LENGTH_SHORT).show();
              } else {
                  dbHelper.Add(Film_Name,Role1,Role2,Role3,Role4,Director_Name, Utils.getBytes(Photo1),Utils.getBytes(Photo2));
                  Toast.makeText(getApplicationContext(), "Data added successfully!", Toast.LENGTH_SHORT).show();
              }
          }

      });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Film_Name = edittxtfilmname.getText().toString();

                dbHelper.Delete(getApplicationContext(), Film_Name);
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Film_Name = edittxtfilmname.getText().toString();
                String Role1 = edittxtrole1.getText().toString();
                String Role2 = edittxtrole2.getText().toString();
                String Role3 = edittxtrole3.getText().toString();
                String Role4 = edittxtrole4.getText().toString();
                String Director_Name = edittxtdirector.getText().toString();
                Bitmap Photo1 = ((BitmapDrawable)imageview1.getDrawable()).getBitmap();
                Bitmap Photo2 = ((BitmapDrawable)imageview2.getDrawable()).getBitmap();


                if(Film_Name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "film name cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    dbHelper.Update(Film_Name,Role1,Role2,Role3,Role4,Director_Name, Utils.getBytes(Photo1),Utils.getBytes(Photo2));
                    Toast.makeText(getApplicationContext(), "film name updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void viewAll(){
    btnviewall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor res = dbHelper.getAllData();
            if(res.getCount() == 0){
                showMessage("Error", "Nothing found");
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("ID : " + res.getString(0)+"\n");
                buffer.append("Film Name : " + res.getString(1)+"\n");
                buffer.append("Role 1 : " + res.getString(2)+"\n");
                buffer.append("Role 2 : " + res.getString(3)+"\n");
                buffer.append("Role 3 : " + res.getString(4)+"\n");
                buffer.append("Role 4 : " + res.getString(5)+"\n");
                buffer.append("Director Name : " + res.getString(6)+"\n");
                buffer.append("Photo 1 : " + res.getString(7)+"\n");
                buffer.append("Photo 2 : " + res.getString(8)+"\n");


            }
            showMessage("Data",buffer.toString());
        }
    });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    protected void onActivity(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri1 = data.getData();
            imageview1.setImageURI(imageUri1);
        }
    }

    }
