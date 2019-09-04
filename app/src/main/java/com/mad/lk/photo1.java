package com.mad.lk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mad.lk.Film_Details_Maintain.Utils.Utils;
import com.mad.lk.Film_Details_Maintain.db_film_details_maintain;

public class photo1 extends AppCompatActivity {

    private static  final int SELECT_PHOTO = 7777;
    Button select1;
    Button save1;
    ImageView image1;

    db_film_details_maintain dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo1);

        dbHelper = new db_film_details_maintain(this);

        image1 = (ImageView)findViewById(R.id.idviewimage1);

        select1 = (Button)findViewById(R.id.idbtnSelect1);

        save1 = (Button)findViewById(R.id.idbtnSave1);

        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)image1.getDrawable()).getBitmap();

                AlertDialog.Builder builder = new AlertDialog.Builder(photo1.this);

                dbHelper.addbitmap1(Utils.getBytes(bitmap));
                Toast.makeText(photo1.this,"",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data !=null){
            Uri pickedImage = data.getData();
            image1.setImageURI(pickedImage);
            save1.setEnabled(true);
        }
    }
}
