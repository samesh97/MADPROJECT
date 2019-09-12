package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad.lk.Film_Details_Maintain.DatabaseHelper_Lali;

public class bookTicket extends AppCompatActivity {

        DatabaseHelper_Lali db;
        EditText editDate,editTime,editphoneNumber,editAdult,editChild;
        Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);

        db = new DatabaseHelper_Lali(this);

        editDate = (EditText)findViewById(R.id.date);
        editTime = (EditText)findViewById(R.id.time);
        editphoneNumber = (EditText)findViewById(R.id.phoneNumber);
        editAdult = (EditText)findViewById(R.id.adult);
        editChild = (EditText)findViewById(R.id.child);
        btnAdd = (Button)findViewById(R.id.book);
        addData();
    }

    public void addData(){
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted = db.insertData(editDate.getText().toString(),
                                editTime.getText().toString(),
                                editphoneNumber.getText().toString(),
                                editAdult.getText().toString(),
                                editChild.getText().toString());
                       if ( isInserted = true){
                           Toast.makeText(bookTicket.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(bookTicket.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                       }
                    }
                }
        );
    }

    public void openafterBookingPage(View view) {
        startActivity(new Intent(bookTicket.this,afterBooking.class));
    }
}
