package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteWithNote extends AppCompatActivity {

TextView notes;

FavoriteDatabaseHelper fhelper;
Button add;
//DatabaseHelper db;
String filmnamef = "aaa",descriptionf="bbb",notef="ccc",rankf="ddd",datef="eee",timef="ff";
int idf;
int seatsf;
Bitmap imagef;
Intent intent;
EditText idNote;
DatabaseHelper helper;
    byte[] image2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_with_note);

        helper = new DatabaseHelper(getApplicationContext());
        fhelper = new FavoriteDatabaseHelper(getApplicationContext());


        intent = getIntent();



        notes = (TextView) findViewById(R.id.favoritnote);
       add =(Button)findViewById(R.id.btnfavAdd);


        idNote = (EditText) findViewById(R.id.idNote);



        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                filmnamef = intent.getStringExtra("NAME");

                descriptionf = intent.getStringExtra("DESCRIPTION");
                rankf = intent.getStringExtra("RANKINGS");
                datef = intent.getStringExtra("DATE");
                timef = intent.getStringExtra("TIME");
                seatsf = intent.getIntExtra("SEATS",0);
                idf = intent.getIntExtra("ID",0);
                imagef = SearchForTickets.background;
                Cursor data2 = helper.getFilmData(String.valueOf(idf));
                while (data2.moveToNext())
                {
                    image2 = data2.getBlob(1);
                }

                notef = idNote.getText().toString();
                if(notef.equals("") || notef == null)
                {
                    Toast.makeText(FavoriteWithNote.this, "Enter a note first", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(fhelper.addfavorites(idf,filmnamef,descriptionf,rankf,datef,timef,seatsf,image2,notef))
                {
                    Toast.makeText(FavoriteWithNote.this,"Added to Favourites",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),favorite_list_view.class));
                    finish();
                }
                else
                {
                    if(fhelper.isFilmAlreadyExistInFavourites(String.valueOf(idf)))
                    {
                        Toast.makeText(FavoriteWithNote.this, "This Film is already exist in the Favourites, You can edit or delete it from there", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(FavoriteWithNote.this,"Could not add to database",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });



    }



}
