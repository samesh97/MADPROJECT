package com.mad.lk;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity
{
    ListView transactionListView;
    Adapter adapter;
    DBHelper_Lali helper;
    ArrayList<Integer> ids,seats;
    ArrayList<String> names,dates,times,types;
    ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        ids = new ArrayList<>();
        seats = new ArrayList<>();
        names = new ArrayList<>();
        dates = new ArrayList<>();
        times = new ArrayList<>();
        types = new ArrayList<>();
        images = new ArrayList<>();

        helper = new DBHelper_Lali(Transactions.this);

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
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.transactionrow,null);
            ImageView poster = view.findViewById(R.id.filmPoster);
            TextView filmName = view.findViewById(R.id.filmName);
            TextView ticketType = view.findViewById(R.id.ticketType);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
            TextView reservedSeats = view.findViewById(R.id.reservedSeats);

            poster.setImageBitmap(images.get(position));
            filmName.setText(names.get(position));
            ticketType.setText(types.get(position));
            filmShowingDate.setText(dates.get(position));
            filmShowingTime.setText(times.get(position));
            reservedSeats.setText(seats.get(position));

            return view;
        }
    }

}
