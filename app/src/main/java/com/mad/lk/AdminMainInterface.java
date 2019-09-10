package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AdminMainInterface extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int x = item.getItemId();
        if(x == R.id.settings)
        {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_SHORT).show();

        }
        if(x == R.id.logout)
        {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor mEdit1 = sp.edit();
            mEdit1.putString("username","Registered");
            mEdit1.commit();

            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

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
        setContentView(R.layout.activity_admin_main_interface);
    }
}
