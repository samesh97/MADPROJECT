package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class film_details_maintain extends AppCompatActivity {

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

    TextView viewFilmName;
    TextView viewRole1;
    TextView viewRole2;
    TextView viewRole3;
    TextView viewRole4;
    TextView viewDirector;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        btnadd = findViewById(R.id.idbtnAdd);
        btnclear = findViewById(R.id.idbtnClear);
        btnupdate = findViewById(R.id.idbtnUpdate);
        btndelete = findViewById(R.id.idbtnDelete);

        txtfilmname = (EditText) findViewById(R.id.idtxtFilmName);
        viewFilmName = (TextView) findViewById(R.id.idtxtFilmName);


        txtrole1 = (EditText) findViewById(R.id.idtxtRole1);
        viewRole1 = (TextView) findViewById(R.id.idtxtRole1);

        txtrole2 = (EditText) findViewById(R.id.idtxtRole2);
        viewRole2 = (TextView) findViewById(R.id.idtxtRole2);

        txtrole3 = (EditText) findViewById(R.id.idtxtRole3);
        viewRole3 = (TextView) findViewById(R.id.idtxtRole3);

        txtrole4 = (EditText) findViewById(R.id.idtxtRole4);
        viewRole4 = (TextView) findViewById(R.id.idtxtRole4);

        txtdirector = (EditText) findViewById(R.id.idtxtDirector);
        viewDirector = (TextView) findViewById(R.id.idtxtDirector);


    }

    public void ShowToastAdd(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Add sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastClear(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Clear sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastUpdate(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Update sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastDelete(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Delete sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }
}
