package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchForTickets extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int x = item.getItemId();
        if(x == R.id.settings)
        {
            Intent intent = new Intent(getApplicationContext(),NoticeUserDisplayNotices.class);
            startActivity(intent);

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
    ArrayList<Integer> filmPosters;
    ArrayList<String> filmname,date,ranking,time,availableSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_tickets);

        filmSearchView = (SearchView)findViewById(R.id.filmSearchView);
        filmSearchList = (ListView) findViewById(R.id.filmSearchList);

        filmPosters = new ArrayList<>();
        filmname = new ArrayList<>();
        date = new ArrayList<>();
        ranking = new ArrayList<>();
        time = new ArrayList<>();
        availableSets = new ArrayList<>();


        filmPosters.add(R.drawable.missionimpossible);
        filmPosters.add(R.drawable.purge);
        filmPosters.add(R.drawable.blackpanther);
        //filmPosters.add(R.drawable.bladerunner);
        filmPosters.add(R.drawable.fantasticfour);
        filmPosters.add(R.drawable.ghoststories);
        filmPosters.add(R.drawable.hitman);
        filmPosters.add(R.drawable.ironman);
        //filmPosters.add(R.drawable.minorityreport);
        //filmPosters.add(R.drawable.moonlight);
        //filmPosters.add(R.drawable.robinhood);
        //filmPosters.add(R.drawable.strangerthings);
        //filmPosters.add(R.drawable.theneondemon);
        //filmPosters.add(R.drawable.thor);
        //filmPosters.add(R.drawable.venom);



        filmSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                keyWord = query;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        ListViewAdapter adapter = new ListViewAdapter();
        filmSearchList.setAdapter(adapter);

        filmSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(),thara2.class);
                startActivity(intent);
            }
        });
    }
    public class ListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return filmPosters.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.moviesearchrow,null);
            TextView filmName = view.findViewById(R.id.filmName);
            TextView filmRatings = view.findViewById(R.id.filmRatings);
            TextView filmShowingDate = view.findViewById(R.id.filmShowingDate);
            TextView availableSeats = view.findViewById(R.id.availableSeats);
            ImageView filmPoster = view.findViewById(R.id.filmPoster);
            TextView filmShowingTime = view.findViewById(R.id.filmShowingTime);

            filmPoster.setImageResource(filmPosters.get(position));

            filmName.setText("Film Name : " + "Minority Reports");
            filmRatings.setText("Rankings : " + "1.9");
            filmShowingDate.setText("Showing Date : "+"2019/08/20");
            availableSeats.setText("Available Seats : " + "250");
            filmShowingTime.setText("Showing Time : " + "10.30am");
            return view;
        }
    }
}
