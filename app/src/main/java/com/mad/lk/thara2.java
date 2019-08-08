package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class thara2 extends AppCompatActivity {

    Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thara2);

        booking = (Button) findViewById(R.id.booking);

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(thara2.this,bookTicket.class);
                startActivity(intent);
            }
        });
    }

    public void thara1(View view)
    {
        startActivity(new Intent(thara2.this,thara1.class));
    }
}
