package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchForTickets extends AppCompatActivity {

    SearchView filmSearchView;
    String keyWord = null;
    ListView filmSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_tickets);

        filmSearchView = (SearchView)findViewById(R.id.filmSearchView);
        filmSearchList = (ListView) findViewById(R.id.filmSearchList);

        ListViewAdapter adapter = new ListViewAdapter();
        filmSearchList.setAdapter(adapter);

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
    }
    public class ListViewAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 15;
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

            filmName.setText("Minority Reports");
            filmRatings.setText("1.9");
            filmShowingDate.setText("2019/08/20");
            availableSeats.setText("250");
            return view;
        }
    }
}
