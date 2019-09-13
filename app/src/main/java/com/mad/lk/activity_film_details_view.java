package com.mad.lk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class activity_film_details_view extends AppCompatActivity {



    TextView filmName,Ratings,Date,Time,Seats,Description;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_view);


        filmName = (TextView) findViewById(R.id.filmName);
        Ratings = (TextView) findViewById(R.id.Ratings);
        Date = (TextView) findViewById(R.id.Date);
        Time = (TextView) findViewById(R.id.Time);
        Seats = (TextView) findViewById(R.id.Seats);
        Description = (TextView) findViewById(R.id.Description);
        poster = (ImageView) findViewById(R.id.poster);

        poster.setImageBitmap(SearchForTickets.background);

        Intent intent = getIntent();

        filmName.setText(intent.getStringExtra("NAME"));
        Ratings.setText("Rankings : "+intent.getStringExtra("RANKINGS"));
        Date.setText("Date : " + intent.getStringExtra("DATE"));
        Time.setText("Time : "+intent.getStringExtra("TIME"));
        Seats.setText("Seats : " + intent.getIntExtra("SEATS",0));
        Description.setText(intent.getStringExtra("DESCRIPTION"));

    }

}
