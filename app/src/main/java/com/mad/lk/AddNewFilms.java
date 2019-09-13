package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class AddNewFilms extends AppCompatActivity {

    TextView name, rankings, date, time, seats, description;
    ImageView picture;
    DatabaseHelper helper;
    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    Uri filePath;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try
            {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                picture.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_films);

        helper = new DatabaseHelper(getApplicationContext());

        name = (TextView) findViewById(R.id.name);
        rankings = (TextView) findViewById(R.id.rankings);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        seats = (TextView) findViewById(R.id.seat);
        description = (TextView) findViewById(R.id.description);
        picture = (ImageView) findViewById(R.id.picture);






        picture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showFileChooser();
            }
        });
    }


    public void AddDetailsToDatabase(View view)
    {
        String nameText = name.getText().toString();
        String rankText = rankings.getText().toString();
        String dateText = date.getText().toString();
        String timeText = time.getText().toString();
        String seatsText = seats.getText().toString();
        String descriptionText = description.getText().toString();

        if (bitmap == null)
        {
            Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show();
            return;
        }


        if(helper.insertAFilm(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),helper.getBytes(bitmap)))
        {
            Toast.makeText(this, "Added successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }
        private void showFileChooser()
        {
            ActivityCompat.requestPermissions(AddNewFilms.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PICK_IMAGE_REQUEST);

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        }

}
