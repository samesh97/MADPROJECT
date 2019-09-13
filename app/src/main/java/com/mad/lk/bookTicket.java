package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
EditText seatqua;
Button idbtnbooking;
Button idbtnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        dbl = new DBHelper_Lali(this);

        seatqua = (EditText)findViewById(R.id.seatqua);
        spinner = (Spinner)findViewById(R.id.spinner);
        idbtnbooking = (Button)findViewById((R.id.idbtnbooking));
        idbtnview = (Button)findViewById(R.id.idbtnview) ;

        insertbooking();
        viewbooking();
    }

    public void insertbooking(){
        idbtnbooking.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                  boolean IsInserted = dbl.insertbooking(Integer.parseInt(seatqua.getText().toString()),spinner.getSelectedItem().toString());
                    if(IsInserted = true){
                        Toast.makeText(bookTicket.this,"Booking Confirmed",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(bookTicket.this,"Booking not Confirmed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

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
                            buffer.append("booking_ID :"+res.getString(0)+"\n");
                            buffer.append("seatqua :"+res.getInt(1)+"\n");
                            buffer.append("spinner :"+res.getString(2)+"\n\n");

                        }

                        showMassage("Data ",buffer.toString());
                    }
                }
        );
    }

    public void showMassage(String title,String massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();
    }

}
