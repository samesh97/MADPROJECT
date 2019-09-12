package com.mad.lk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AdminMainInterface extends AppCompatActivity {

    ListView films;
    Adapter adapter;
    DatabaseHelper helper;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> rankings = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Integer> seats = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();

    com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int x = item.getItemId();
        if(x == R.id.settings)
        {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();

        }
        if(x == R.id.logout)
        {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor mEdit1 = sp.edit();
            mEdit1.putString("username","Registered");
            mEdit1.commit();

            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notices_menu, menu);
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_interface);

        helper = new DatabaseHelper(getApplicationContext());

        helper.getAllFimsDetails();

        bottomNavigation = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottomNavigation);


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                if(menuItem.getItemId() == R.id.addNewFilms)
                {
                    startActivity(new Intent(getApplicationContext(),AddNewFilms.class));
                }
                if(menuItem.getItemId()==R.id.navigationFilmDetails)
                    startActivity(new Intent(getApplicationContext(),film_details_maintain.class));

                return false;
            }
        });

        Cursor data = helper.getAllFimsDetails();
        while (data.moveToNext())
        {
            int IDS  = data.getInt(0);
            byte[] image  = data.getBlob(1);
            String NAME = data.getString(2);
            String RANKINGS  = data.getString(4);
            String DATE  = data.getString(5);
            String TIME  = data.getString(6);
            int SEATS  = data.getInt(7);

            names.add(NAME);
            rankings.add(RANKINGS);
            dates.add(DATE);
            times.add(TIME);
            seats.add(SEATS);
            ids.add(IDS);
            images.add(helper.getImage(image));




        }


        films = (ListView) findViewById(R.id.films);
        adapter = new Adapter();
        films.setAdapter(adapter);
    }
    public class Adapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return seats.size();
        }

        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.adminmoviesearchrow,null);
            TextView filmName = view.findViewById(R.id.filmName);
            TextView filmRatings = view.findViewById(R.id.filmRatings);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
            TextView availableSeats = view.findViewById(R.id.availableSeats);
            ImageView filmPoster = view.findViewById(R.id.filmPoster);

            try
            {
                filmName.setText(names.get(position));
                filmRatings.setText(rankings.get(position));
                filmShowingDate.setText(dates.get(position));
                filmShowingTime.setText(times.get(position));
                availableSeats.setText("" + seats.get(position));
                filmPoster.setImageBitmap(images.get(position));
            }
            catch (Exception e)
            {

            }

            return view;
        }
    }

}
