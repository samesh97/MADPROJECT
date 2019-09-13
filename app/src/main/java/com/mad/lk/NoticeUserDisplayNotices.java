package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoticeUserDisplayNotices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_user_display_notices);

        Intent intentGet = getIntent();
        String date = intentGet.getStringExtra("msg1");

        String description=intentGet.getStringExtra("msg3");

        TextView Date =findViewById(R.id.idviewdirector);
        TextView Description=findViewById(R.id.textView);

        Date.setText(date);
        Description.setText(description);

    }
}
