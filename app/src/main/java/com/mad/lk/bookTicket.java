package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
        dbl = new DBHelper_Lali(this);

        seatqua = (EditText)findViewById(R.id.seatqua);
        spinner = (Spinner)findViewById(R.id.spinner);
        idbtnbooking = (Button)findViewById((R.id.idbtnbooking));

        insertbooking();
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
}
