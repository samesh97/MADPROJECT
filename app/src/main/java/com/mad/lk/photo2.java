package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class photo2 extends AppCompatActivity {


    Button select2;
    Button save2;
    ImageView image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo2);

        image2 = findViewById(R.id.idviewimage2);

        select2 = findViewById(R.id.idbtnSelect2);

        save2 = findViewById(R.id.idbtnSave2);
    }
}
