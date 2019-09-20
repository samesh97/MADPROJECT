package com.mad.lk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.Timer;
import java.util.TimerTask;

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
    TextView noFilmFoundText;

    private Handler handler = new Handler();
    private static final long Interval = 30;

    int count = 255;
    boolean iszero = false;

    com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation;

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int x = item.getItemId();
        if(x == R.id.settings)
        {
            startActivity(new Intent(getApplicationContext(),AdminNoticeScreen.class));

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

        noFilmFoundText = (TextView) findViewById(R.id.noFilmFoundText);

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
        if(names.size() > 0)
        {
            noFilmFoundText.setVisibility(View.GONE);
        }



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
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.adminmoviesearchrow,null);
            TextView filmName = view.findViewById(R.id.filmName);
            TextView filmRatings = view.findViewById(R.id.filmRatings);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
            TextView availableSeats = view.findViewById(R.id.availableSeats);
            ImageView filmPoster = view.findViewById(R.id.filmPoster);
            final ImageView arrow = view.findViewById(R.id.arrow);

            ImageView edit = view.findViewById(R.id.edit);
            ImageView delete = view.findViewById(R.id.delete);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(AdminMainInterface.this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(helper.deleteAFilm(ids.get(position).toString()))
                    {
                        ids.remove(position);
                        rankings.remove(position);
                        images.remove(position);
                        dates.remove(position);
                        times.remove(position);
                        seats.remove(position);
                        films.invalidateViews();
                        Toast.makeText(AdminMainInterface.this, "Deleted", Toast.LENGTH_SHORT).show();

                        if(names.size() <= 0)
                        {
                            noFilmFoundText.setVisibility(View.VISIBLE);
                        }
                    }
                    else
                    {
                        Toast.makeText(AdminMainInterface.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run()
                        {
                            if(count <= 0)
                            {
                                iszero = true;
                            }
                            if(count >= 255)
                            {
                                iszero = false;
                            }

                            if(!iszero)
                            {
                                count--;
                                arrow.setX(arrow.getX() - 0.2f);
                            }
                            if(iszero)
                            {
                                count++;
                                arrow.setX(arrow.getX() + 0.2f);
                            }
                            arrow.setAlpha(count);



                        }
                    });
                }
            },0,Interval);

            try
            {
                filmName.setText("Film Name : " +names.get(position));
                filmRatings.setText("Rankings : " +rankings.get(position));
                filmShowingDate.setText("Showing Date : "+dates.get(position));
                filmShowingTime.setText("Showing Time : "+times.get(position));
                availableSeats.setText("Available Seats : " + seats.get(position));
                filmPoster.setImageBitmap(images.get(position));

            }
            catch (Exception e)
            {

            }

            return view;
        }
    }

}
