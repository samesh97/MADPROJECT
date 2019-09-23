package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class UpdateAFilm extends AppCompatActivity {

    TextView name, rankings, date, time, seats, description;
    ImageView picture;
    DatabaseHelper helper;
    private static final int PICK_IMAGE_REQUEST = 234;
    Bitmap bitmap;
    Uri filePath;
    String Clickedid;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_afilm);

        Intent intent = getIntent();
        Clickedid = String.valueOf(intent.getIntExtra("ID",0));


        helper = new DatabaseHelper(getApplicationContext());

        name = (TextView) findViewById(R.id.name);
        rankings = (TextView) findViewById(R.id.rankings);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        seats = (TextView) findViewById(R.id.seat);
        description = (TextView) findViewById(R.id.description);
        picture = (ImageView) findViewById(R.id.picture);

        Cursor cursor = helper.getFilmData(Clickedid);
        while (cursor.moveToNext())
        {
            name.setText(cursor.getString(2));
            rankings.setText(cursor.getString(4));
            date.setText(cursor.getString(5));
            time.setText(cursor.getString(6));
            seats.setText("" + cursor.getInt(7));
            description.setText(cursor.getString(3));
            picture.setImageBitmap(helper.getImage(cursor.getBlob(1)));
        }

        picture.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showFileChooser();
            }
        });

    }
    public void UpdateFilm(View view)
    {
        String nameText = name.getText().toString();
        String rankText = rankings.getText().toString();
        String dateText = date.getText().toString();
        String timeText = time.getText().toString();
        String seatsText = seats.getText().toString();
        String descriptionText = description.getText().toString();

        if(nameText.equals("") || rankText.equals("") || dateText.equals("") || timeText.equals("") || seatsText.equals("") || descriptionText.equals(""))
        {
            Toast.makeText(this, "Fill Required Fields First", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            if(bitmap != null)
            {
                Bitmap resizedImage = getResizedBitmap(bitmap,200);
                if(helper.updateAFilmWithImage(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),helper.getBytes(resizedImage),Clickedid))
                {
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if(helper.updateAFilmWithoutImage(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),Clickedid))
                {
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        }







    }
    private void showFileChooser()
    {
        ActivityCompat.requestPermissions(UpdateAFilm.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PICK_IMAGE_REQUEST);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
