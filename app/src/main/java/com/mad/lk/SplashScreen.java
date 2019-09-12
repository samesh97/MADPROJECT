package com.mad.lk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.appus.splash.Splash;
import android.os.Bundle;
import android.webkit.WebView;

import com.appus.splash.Splash;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
        splash.perform();
        splash.setBackgroundColor(getResources().getColor(R.color.blue));
        splash.setSplashImage(getResources().getDrawable(R.drawable.logo));
        splash.setSplashImageColor(getResources().getColor(R.color.white));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String username = preferences.getString("username", null);
                if(username == null || username.equals(""))
                {
                    startActivity(new Intent(getApplicationContext(),RegisterScreen.class));
                    finish();
                }
                else if(username.equals("Admin"))
                {
                    startActivity(new Intent(getApplicationContext(),AdminMainInterface.class));
                    finish();
                }
                else if(username.equals("Registered"))
                {
                    startActivity(new Intent(getApplicationContext(),LoginScreen.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),SearchForTickets.class));
                    finish();
                }


            }
        },2500);
    }
}
