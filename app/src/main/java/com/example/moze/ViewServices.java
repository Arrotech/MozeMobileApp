package com.example.moze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewServices extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_service:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.view_services:
                startActivity(new Intent(this, ViewServices.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void init() {
        editText = findViewById(R.id.occupation_name);
        button = findViewById(R.id.occupation_click);
        responseText = findViewById(R.id.response_text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchServicesByOccupation();
            }
        });
    }

    private void fetchServicesByOccupation() {
        //Obtain an instance of Retrofit by calling the static method.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moze-api-endpoints.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);

        Call call = serviceInterface.getServices(editText.getText().toString());

        call.enqueue(new Callback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call call, Response response) {

                if (response.body() != null) {

                    Main main = (Main) response.body();

                    responseText.setText("Portfolio: " + main.getService().getPortfolio() + "\n " +
                            "Occupation: " + main.getService().getOccupation() + "\n" +
                            "Phone: " + main.getService().getPhone());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(ViewServices.this, "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
