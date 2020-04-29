package com.example.simplemonkey;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private final int TIMEOUT = 2000;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();  // OCULTA LA APPBAR DEL SPLASH
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            public void run(){
            Intent intent = new Intent(SplashScreen.this, AccountLogin.class);
            startActivity(intent);
            finish();
        };
    }, TIMEOUT);

    }
}





