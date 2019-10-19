package com.example.moze.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moze.AppSettings.MainActivity;
import com.example.moze.R;

public class GettingStartedActivity2 extends AppCompatActivity implements  View.OnClickListener {

    ImageView btnGettingStarted2;
    TextView tvSplash2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started2);

        btnGettingStarted2 = (ImageView) findViewById(R.id.btnGettingStarted2);
        tvSplash2 = (TextView) findViewById(R.id.tvSplash2);

        btnGettingStarted2.setOnClickListener(this);
        tvSplash2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGettingStarted2:
                Intent mainActivityIntent = new Intent(GettingStartedActivity2.this, MainActivity.class);
                startActivity(mainActivityIntent);
                break;
            case R.id.tvSplash2:
                Intent mainActivityIntent2 = new Intent(GettingStartedActivity2.this, MainActivity.class);
                startActivity(mainActivityIntent2);
                break;
        }
    }
}
