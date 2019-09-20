package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class bookTicket extends AppCompatActivity {

    DBHelper_Lali dbl;
    Spinner spinner;
    EditText seatqua;//,booking_ID;
    Button idbtnbooking;
    //Button idbtnview;
    //Button btnupdate;
    //Button btnDelete;
    Intent intent;
    int numberOfSeats;
    String filmName,time,date;
    int id;
    DatabaseHelper helper;
    byte[] image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        helper = new DatabaseHelper(bookTicket.this);


        intent = getIntent();
        numberOfSeats = intent.getIntExtra("SEATS",0);
        filmName = intent.getStringExtra("NAME");
        time = intent.getStringExtra("TIME");
        date = intent.getStringExtra("DATE");
        id = intent.getIntExtra("ID",0);

        Cursor cursor = helper.getFilmData(String.valueOf(id));
        while (cursor.moveToNext())
        {
            image = cursor.getBlob(1);
        }



        dbl = new DBHelper_Lali(getApplicationContext());

        seatqua = (EditText)findViewById(R.id.seatqua);
        //booking_ID = (EditText)findViewById(R.id.booking_ID);
        spinner = (Spinner)findViewById(R.id.spinner);
        idbtnbooking = (Button)findViewById((R.id.idbtnbooking));
        //idbtnview = (Button)findViewById(R.id.idbtnview);
        //btnupdate = (Button)findViewById(R.id.btnupdate);
        //btnDelete = (Button)findViewById(R.id.btndelete);


        //viewbooking();
        //updatebooking();
        //deletebooking();


        idbtnbooking.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View view)
             {
                 if(numberOfSeats <= 0)
                 {
                     Toast.makeText(bookTicket.this, "All the seats are already Reserverd", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(seatqua.getText().toString().equals(""))
                 {
                     Toast.makeText(bookTicket.this, "Enter number of seats", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if((numberOfSeats - (Integer.parseInt(seatqua.getText().toString()))) <= 0)
                 {
                     Toast.makeText(bookTicket.this, "You cannot reserve this amount of tickets right now because the amount you entered is larger than the available tickets", Toast.LENGTH_LONG).show();
                     return;
                 }
                 boolean IsInserted = dbl.insertbooking(filmName,Integer.parseInt(seatqua.getText().toString()),spinner.getSelectedItem().toString(),time,date,image,id);
                 if(IsInserted)
                 {
                     if(helper.updateSeatCount(String.valueOf(id),numberOfSeats - (Integer.parseInt(seatqua.getText().toString()))))
                     {
                         Toast.makeText(bookTicket.this,"Booking Confirmed",Toast.LENGTH_LONG).show();
                     }

                 }
                 else
                 {
                     if(dbl.isTranscationAlreadyExist(String.valueOf(id)))
                     {
                         Toast.makeText(bookTicket.this, "You have already booked few tickets for this film, You can edit or delete it in  transaction page", Toast.LENGTH_LONG).show();
                     }
                     else
                     {
                         Toast.makeText(bookTicket.this,"Could not add to database",Toast.LENGTH_LONG).show();
                     }

                 }

             }
         }
        );
    }
/*
    public void deletebooking(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRaws = dbl.deletebooking(booking_ID.getText().toString());
                        if (deletedRaws > 0) {
                            Toast.makeText(bookTicket.this,"Booking Deleted",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(bookTicket.this,"Booking not Deleted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }*/
/*
    public void updatebooking(){
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean IsUpdate = dbl.updatebooking(booking_ID.getText().toString(),
                                Integer.parseInt(seatqua.getText().toString()),
                                spinner.getSelectedItem().toString());
                        if(IsUpdate == true )
                        {
                            Toast.makeText(bookTicket.this,"Booking Updated",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(bookTicket.this,"Booking not Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }*/




/*
    public void viewbooking(){
        idbtnview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = dbl.getAllbooking();
                        if(res.getCount()==0){
                            showMassage("Error!","Nothing Found!");
                            return;
                    }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Booking_ID :"+res.getString(0)+"\n");
                            buffer.append("Seat Quantity :"+res.getInt(1)+"\n");
                            buffer.append("Seat Type :"+res.getString(2)+"\n\n");

                        }

                        showMassage("Data ",buffer.toString());
                    }
                }
        );
    }*/

    public void showMassage(String title,String massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();
    }

}
