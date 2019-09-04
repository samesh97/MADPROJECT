package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import com.mad.lk.Film_Details_Maintain.db_film_details_maintain;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class film_details_maintain extends AppCompatActivity {

    db_film_details_maintain dbHelper;


    Button image1;
    Button image2;
    Button image3;
    Button image4;

    Button btnadd;
    Button btnviewall;
    Button btnupdate;
    Button btndelete;

    EditText txtfilmname;
    EditText txtrole1;
    EditText txtrole2;
    EditText txtrole3;
    EditText txtrole4;
    EditText txtdirector;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        dbHelper = new db_film_details_maintain(this);

        image1 = (Button)findViewById(R.id.btnimage1);
        image2 = (Button)findViewById(R.id.btnimage2);
        image3 = (Button)findViewById(R.id.btnimage3);
        image4 = (Button)findViewById(R.id.btnimage4);

        btnadd = (Button)findViewById(R.id.idbtnAdd);
        btnviewall = (Button)findViewById(R.id.idbtnview);
        btnupdate = (Button)findViewById(R.id.idbtnUpdate);
        btndelete = (Button)findViewById(R.id.idbtnDelete);

        txtfilmname = findViewById(R.id.idtxtFilmName);
        txtrole1 = findViewById(R.id.idtxtRole1);
        txtrole2 = findViewById(R.id.idtxtRole2);
        txtrole3 = findViewById(R.id.idtxtRole3);
        txtrole4 = findViewById(R.id.idtxtRole4);
        txtdirector = findViewById(R.id.idtxtDirector);




        viewAll();


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),photo1.class);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),photo2.class);
            }
        });


        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),photo3.class);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),photo4.class);
            }
        });
//------------------------------Adding button----------------------------


        btnadd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              String filmname = txtfilmname.getText().toString();
              String role1 = txtrole1.getText().toString();
              String role2 = txtrole2.getText().toString();
              String role3 = txtrole3.getText().toString();
              String role4 = txtrole4.getText().toString();
              String director = txtdirector.getText().toString();




              if (filmname.isEmpty()) {
                  Toast.makeText(getApplicationContext(), "filmname cannot be empty", Toast.LENGTH_SHORT).show();
              } else {
                  dbHelper.Add(filmname,role1,role2,role3,role4,director);
                  Toast.makeText(getApplicationContext(), "Data added successfully!", Toast.LENGTH_SHORT).show();
              }
          }

      });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filmname = txtfilmname.getText().toString();

                dbHelper.Delete(getApplicationContext(), filmname);
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filmname = txtfilmname.getText().toString();
                String role1 = txtrole1.getText().toString();
                String role2 = txtrole2.getText().toString();
                String role3 = txtrole3.getText().toString();
                String role4 = txtrole4.getText().toString();
                String director = txtdirector.getText().toString();

                if(filmname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "film name cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    dbHelper.Update(filmname,role1,role2,role3,role4,director);
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


}
