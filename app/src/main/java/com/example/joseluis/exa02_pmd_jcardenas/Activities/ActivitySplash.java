package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.joseluis.exa02_pmd_jcardenas.R;

public class ActivitySplash extends AppCompatActivity {
    private final int DURACION_SPLASH = 20000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(ActivitySplash.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
