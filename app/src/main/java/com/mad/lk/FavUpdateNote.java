package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FavUpdateNote extends AppCompatActivity {


Button edit;
EditText text;
Button gotofav;

    FavoriteDatabaseHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_update_note);


        helper = new FavoriteDatabaseHelper(getApplicationContext());

        edit =(Button)findViewById(R.id.button);
        text = (EditText)findViewById(R.id.editText);
        gotofav =(Button)findViewById(R.id.idt2);

       // text.setVisibility(View.GONE);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(FavUpdateNote.this,"",Toast.LENGTH_LONG).show();

              String notess = text.getText().toString();
                boolean count = helper.updateFavorite(notess);
                   if(!notess.equals(null))
                       Toast.makeText(FavUpdateNote.this, "error", Toast.LENGTH_SHORT).show();
                   else

                       Toast.makeText(FavUpdateNote.this, "Updated!", Toast.LENGTH_SHORT).show();

            }
        });

        gotofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(FavUpdateNote.this,"test",Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(FavUpdateNote.this,favorite_list_view.class);
                startActivity(intent);
            }
        });


    }
}
