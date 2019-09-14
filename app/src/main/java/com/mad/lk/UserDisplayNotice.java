package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserDisplayNotice extends AppCompatActivity {

    ArrayList<String> message;
    ListView noticeView;
    Adapter adapter;
    DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_notice);



        message = new ArrayList<>();
        noticeView = (ListView) findViewById(R.id.noticeView);
        helper = new DatabaseHelper(getApplicationContext());
        Cursor data = helper.getAllNotices();
        while (data.moveToNext())
        {
            message.add(data.getString(1));
        }

        adapter = new Adapter();
        noticeView.setAdapter(adapter);

    }
    public class Adapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return message.size();
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View view1 = getLayoutInflater().inflate(R.layout.activity_notice_user_display_notices,null);
            TextView messageView =  (TextView) view1.findViewById(R.id.messageView);
            messageView.setText(message.get(i));

            return view1;
        }
    }
}
