package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class photo3 extends AppCompatActivity {

    Button select3;
    Button save3;
    ImageView image3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo3);

        image3 = findViewById(R.id.idviewimage3);

        select3 = findViewById(R.id.idbtnSelect3);

        save3 = findViewById(R.id.idbtnSave3);
    }
}
