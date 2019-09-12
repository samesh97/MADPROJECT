package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.lk.Film_Details_Maintain.Utils.Utils;


public class activity_film_details_view extends AppCompatActivity {



    TextView filmName,Ratings,Date,Time,Seats,Description;
    ConstraintLayout layout;

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

        layout = (ConstraintLayout) findViewById(R.id.layout);

        Intent intent = getIntent();
        filmName.setText(intent.getStringExtra("NAME"));
        Ratings.setText(intent.getStringExtra("RANKINGS"));
        Date.setText(intent.getStringExtra("DATE"));
        Time.setText(intent.getStringExtra("TIME"));
        Seats.setText(intent.getStringExtra("SEATS"));
        Description.setText(intent.getStringExtra("DESCRIPTION"));

        Drawable d = new BitmapDrawable(getResources(), SearchForTickets.background);

        layout.setBackground(d);
        layout.setAlpha(.5f);



    }

}
