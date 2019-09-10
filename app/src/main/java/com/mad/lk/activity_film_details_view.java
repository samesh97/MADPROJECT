package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mad.lk.Film_Details_Maintain.Utils.Utils;
import com.mad.lk.Film_Details_Maintain.db_film_details_maintain;

public class activity_film_details_view extends AppCompatActivity {


    db_film_details_maintain dbHelper;

    Button booking;

    ImageView image1;
    ImageView image2;

    TextView filmname;
    TextView role1;
    TextView role2;
    TextView role3;
    TextView role4;
    TextView directorname;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_view);

        dbHelper = new db_film_details_maintain(this);

        booking = (Button)findViewById(R.id.idbtnbooking);

        image1 = (ImageView)findViewById(R.id.idimage1);
        image2 = (ImageView)findViewById(R.id.idimage2);

        filmname = findViewById(R.id.idtxtFilmName);
        role1 = findViewById(R.id.idtxtRole1);
        role2 = findViewById(R.id.idtxtRole2);
        role3 = findViewById(R.id.idtxtRole3);
        role4 = findViewById(R.id.idtxtRole4);
        directorname = findViewById(R.id.idtxtDirector);


        booking = findViewById(R.id.idbtnbooking);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),bookTicket.class);
                
            }
        });
    }

    //public void film_details_view(View view)
    //{
      //  startActivity(new Intent(activity_film_details_view.this, film_details_maintain.class));
    //}





}
