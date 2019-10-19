package com.example.moze.PayPal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moze.R;

public class ServiceFeeCheckout extends AppCompatActivity {

    Button bServiceFeePayPal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_fee_checkout);

        bServiceFeePayPal = (Button) findViewById(R.id.bServiceFeePayPal);
        bServiceFeePayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(ServiceFeeCheckout.this, PayPalPayments.class);
                startActivity(loginIntent);
            }
        });
    }
}
