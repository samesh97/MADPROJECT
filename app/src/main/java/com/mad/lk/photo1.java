package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class photo1 extends AppCompatActivity {

    Button select1;
    Button save1;
    ImageView image1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo1);

        image1 = findViewById(R.id.idviewimage1);

        select1 = findViewById(R.id.idbtnSelect1);

        save1 = findViewById(R.id.idbtnSave1);



    }

}
