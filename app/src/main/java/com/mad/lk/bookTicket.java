package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        intent = getIntent();
        numberOfSeats = intent.getIntExtra("SEATS",0);
        filmName = intent.getStringExtra("NAME");
        time = intent.getStringExtra("TIME");
        date = intent.getStringExtra("DATE");



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

                 if(seatqua.getText().toString().equals(""))
                 {
                     Toast.makeText(bookTicket.this, "Enter number of seats", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 boolean IsInserted = dbl.insertbooking(filmName,Integer.parseInt(seatqua.getText().toString()),spinner.getSelectedItem().toString(),time,date);
                 if(IsInserted)
                 {
                     Toast.makeText(bookTicket.this,"Booking Confirmed",Toast.LENGTH_LONG).show();
                 }
                 else
                 {
                     Toast.makeText(bookTicket.this,"Booking not Confirmed",Toast.LENGTH_LONG).show();
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
