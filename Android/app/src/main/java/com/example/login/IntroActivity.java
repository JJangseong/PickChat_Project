package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().setTitle("");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override

            public void run() {
                Intent intent = new Intent(IntroActivity.this, FirstPageActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
