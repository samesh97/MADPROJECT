package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteFilm extends AppCompatActivity {

    TextView favfilmname,favdescription,favranking,favdate,favtime,favseats,favnotes;
    ImageView favimage;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_view);

        favfilmname = (TextView) findViewById(R.id.favoritefilmname);
        favranking = (TextView) findViewById(R.id.favoriterank);
        favdate = (TextView) findViewById(R.id.favoritdate);
        favtime = (TextView) findViewById(R.id.favorittime);
        favseats = (TextView) findViewById(R.id.favoriteseat);
        favdescription = (TextView) findViewById(R.id.favoritdesc);
        favnotes = (TextView) findViewById(R.id.favoritnote);

        ImageView image = (ImageView)findViewById(R.id.favoriteimage);


        layout = (ConstraintLayout) findViewById(R.id.layout);

        Intent intent = getIntent();
        favfilmname.setText(intent.getStringExtra("NAME"));
        favranking.setText(intent.getStringExtra("RANKINGS"));
        favdate.setText(intent.getStringExtra("DATE"));
        favtime.setText(intent.getStringExtra("TIME"));
        favseats.setText(intent.getStringExtra("SEATS"));
        favdescription.setText(intent.getStringExtra("DESCRIPTION"));
        favnotes.setText(intent.getStringExtra("DESCRIPTION"));


    }

}




