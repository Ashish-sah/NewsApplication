package com.ashish.NewsApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ashish.NewsApplication.BusinessFolder.BusinessActivity;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN = 5000 ;   //1000 means 1sec so 5000 means 5sec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //create splash screen
        //this handle delay method
        //
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(getApplicationContext(), BusinessActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}