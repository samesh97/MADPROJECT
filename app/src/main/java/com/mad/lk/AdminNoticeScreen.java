package com.mad.lk;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminNoticeScreen extends AppCompatActivity {

    ListView noticeView;
    ArrayList<String> messages;
    ArrayList<Integer> ids;
    DatabaseHelper helper;
    NoticeAdapter adapter;
    //public Dialog popup;
    static String updatingMessage = null;
    static String updatingId = null;


    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
        finish();
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_screen);


       // popup = new Dialog(this);
        //popup.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        messages = new ArrayList<>();
        ids = new ArrayList<>();
        helper = new DatabaseHelper(AdminNoticeScreen.this);
        adapter = new NoticeAdapter();

        Cursor details = helper.getAllNotices();
        while (details.moveToNext())
        {
            ids.add(details.getInt(0));
            messages.add(details.getString(1));
        }

        noticeView = (ListView) findViewById(R.id.noticeView);
        noticeView.setAdapter(adapter);
    }

    public void AddNewNotice(View view)
    {
        startActivity(new Intent(AdminNoticeScreen.this,AddNewNotice.class));
    }



    public class NoticeAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.noticerow,null);
            TextView message = view.findViewById(R.id.message);
            final TextView id = view.findViewById(R.id.id);
            ImageView edit = view.findViewById(R.id.edit);
            ImageView delete = view.findViewById(R.id.delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    updatingMessage = messages.get(position);
                    updatingId = ids.get(position).toString();
                    showPopup();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    boolean success = helper.deleteANotice(ids.get(position).toString());
                    if(success)
                    {
                        ids.remove(position);
                        messages.remove(position);
                        noticeView.invalidateViews();
                        Toast.makeText(AdminNoticeScreen.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AdminNoticeScreen.this, "Unable to delete", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            message.setText( messages.get(position));
            id.setText("" + ids.get(position));
            return view;
        }
    }
    public void showPopup()
    {


        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_popup_screen, null);

        final EditText editText = (EditText) dialogView.findViewById(R.id.updateText);
        Button button1 = (Button) dialogView.findViewById(R.id.update);
        editText.setText(AdminNoticeScreen.updatingMessage);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(helper.updateNotice(editText.getText().toString(),updatingId))
                {
                    Toast.makeText(AdminNoticeScreen.this, "Updated", Toast.LENGTH_SHORT).show();
                    dialogBuilder.dismiss();
                    startActivity(new Intent(getApplicationContext(),AdminNoticeScreen.class));
                    finish();
                }
                else
                {
                    Toast.makeText(AdminNoticeScreen.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }


}
