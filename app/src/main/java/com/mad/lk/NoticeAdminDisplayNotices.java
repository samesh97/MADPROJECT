package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoticeAdminDisplayNotices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_admin_display_notices);

        Intent intentGet = getIntent();
        String date = intentGet.getStringExtra("msg1");

        String description=intentGet.getStringExtra("msg3");

        TextView Date= findViewById(R.id.textViewDisplayDate);
        Date.setText(date);

        TextView Description=findViewById(R.id.textViewDisplayDescription);
        Description.setText(description);




    }

    public void sendMessage(View view){
        Intent intentGoBack =new Intent(this,NoticeAddNotices.class);

        Button Edit= findViewById(R.id.btnEdit);
            startActivity(intentGoBack);

    }
}
