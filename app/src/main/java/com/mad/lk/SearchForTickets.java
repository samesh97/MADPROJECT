package com.mad.lk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchForTickets extends AppCompatActivity {


    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> rankings = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Integer> seats = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    DatabaseHelper helper;
    static Bitmap background;
    CircleImageView pic;
    private Handler handler = new Handler();
    private static final long Interval = 30;
    TextView noFilmFoundText;

    int count = 255;
    boolean iszero = false;



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int x = item.getItemId();
        if(x == R.id.settings)
        {
            startActivity(new Intent(getApplicationContext(),UserDisplayNotice.class));

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

    SearchView filmSearchView;
    String keyWord = null;
    ListView filmSearchList;
    com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_tickets);

        noFilmFoundText = (TextView) findViewById(R.id.noFilmFoundText);


        pic = (CircleImageView) findViewById(R.id.pic);

        helper = new DatabaseHelper(getApplicationContext());

        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String uName = mSharedPreference1.getString(  "username", null);
        Cursor data2 = helper.getUserData(uName);
        while (data2.moveToNext())
        {
            try
            {
                byte[] image2 = data2.getBlob(2);
                pic.setImageBitmap(helper.getImage(image2));
            }
            catch (Exception w)
            {

            }

        }
        data2.close();

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });







        filmSearchView = (SearchView)findViewById(R.id.filmSearchView);
        filmSearchList = (ListView) findViewById(R.id.filmSearchList);
        bottomNavigation = (com.google.android.material.bottomnavigation.BottomNavigationView) findViewById(R.id.bottomNavigation);


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {

                if(menuItem.getItemId() == R.id.navigationFavorite)
                {
                    //bottomNavigation.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
                    bottomNavigation.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gradient2));
                    //-----------favorite page-----------
                     startActivity(new Intent(getApplicationContext(),favorite_list_view.class));


                }
                if(menuItem.getItemId() == R.id.navigationOrders)
                {
                    bottomNavigation.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.navigation_gradient));
                    filmSearchList.invalidateViews();
                    startActivity(new Intent(getApplicationContext(),Transactions.class));
                }
                return false;
            }
        });








        filmSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                keyWord = query;

                setSearch();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                keyWord = newText;
                setSearch();
                return true;
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
            String des  = data.getString(3);
            int SEATS  = data.getInt(7);


            names.add(NAME);
            rankings.add(RANKINGS);
            dates.add(DATE);
            times.add(TIME);
            seats.add(SEATS);
            ids.add(IDS);
            images.add(helper.getImage(image));
            descriptions.add(des);





        }

        Adapter adapter = new Adapter();
        filmSearchList.setAdapter(adapter);

        if(names.size() > 0)
        {
            noFilmFoundText.setVisibility(View.GONE);
        }


    }
    public class Adapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
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

            View view = getLayoutInflater().inflate(R.layout.moviesearchrow,null);
            TextView filmName = view.findViewById(R.id.filmName);
            TextView filmRatings = view.findViewById(R.id.filmRatings);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);
            TextView availableSeats = view.findViewById(R.id.availableSeats);
            ImageView filmPoster = view.findViewById(R.id.filmPoster);
            final ImageView arrow = view.findViewById(R.id.arrow2);

            final ImageView info = view.findViewById(R.id.info);
            final ImageView fav = view.findViewById(R.id.fav);

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getApplicationContext(),activity_film_details_view.class);
                    intent.putExtra("ID",ids.get(position));
                    intent.putExtra("SEATS",seats.get(position));
                    intent.putExtra("NAME",names.get(position));
                    intent.putExtra("RANKINGS",rankings.get(position));
                    intent.putExtra("DATE",dates.get(position));
                    intent.putExtra("TIME",times.get(position));
                    background = images.get(position);
                    intent.putExtra("DESCRIPTION",descriptions.get(position));
                    startActivity(intent);
                }
            });


            fav.setOnClickListener(new View.OnClickListener() {
               @Override
              public void onClick(View v)
              {
                  Intent intent = new Intent(getApplicationContext(),FavoriteWithNote.class);
                  intent.putExtra("ID",ids.get(position));
                  intent.putExtra("SEATS",seats.get(position));
                  intent.putExtra("NAME",names.get(position));
                  intent.putExtra("RANKINGS",rankings.get(position));
                  intent.putExtra("DATE",dates.get(position));
                  intent.putExtra("TIME",times.get(position));
                  background = images.get(position);
                  intent.putExtra("DESCRIPTION",descriptions.get(position));
                 startActivity(intent);

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
                filmRatings.setText("Price : Rs " +rankings.get(position));
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
    public boolean checkUpperCaseAndLowerCase(String KeyWord,String filmName)
    {
        int count2 = 0;
        if (KeyWord.length() <= filmName.length())
        {
            for (int i = 0; i < KeyWord.length(); i++)
            {

                Character sample1 = filmName.charAt(i);
                Character lowerCase = Character.toLowerCase(sample1);
                Character upperCase = Character.toUpperCase(sample1);


                Character sample2 = KeyWord.charAt(i);
                Character lCase = Character.toLowerCase(sample2);
                Character uCase = Character.toUpperCase(sample2);

                if (lowerCase.equals(uCase) || upperCase.equals(lCase) || lowerCase.equals(lCase) || upperCase.equals(uCase)) {
                    count2++;
                    if (count2 == KeyWord.length())
                    {
                        return true;
                    }

                }


            }
        }
        return false;

    }
    public void setSearch()
    {

        names.clear();
        rankings.clear();
        dates.clear();
        seats.clear();
        times.clear();
        images.clear();
        descriptions.clear();
        times.clear();

        if(!keyWord.equals(""))
        {
            Cursor data = helper.getAllFimsDetails();
            while (data.moveToNext())
            {
                int IDS  = data.getInt(0);
                byte[] image  = data.getBlob(1);
                String NAME = data.getString(2);
                String RANKINGS  = data.getString(4);
                String DATE  = data.getString(5);
                String TIME  = data.getString(6);
                String des  = data.getString(3);
                int SEATS  = data.getInt(7);



                if(checkUpperCaseAndLowerCase(keyWord,NAME))
                {
                    names.add(NAME);
                    rankings.add(RANKINGS);
                    dates.add(DATE);
                    times.add(TIME);
                    seats.add(SEATS);
                    ids.add(IDS);
                    images.add(helper.getImage(image));
                    descriptions.add(des);
                }




            }
        }
        else
        {
            Cursor data = helper.getAllFimsDetails();
            while (data.moveToNext())
            {
                int IDS  = data.getInt(0);
                byte[] image  = data.getBlob(1);
                String NAME = data.getString(2);
                String RANKINGS  = data.getString(4);
                String DATE  = data.getString(5);
                String TIME  = data.getString(6);
                String des  = data.getString(3);
                int SEATS  = data.getInt(7);


                    names.add(NAME);
                    rankings.add(RANKINGS);
                    dates.add(DATE);
                    times.add(TIME);
                    seats.add(SEATS);
                    ids.add(IDS);
                    images.add(helper.getImage(image));
                    descriptions.add(des);

            }
        }

        filmSearchList.invalidateViews();
    }

}
