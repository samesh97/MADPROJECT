package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteWithNote extends AppCompatActivity {

TextView filmname,rankings,date,seats,time,description,notes;
Bitmap bitmap;
ImageView picture;
FavoriteDatabaseHelper fhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_with_note);

        notes = (TextView) findViewById(R.id.favoritnote);
        filmname = (TextView) findViewById(R.id.filmName);
        rankings = (TextView) findViewById(R.id.Ratings);
        date = (TextView) findViewById(R.id.Date);
        time = (TextView) findViewById(R.id.Time);
        seats = (TextView) findViewById(R.id.Seats);
        description = (TextView) findViewById(R.id.Description);
        picture = (ImageView) findViewById(R.id.poster);





    }

    public void AddFavoritesToDatabase(View view) {

        String nameText = filmname.getText().toString();
        String rankText = rankings.getText().toString();
        String dateText = date.getText().toString();
        String timeText = time.getText().toString();
        String seatsText = seats.getText().toString();
        String descriptionText = description.getText().toString();
        String note = notes.getText().toString();


        if(fhelper.addfavorites(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),fhelper.getBytes(bitmap),note))
        {
            Toast.makeText(this, "Favorite data add successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),favorite_list_view.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateFavoritesToDatabase(View view) {


        String notess = notes.getText().toString();

        if(notess.isEmpty()) {
            Toast.makeText(getApplicationContext(), "note cannot be empty ! ", Toast.LENGTH_SHORT).show();
        }else {
            fhelper.updateFavorite(notess);
            Toast.makeText(getApplicationContext(), "note updated successfully. ", Toast.LENGTH_SHORT).show();
        }
    }
}
