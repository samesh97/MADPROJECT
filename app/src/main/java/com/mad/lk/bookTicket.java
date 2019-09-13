package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class bookTicket extends AppCompatActivity {
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        db=new DatabaseHelper(this);

    }
}
