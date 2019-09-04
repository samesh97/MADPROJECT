package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class photo4 extends AppCompatActivity {

    Button select4;
    Button save4;
    ImageView image4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo4);

        image4 = findViewById(R.id.idviewimage4);

        select4 = findViewById(R.id.idbtnSelect4);

        save4 = findViewById(R.id.idbtnSave4);
    }
}
