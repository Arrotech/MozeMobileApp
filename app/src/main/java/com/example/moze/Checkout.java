package com.example.moze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moze.Daraja.MpesaAPI;
import com.example.moze.PayPal.PayPalPayments;

public class Checkout extends AppCompatActivity {

    private Button btnCheckoutMpesa, btnCheckoutPayPal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnCheckoutMpesa = (Button) findViewById(R.id.btnCheckoutMpesa);
        btnCheckoutPayPal = (Button) findViewById(R.id.btnCheckoutPayPal);

        btnCheckoutMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Checkout.this, MpesaAPI.class);
                startActivity(registerIntent);
            }
        });

        btnCheckoutPayPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Checkout.this, PayPalPayments.class);
                startActivity(registerIntent);
            }
        });
    }
}
