package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class favorite_list_view extends AppCompatActivity {



    ListView films;

    Adapter adapter;

    FavoriteDatabaseHelper helper;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> rankings = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Integer> seats = new ArrayList<>();
    ArrayList<String> description = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();

    private Handler handler = new Handler();

    private static final long Interval = 30;

    int count = 255;
    boolean iszero = false;



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
        setContentView(R.layout.activity_favorite_list_view);

        helper = new FavoriteDatabaseHelper(getApplicationContext());

        helper.getAllFavorite();




        Cursor data = helper.getAllFavorites()
                ;
        while (data.moveToNext())
        {
            int IDS  = data.getInt(0);
            byte[] image  = data.getBlob(1);
            String NAME = data.getString(2);
            String DESCRIPTION  = data.getString(3);
            String RANKINGS  = data.getString(4);
            String DATE  = data.getString(5);
            String TIME  = data.getString(6);
            int SEATS  = data.getInt(7);
            String NOTES  = data.getString(8);

            names.add(NAME);
            rankings.add(RANKINGS);
            description.add(DESCRIPTION);
            dates.add(DATE);
            times.add(TIME);
            seats.add(SEATS);
            ids.add(IDS);
            notes.add(NOTES);
            images.add(helper.getImage(image));

        }


        films = (ListView) findViewById(R.id.favfilmlist);

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
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.activity_favorite_list_view,null);

            TextView filmName = view.findViewById(R.id.favoritefilmname);
            TextView filmRatings = view.findViewById(R.id.favoriterank);
            TextView filmShowingDate = view.findViewById(R.id.favoritdate);
            TextView filmShowingTime = view.findViewById(R.id.favorittime);
            TextView availableSeats = view.findViewById(R.id.favoriteseat);
            ImageView filmPoster = view.findViewById(R.id.favoriteimage);
            TextView filmDescription = view.findViewById(R.id.favoritdesc);
            TextView filmnote = view.findViewById(R.id.favoritnote);


            ImageView delete = view.findViewById(R.id.btndeletefavorite);


            delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(helper.deleteFavoriteFilm(ids.get(position).toString()))
                    {
                        ids.remove(position);
                        rankings.remove(position);
                        images.remove(position);
                        dates.remove(position);
                        times.remove(position);
                        seats.remove(position);
                        films.invalidateViews();
                        seats.remove(position);

                        Toast.makeText(favorite_list_view.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(favorite_list_view.this, "wrong", Toast.LENGTH_SHORT).show();
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
                filmDescription.setText("Description : " +description.get(position));
                filmnote.setText("Notes : "+notes.get(position));

                filmPoster.setImageBitmap(images.get(position));

            }
            catch (Exception e)
            {

            }

            return view;
        }
    }


}
