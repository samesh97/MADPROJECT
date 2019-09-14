package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FavoriteWithNote extends AppCompatActivity {

TextView notes;

FavoriteDatabaseHelper fhelper;
Button view,add,update;
//DatabaseHelper db;
String filmnamef = "aaa",descriptionf="bbb",notef="ccc",rankf="ddd",datef="eee",timef="ff",seatf="17";
Bitmap imagef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_with_note);

        notes = (TextView) findViewById(R.id.favoritnote);
       add =(Button)findViewById(R.id.btnfavAdd);
        view =(Button)findViewById(R.id.favView);
        update =(Button)findViewById(R.id.btnfavupdate);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FavoriteWithNote.this, "Clicked", Toast.LENGTH_SHORT).show();

                //  Cursor res = db.getAllFimsDetails();
                 //if(res.getCount()==0){
                  // showMassage("Error!","Nothing Found!");
                  //return;
                  //}
                //StringBuffer buffer = new StringBuffer();
                //while (res.moveToNext()){
                  // buffer.append("Film ID :"+res.getString(0)+"\n");
                 // buffer.append("Film Name :"+res.getString(1)+"\n");
                 //buffer.append("Description :"+res.getString(2)+"\n");
                //buffer.append("Image :"+res.getString(3)+"\n");
               // buffer.append(" Rank:"+res.getString(4)+"\n");
                //buffer.append("Date :"+res.getInt(5)+"\n");
              //   buffer.append("Time :"+res.getString(6)+"\n\n");
               //buffer.append("Seat :"+res.getString(7)+"\n\n");
                //}
                //showMassage("Your Favourite Films",buffer.toString());
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // boolean IsInserted = fhelper.addfavorites(filmnamef,descriptionf,rankf,datef,timef,Integer.parseInt(seatf),fhelper.getBytes(imagef),notef);

                //if(IsInserted)
                //{
                  //  Toast.makeText(FavoriteWithNote.this,"data inserted",Toast.LENGTH_LONG).show();
                //}
                //else
                //{
                  //  Toast.makeText(FavoriteWithNote.this,"error",Toast.LENGTH_LONG).show();
                //}
                String nameText = filmnamef.toString();
                String rankText = rankf.toString();
                String dateText = datef.toString();
                String timeText = timef.toString();
                String seatsText = seatf.toString();
                String descriptionText = descriptionf.toString();
                String noteText = notef.toString();


                if(fhelper.addfavorites(nameText,descriptionText,rankText,dateText,timeText,Integer.parseInt(seatsText),fhelper.getBytes(imagef),seatsText))
                {
                    Toast.makeText(FavoriteWithNote.this,"error",Toast.LENGTH_LONG).show();
                   // startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
                    //finish();
                }
                else
                {
                    Toast.makeText(FavoriteWithNote.this,"error",Toast.LENGTH_LONG).show();
                }


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = notes.getText().toString();

                   if(!notef.equals(""))
                       Toast.makeText(FavoriteWithNote.this, "Please fill out the field", Toast.LENGTH_SHORT).show();
                   else
                   if(fhelper.updateFavorite(notef))

                       Toast.makeText(FavoriteWithNote.this, "Updated!", Toast.LENGTH_SHORT).show();

            }
        });

    }



      //  view.setOnClickListener(
        //        new View.OnClickListener() {
         //           @Override
                  // public void onClick(View v) {


                     //  Cursor res = db.getAllFimsDetails();
                       // if(res.getCount()==0){
                         //   showMassage("Error!","Nothing Found!");
                          //  return;
                      //  }
                        //StringBuffer buffer = new StringBuffer();
                        //while (res.moveToNext()){
                         //   buffer.append("Film ID :"+res.getString(0)+"\n");
                          //  buffer.append("Film Name :"+res.getString(1)+"\n");
                           // buffer.append("Description :"+res.getString(2)+"\n");
                            //buffer.append("Image :"+res.getString(3)+"\n");
                            //buffer.append(" Rank:"+res.getString(4)+"\n");
                            //buffer.append("Date :"+res.getInt(5)+"\n");
                           // buffer.append("Time :"+res.getString(6)+"\n\n");
                           // buffer.append("Seat :"+res.getString(7)+"\n\n");

                        //}
                        //showMassage("Your Favourite Films",buffer.toString());
                    //}
               // }
        //);
    //}

   // public void showMassage(String title,String massage) {
     //   AlertDialog.Builder builder = new AlertDialog.Builder(this);
      //  builder.setCancelable(true);
       // builder.setTitle(title);
        //builder.setMessage(massage);
        //builder.show();
    //}


}
