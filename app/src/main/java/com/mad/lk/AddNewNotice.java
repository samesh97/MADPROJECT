package com.mad.lk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewNotice extends AppCompatActivity {

    EditText message;
    String messageText;
    DatabaseHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notice);

        helper = new DatabaseHelper(AddNewNotice.this);

        message = (EditText) findViewById(R.id.message);

    }

    public void sendNotification(View view)
    {
        messageText = message.getText().toString();
        if(messageText.equals(""))
        {
            Toast.makeText(this, "Please enter the message", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            boolean success = helper.insertNotice(messageText);
            if(success)
            {
                Toast.makeText(this, "Notification was inserted", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(AddNewNotice.this,AdminNoticeScreen.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this, "Failed to send", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
