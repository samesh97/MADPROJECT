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
import java.util.Timer;
import java.util.TimerTask;

public class FavoriteInserting extends AppCompatActivity {


Button deletefavorite;
FavoriteDatabaseHelper helper;

    ListView films;


    ArrayList<String> favoritenames = new ArrayList<>();
    ArrayList<String> favoriterankings = new ArrayList<>();
    ArrayList<String> favoritedates = new ArrayList<>();
    ArrayList<String> favoritetimes = new ArrayList<>();
    ArrayList<Integer> favoriteseats = new ArrayList<>();
    ArrayList<Integer> favoriteids = new ArrayList<>();
    ArrayList<Bitmap> favoriteimages = new ArrayList<>();
    ArrayList<String> favoritenotes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_insert);


      deletefavorite = (Button)findViewById(R.id.btndeletefavorite);
    }



    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = getLayoutInflater().inflate(R.layout.activity_favorite_insert,null);

        final TextView filmName = view.findViewById(R.id.filmName);
        TextView filmRatings = view.findViewById(R.id.filmRatings);
        TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
        TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
        TextView availableSeats = view.findViewById(R.id.availableSeats);
        ImageView filmPoster = view.findViewById(R.id.filmPoster);
        TextView notes = view.findViewById(R.id.favoritnote);
        final ImageView delete = view.findViewById(R.id.arrow);


        deletefavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(helper.deleteFavoriteFilm(favoriteids.get(position).toString()))
                {
                    favoriteids.remove(position);
                    favoriterankings.remove(position);
                    favoriteimages.remove(position);
                    favoritedates.remove(position);
                    favoritetimes.remove(position);
                    favoritenotes.remove(position);
                    favoriteseats.remove(position);
                    films.invalidateViews();
                    Toast.makeText(FavoriteInserting.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(FavoriteInserting.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });


        try
        {
            filmName.setText("Film Name : " +favoritenames.get(position));
            filmRatings.setText("Rankings : " +favoriterankings.get(position));
            filmShowingDate.setText("Showing Date : "+favoritedates.get(position));
            filmShowingTime.setText("Showing Time : "+favoritetimes.get(position));
            availableSeats.setText("Available Seats : " + favoriteseats.get(position));
            notes.setText("Film Name : " +favoritenotes.get(position));

            filmPoster.setImageBitmap(favoriteimages.get(position));
        }
        catch (Exception e)
        {

        }

        return view;
    }
}




