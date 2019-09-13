package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoticeAddNotices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_add_notices);


        Intent intentCameBack =getIntent();
        String date= intentCameBack.getStringExtra("msg11");
        String description= intentCameBack.getStringExtra("msg22");

        EditText Date= findViewById(R.id.editTextDate);
        Date.setText(date);

        EditText Description = findViewById(R.id.editTextDescription);
        Description.setText(description);
    }

    public void sendMessage(View view){
        Intent intentDisplay= new Intent(this,NoticeAdminDisplayNotices.class);

        EditText Date= findViewById(R.id.editTextDate);

        EditText Description=findViewById(R.id.editTextDescription);
        Button Add=findViewById(R.id.idbtnAdd);


        intentDisplay.putExtra("msg1",Date.getText().toString());

        intentDisplay.putExtra("msg3",Description.getText().toString());

        startActivity(intentDisplay);


    }
    public void saveUpdatedDetails(View view){
        Intent showUpdated=new Intent(this,NoticeAdminDisplayNotices.class);
        EditText Date= findViewById(R.id.editTextDate);
        EditText Description=findViewById(R.id.editTextDescription);
        Button Save=findViewById(R.id.btnSave);


        showUpdated.putExtra("msg1",Date.getText().toString());

        showUpdated.putExtra("msg3",Description.getText().toString());
        startActivity(showUpdated);
    }

}
