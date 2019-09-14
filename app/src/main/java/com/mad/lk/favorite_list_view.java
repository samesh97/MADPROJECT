package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class favorite_list_view extends AppCompatActivity {



    ListView films;

    Button update;

    Adapter adapter;

    FavoriteDatabaseHelper helper;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> rankings = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Integer> seats = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<Bitmap> images = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list_view);

        helper = new FavoriteDatabaseHelper(getApplicationContext());
        films = (ListView) findViewById(R.id.favfilmlist);

        Cursor data = helper.getAllFavorites();
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
            films.invalidateViews();



        }

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
            View view = getLayoutInflater().inflate(R.layout.activity_favorite_film,null);

            TextView filmName = view.findViewById(R.id.favoritefilmname);
            TextView filmRatings = view.findViewById(R.id.favoriterank);
            TextView filmShowingDate = view.findViewById(R.id.favoritdate);
            TextView filmShowingTime = view.findViewById(R.id.favorittime);
            TextView availableSeats = view.findViewById(R.id.favoriteseat);
            ImageView filmPoster = view.findViewById(R.id.favoriteimage);
            TextView filmDescription = view.findViewById(R.id.favoritdesc);
            TextView filmnote = view.findViewById(R.id.favoritnote);


            Button update =view.findViewById(R.id.btnupdatefavorite);
            Button delete = view.findViewById(R.id.btndeletefavorite);

            filmName.setText("Film Name : " +names.get(position));
            filmRatings.setText("Rankings : " +rankings.get(position));
            filmShowingDate.setText("Showing Date : "+dates.get(position));
            filmShowingTime.setText("Showing Time : "+times.get(position));
            availableSeats.setText("Available Seats : " + seats.get(position));
            filmDescription.setText("Description : " +description.get(position));
            filmnote.setText("Notes : "+notes.get(position));

            filmPoster.setImageBitmap(images.get(position));

            delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getApplicationContext(), "" + names.size(), Toast.LENGTH_SHORT).show();
                    if(helper.deleteFavoriteFilm(ids.get(position).toString()))
                    {
                        try
                        {
                            ids.remove(position);
                            rankings.remove(position);
                            images.remove(position);
                            dates.remove(position);
                            times.remove(position);
                            seats.remove(position);
                            films.invalidateViews();
                            seats.remove(position);
                            notes.remove(position);
                        }
                        catch (Exception e)

                        {

                        }

                        Toast.makeText(favorite_list_view.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(favorite_list_view.this, "wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(favorite_list_view.this,"Update your note here",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(favorite_list_view.this,FavUpdateNote.class);
                            startActivity(intent);
              /* String noteText = notes.getText().toString();

                   if(!notef.equals(""))
                       Toast.makeText(favorite_list_view.this, "Please fill out the field", Toast.LENGTH_SHORT).show();
                   else
                   if(fhelper.updateFavorite(notef))

                       Toast.makeText(favorite_list_view.this, "Updated!", Toast.LENGTH_SHORT).show();
*/
                }
            });



            return view;
        }
    }


}
