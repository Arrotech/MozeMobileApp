package com.example.moze.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moze.AppSettings.MainActivity;
import com.example.moze.R;
import com.example.moze.UserAccount.Register.Register;

public class GettingStartedActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button btnGetStarted;
    TextView tvSplash3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started3);

        tvSplash3 = (TextView) findViewById(R.id.tvSplash3);
        btnGetStarted = (Button) findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(this);
        tvSplash3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGetStarted:
                Intent gettingStartedActivity = new Intent(GettingStartedActivity3.this, GettingStartedActivity.class);
                startActivity(gettingStartedActivity);
                break;
            case R.id.tvSplash3:
                Intent gettingStartedActivity2 = new Intent(GettingStartedActivity3.this, Register.class);
                startActivity(gettingStartedActivity2);
                break;
        }
    }
}
