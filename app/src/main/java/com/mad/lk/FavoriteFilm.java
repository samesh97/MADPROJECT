package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoriteFilm extends AppCompatActivity {

    FavoriteDatabaseHelper fhelper;

    TextView favfilmname,favdescription,favranking,favdate,favtime,favseats,favnotes;
    ImageView favimage;
    ConstraintLayout layout;
    Button deletefav;

    ListView films;

    favorite_list_view.Adapter adapter;

    FavoriteDatabaseHelper helper;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> rankings = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Integer> seats = new ArrayList<>();
    ArrayList<String> description = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();

    private Handler handler = new Handler();

    private static final long Interval = 30;

    int count = 255;
    boolean iszero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_film);

        favfilmname = (TextView) findViewById(R.id.favoritefilmname);
        favranking = (TextView) findViewById(R.id.favoriterank);
        favdate = (TextView) findViewById(R.id.favoritdate);
        favtime = (TextView) findViewById(R.id.favorittime);
        favseats = (TextView) findViewById(R.id.favoriteseat);
        favdescription = (TextView) findViewById(R.id.favoritdesc);
        favnotes = (TextView) findViewById(R.id.favoritnote);

        favimage = (ImageView) findViewById(R.id.favoriteimage);

        deletefav = (Button) findViewById(R.id.btndeletefavorite);

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




