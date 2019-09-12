package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FavoriteInserting extends AppCompatActivity {


Button deletefavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_insert);


        deletefavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFavorites();
            }
        });
    }

    public void deleteFavorites(){
       String FAVORITE_FILMNAME = getIntent().getExtras().getString("NAME");
       FavoriteDatabaseHelper Fdb = new FavoriteDatabaseHelper(FavoriteInserting.this);
       Fdb.deleteFavorites(FAVORITE_FILMNAME);


    }



}


