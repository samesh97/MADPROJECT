package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FavUpdateNote extends AppCompatActivity {


Button edit;
EditText text;
Button gotofav;
String updateItemID;



    DatabaseHelper helper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_update_note);

        Intent intent = getIntent();
        updateItemID = String.valueOf(intent.getIntExtra("ID",0));


        helper = new DatabaseHelper(getApplicationContext());

        edit =(Button)findViewById(R.id.button);
        text = (EditText)findViewById(R.id.editText);


       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = preferences.getString("username", null);

        Cursor cursor = helper.getAllFavorites(user,updateItemID);
        while (cursor.moveToNext())
        {
            text.setText(cursor.getString(8));

        }
       ///// text.setVisibility(View.GONE);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(FavUpdateNote.this,"",Toast.LENGTH_LONG).show();

               // Boolean updateFavorite = helper.updateFavorite("abcde",2);
                String editedText = text.getText().toString();

                if(editedText.equals(""))                {
                    Toast.makeText(FavUpdateNote.this, "Fill your note First", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    if(helper.updateFavorite(text.getText().toString(),String.valueOf(updateItemID)))
                    {
                        Toast.makeText(FavUpdateNote.this, "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent =  new Intent(FavUpdateNote.this,favorite_list_view.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(FavUpdateNote.this, "Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


       /* gotofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(FavUpdateNote.this,"test",Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(FavUpdateNote.this,favorite_list_view.class);
                startActivity(intent);
            }
        });*/


    }
}
