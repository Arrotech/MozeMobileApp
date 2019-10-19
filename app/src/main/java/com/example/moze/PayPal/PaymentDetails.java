package com.example.moze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView tvPaymentID, tvPaymentAmount, tvPaymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        tvPaymentID = (TextView) findViewById(R.id.tvPaymentID);
        tvPaymentAmount = (TextView) findViewById(R.id.tvPaymentAmount);
        tvPaymentStatus = (TextView) findViewById(R.id.tvPaymentStatus);

        Intent intent = getIntent();

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String paymentAmount) {

        try {
            tvPaymentID.setText(response.getString("id"));
            tvPaymentAmount.setText(response.getString("$"+paymentAmount));
            tvPaymentStatus.setText(response.getString("state"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
