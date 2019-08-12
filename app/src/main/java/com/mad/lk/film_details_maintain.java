package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class film_details_maintain extends AppCompatActivity {

    Button btnadd;
    Button btnclear;
    Button btnupdate;
    Button btndelete;

    Text txtfilmname;
    Text txtrole1;
    Text txtrole2;
    Text txtrole3;
    Text txtrole4;
    Text txtdirector;

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

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
            });

        btnclear.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v)
          {

          }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
            });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v)
            {

            }
            });

    }

}
