package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_film_details_view extends AppCompatActivity {

    Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_view);

        booking = (Button) findViewById(R.id.loginButton);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_film_details_view.this,bookTicket.class);
                startActivity(intent);
            }
        });
    }

    public void thara1(View view)
    {
        startActivity(new Intent(activity_film_details_view.this, film_details_maintain.class));
    }
}
