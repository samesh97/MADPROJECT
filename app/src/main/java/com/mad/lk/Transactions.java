package com.mad.lk;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity
{
    ListView transactionListView;
    Adapter adapter;
    DatabaseHelper helper;
    ArrayList<Integer> ids,seats;
    ArrayList<String> names,dates,times,types;
    ArrayList<Bitmap> images;
    DatabaseHelper dbhelper;

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),SearchForTickets.class));
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        dbhelper = new DatabaseHelper(getApplicationContext());

        ids = new ArrayList<>();
        seats = new ArrayList<>();
        names = new ArrayList<>();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        types = new ArrayList<>();
        images = new ArrayList<>();

        helper = new DatabaseHelper(Transactions.this);

        Cursor trans = helper.getAllbooking();
        while(trans.moveToNext())
        {
            ids.add(trans.getInt(0));
            names.add(trans.getString(1));
            seats.add(trans.getInt(2));
            types.add(trans.getString(3));
            times.add(trans.getString(4));
            dates.add(trans.getString(5));
            images.add(helper.getImage(trans.getBlob(6)));
        }

        adapter = new Adapter();
        transactionListView = (ListView) findViewById(R.id.transactionListView);
        transactionListView.setAdapter(adapter);

    }
    public class Adapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return ids.size();
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.transactionrow,null);
            ImageView poster = view.findViewById(R.id.filmPoster);
            ImageView delete = view.findViewById(R.id.delete);
            ImageView edit = view.findViewById(R.id.edit);
            final TextView filmName = view.findViewById(R.id.filmName);
            TextView ticketType = view.findViewById(R.id.ticketType);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
            TextView reservedSeats = view.findViewById(R.id.reservedSeats);

            poster.setImageBitmap(images.get(position));
            filmName.setText("Film Name : " + names.get(position));
            ticketType.setText("Ticket Type : " + types.get(position));
            filmShowingDate.setText("Shwoing Date :" + dates.get(position));
            filmShowingTime.setText("Showing Time" + times.get(position));
            reservedSeats.setText("Reserved Seats : " + seats.get(position));

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(helper.deletebooking(String.valueOf(ids.get(position))))
                    {
                        if(dbhelper.updateSeatCount(String.valueOf(ids.get(position)),seats.get(position) + dbhelper.getTicketCount(String.valueOf(ids.get(position)))))
                        {
                            names.remove(position);
                            images.remove(position);
                            ids.remove(position);
                            dates.remove(position);
                            times.remove(position);
                            seats.remove(position);
                            types.remove(position);
                            Toast.makeText(Transactions.this, "Deleted", Toast.LENGTH_SHORT).show();
                            transactionListView.invalidateViews();
                        }


                    }

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(Transactions.this,"Update Your Booking",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Transactions.this,updateBooking.class);
                    intent.putExtra("ID",ids.get(position));
                    startActivity(intent);
                }
            });

            return view;
        }
    }

}
