package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritePage extends AppCompatActivity {

    TextView notes;
    TextView name, rankings, date, time, seats, description;
    ImageView picture;
    Button add;

    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    Uri filePath;


    FavoriteDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_page);

        notes = (TextView) findViewById(R.id.favoritnote);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoBack =new Intent(FavoritePage.this,FavoritePage.class);
            }
        });
    }


    public void AddDetailsToDatabase(View view)
    {
        String nameText = name.getText().toString();
        String rankText = rankings.getText().toString();
        String dateText = date.getText().toString();
        String timeText = time.getText().toString();
        String seatsText = seats.getText().toString();
        String descriptionText = description.getText().toString();
        String note = notes.getText().toString();


        if(helper.addfavorites(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),helper.getBytes(bitmap),note))
        {
            Toast.makeText(this, "Favorite data successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show();
        }

    }


}
