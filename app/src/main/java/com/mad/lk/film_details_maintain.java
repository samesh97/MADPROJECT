package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class film_details_maintain extends AppCompatActivity {

    dbFilmDetails controller;



  //  private static final int PICK_IMAGE=100;

    Button btnadd;
    Button btnclear;
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


    controller = new dbFilmDetails(this,"",1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        btnadd = findViewById(R.id.idbtnAdd);
        btnclear = findViewById(R.id.idbtnClear);
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

      //  db = new dbFilmDetails(this);


    }


    public void btn_click(View view) {

        Toast toastAdd = Toast.makeText(getApplicationContext(),"Add sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();

        Toast toastClear = Toast.makeText(getApplicationContext(),"Clear sucessfully",Toast.LENGTH_LONG);
        toastClear.show();

        Toast toastUpdate = Toast.makeText(getApplicationContext(),"Update sucessfully",Toast.LENGTH_LONG);
        toastUpdate.show();

        Toast toastDelete = Toast.makeText(getApplicationContext(),"Delete sucessfully",Toast.LENGTH_LONG);
        toastDelete.show();

        switch (view.getId()){

            case R.id.idbtnAdd:
                try {
                    controller.Add_FilmDetails(
                            txtfilmname.getText().toString(),
                            txtrole1.getText().toString(),
                            txtrole2.getText().toString(),
                            txtrole3.getText().toString(),
                            txtrole4.getText().toString(),
                            txtdirector.getText().toString(),
                            image1.getText().toString(),
                            image2.getText().toString(),
                            image3.getText().toString(),
                            image4.getText().toString());
                }catch (SQLiteException e){
                    Toast.makeText(film_details_maintain.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.idbtnUpdate:
                AlertDialog.Builder dialog = new AlertDialog.Builder(film_details_maintain.this);
                dialog.setTitle("Enter NEW DETAILS");

                final EditText newfilmName = new EditText(this);
                dialog.setView(newfilmName);

                final EditText newrole1 = new EditText(this);
                dialog.setView(newrole1);

                final EditText newrole2 = new EditText(this);
                dialog.setView(newrole2);

                final EditText newrole3 = new EditText(this);
                dialog.setView(newrole3);

                final EditText newrole4 = new EditText(this);
                dialog.setView(newrole4);

                final EditText newdirectorName = new EditText(this);
                dialog.setView(newdirectorName);

                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        controller.Update_FilmDetails(
                                newfilmName.getText().toString(),
                                newrole1.getText().toString(),
                                newrole2.getText().toString(),
                                newrole3.getText().toString(),
                                newrole4.getText().toString(),
                                newdirectorName.getText().toString(),
                                newimage1.getText().toString(),
                                newimage2.getText().toString(),
                                newimage3.getText().toString(),
                                newimage4.getText().toString());
                    }
                });
                dialog.show();
                break;
            case R.id.idbtnDelete:
                controller.Delete_FilmDetails(txtfilmname.getText().toString());
                break;
            case R.id.idbtnClear:
                break;
        }
    }
}
