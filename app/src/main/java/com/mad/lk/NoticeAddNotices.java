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

}
