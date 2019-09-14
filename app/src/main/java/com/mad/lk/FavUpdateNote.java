package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FavUpdateNote extends AppCompatActivity {

    EditText text;
    Button edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_update_note);

         edit =(Button)findViewById(R.id.button);
        text =(EditText)findViewById(R.id.text);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FavUpdateNote.this,"TTT",Toast.LENGTH_LONG).show();

            /*   String noteText = notes.getText().toString();

                   if(!notef.equals(""))
                       Toast.makeText(FavUpdateNote.this, "Please fill out the field", Toast.LENGTH_SHORT).show();
                   else
                   if(fhelper.updateFavorite(notef))

                       Toast.makeText(favorite_list_view.this, "Updated!", Toast.LENGTH_SHORT).show();
*/
            }
        });
    }
}
