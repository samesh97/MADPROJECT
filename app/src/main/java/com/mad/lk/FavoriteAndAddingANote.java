package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteAndAddingANote extends AppCompatActivity {

    TextView notes;
    TextView filmname, rankings, date, time, seats, description;
    ImageView picture;

    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    Uri filePath;


    DatabaseHelper helper;
    FavoriteDatabaseHelper fhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_and_adding_a_note);

        notes = (TextView) findViewById(R.id.favoritnote);
        filmname = (TextView) findViewById(R.id.filmName);
        rankings = (TextView) findViewById(R.id.Ratings);
        date = (TextView) findViewById(R.id.Date);
        time = (TextView) findViewById(R.id.Time);
        seats = (TextView) findViewById(R.id.Seats);
        description = (TextView) findViewById(R.id.Description);
        picture = (ImageView) findViewById(R.id.poster);


        Intent intent = getIntent();

       // picture.setImageURI(intent.);
        filmname.setText("Film Name : " +intent.getStringExtra("NAME"));
        description.setText("Description : "+intent.getStringExtra("DESCRIPTION"));
        rankings.setText("Rankings : "+intent.getStringExtra("RANKINGS"));
        date.setText("Date : " + intent.getStringExtra("DATE"));
        time.setText("Time : "+intent.getStringExtra("TIME"));
        seats.setText("Seats : " + intent.getIntExtra("SEATS",0));




    }


    public void AddFavoritesToDatabase(View view)
    {
        String nameText = filmname.getText().toString();
        String rankText = rankings.getText().toString();
        String dateText = date.getText().toString();
        String timeText = time.getText().toString();
        String seatsText = seats.getText().toString();
        String descriptionText = description.getText().toString();
        String note = notes.getText().toString();


        if(fhelper.addfavorites(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),helper.getBytes(bitmap),note))
        {
            Toast.makeText(this, "Favorite data successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),favorite_list_view.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
        }

    }


}
