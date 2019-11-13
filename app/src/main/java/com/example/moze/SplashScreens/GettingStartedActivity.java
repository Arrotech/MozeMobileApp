package com.example.moze.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moze.AppSettings.MainActivity;
import com.example.moze.R;

public class GettingStartedActivity extends AppCompatActivity implements  View.OnClickListener {

    ImageView btnGettingStarted, btnGettingStartedBack;
    TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        btnGettingStarted = (ImageView) findViewById(R.id.btnGettingStarted);
        btnGettingStartedBack = (ImageView) findViewById(R.id.btnGettingStartedBack);
        tvSplash = (TextView) findViewById(R.id.tvSplash);

        btnGettingStarted.setOnClickListener(this);
        btnGettingStartedBack.setOnClickListener(this);
        tvSplash.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGettingStarted:
                Intent mainActivityIntent = new Intent(GettingStartedActivity.this, GettingStartedActivity2.class);
                startActivity(mainActivityIntent);
                break;
            case R.id.btnGettingStartedBack:
                Intent onbackpressed = new Intent(GettingStartedActivity.this, GettingStartedActivity3.class);
                startActivity(onbackpressed);
                break;
            case R.id.tvSplash:
                Intent mainActivityIntent2 = new Intent(GettingStartedActivity.this, MainActivity.class);
                startActivity(mainActivityIntent2);
                break;
        }
    }
}
