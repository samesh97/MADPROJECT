package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class film_details_maintain extends AppCompatActivity {

    Button btnadd;
    Button btnclear;
    Button btnupdate;
    Button btndelete;

    EditText txtfilmname;
    EditText txtrole1;
    EditText txtrole2;
    EditText txtrole3;
    EditText txtrole4;
    EditText txtdirector;

    TextView image1;
    TextView image2;
    TextView image3;
    TextView image4;

    ImageView viewimage1;
    ImageView viewimage2;
    ImageView viewimage3;
    ImageView viewimage4;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details_maintain);

        btnadd = findViewById(R.id.idbtnAdd);
        btnclear = findViewById(R.id.idbtnClear);
        btnupdate = findViewById(R.id.idbtnUpdate);
        btndelete = findViewById(R.id.idbtnDelete);

        txtfilmname = (EditText) findViewById(R.id.idtxtFilmName);

        txtrole1 = (EditText) findViewById(R.id.idtxtRole1);

        txtrole2 = (EditText) findViewById(R.id.idtxtRole2);

       txtrole3 = (EditText) findViewById(R.id.idtxtRole3);

        txtrole4 = (EditText) findViewById(R.id.idtxtRole4);

        txtdirector = (EditText) findViewById(R.id.idtxtDirector);

        image1 = (TextView) findViewById(R.id.idtxtphoto1);
        viewimage1 = (ImageView) findViewById(R.id.idimage1);

        image2 = (TextView) findViewById(R.id.idtxtphoto2);
        viewimage2 = (ImageView) findViewById(R.id.idimage2);

        image3 = (TextView) findViewById(R.id.idtxtphoto3);
        viewimage3 = (ImageView) findViewById(R.id.idimage3);

        image4 = (TextView) findViewById(R.id.idtxtphoto4);
        viewimage4 = (ImageView) findViewById(R.id.idimage4);





    }

    public void ShowToastAdd(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Add sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastClear(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Clear sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastUpdate(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Update sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void ShowToastDelete(View view) {
        Toast toastAdd = Toast.makeText(getApplicationContext(),"Delete sucessfully",Toast.LENGTH_LONG);
        toastAdd.show();
    }

    public void btn_click(View view) {
    }
}
