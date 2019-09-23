package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class updateBooking extends AppCompatActivity {

    Button updatebook;
    EditText newseats;
    Spinner newspinner;
    int updateBookingID;

    DBHelper_Lali helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        Intent intent = getIntent();
        updateBookingID = intent.getIntExtra("ID",0);

        helper =new DBHelper_Lali(getApplicationContext());

        updatebook =(Button)findViewById(R.id.button);
         newseats= (EditText)findViewById(R.id.editText);
         newspinner=(Spinner)findViewById(R.id.spinner);

         newseats.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (helper.updateBooking(newseats.getText().toString(),
                         String.valueOf(updateBookingID),newspinner.getSelectedItem().toString()))
                 {
                     Toast.makeText(updateBooking.this, "Updated", Toast.LENGTH_SHORT).show();
                     Intent intent =  new Intent(updateBooking.this,Transactions.class);
                     startActivity(intent);
                 }
                 else
                 {
                     Toast.makeText(updateBooking.this, "Not Updated", Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }
}