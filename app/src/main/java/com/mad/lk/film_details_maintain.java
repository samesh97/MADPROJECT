package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    TextView image1;
    TextView image2;
    TextView image3;
    TextView image4;

    ImageView viewimage1;
    ImageView viewimage2;
    ImageView viewimage3;
    ImageView viewimage4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        dbHelper = new db_film_details_maintain(this);

        btnadd = findViewById(R.id.idbtnAdd);
        btnviewall = findViewById(R.id.idbtnview);
        btnupdate = findViewById(R.id.idbtnUpdate);
        btndelete = findViewById(R.id.idbtnDelete);

        txtfilmname = findViewById(R.id.idtxtFilmName);
        txtrole1 = findViewById(R.id.idtxtRole1);
        txtrole2 = findViewById(R.id.idtxtRole2);
        txtrole3 = findViewById(R.id.idtxtRole3);
        txtrole4 = findViewById(R.id.idtxtRole4);
        txtdirector = findViewById(R.id.idtxtDirector);

        image1 = findViewById(R.id.idtxtphoto1);
        viewimage1 = findViewById(R.id.idimage1);

        image2 = findViewById(R.id.idtxtphoto2);
        viewimage2 = findViewById(R.id.idimage2);

        image3 = findViewById(R.id.idtxtphoto3);
        viewimage3 = findViewById(R.id.idimage3);

        image4 = findViewById(R.id.idtxtphoto4);
        viewimage4 = findViewById(R.id.idimage4);

        viewAll();

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
                showMeessage("Error", "Nothing found");
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
            showMeessage("Data",buffer.toString());
        }
    });
    }

    public void showMeessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
    }
}
