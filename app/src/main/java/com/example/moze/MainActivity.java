package com.example.moze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText portfolio, occupation, phone, location, image, cost;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        portfolio = (EditText) findViewById(R.id.etPortfolio);
        occupation = (EditText) findViewById(R.id.etOccupation);
        phone = (EditText) findViewById(R.id.etPhone);
        location = (EditText) findViewById(R.id.etLocation);
        image = (EditText) findViewById(R.id.etImage);
        cost = (EditText) findViewById(R.id.etCost);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_service:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.view_services:
                startActivity(new Intent(this, ViewServices.class));
                return true;
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnAdd:
                Service service = new Service(
                        portfolio.getText().toString(),
                        occupation.getText().toString(),
                        phone.getText().toString(),
                        location.getText().toString(),
                        image.getText().toString(),
                        cost.getText().toString()
                );
                addService(service);
                break;

        }

    }
    private void addService(Service service){

        String fname = portfolio.getText().toString().trim();
        String lname = occupation.getText().toString().trim();
        String phn = phone.getText().toString().trim();
        String uname = location.getText().toString().trim();
        String eml = image.getText().toString().trim();
        String pass = cost.getText().toString().trim();

        if(fname.isEmpty()){
            portfolio.setError("First name required");
            portfolio.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            occupation.setError("Last name required");
            occupation.requestFocus();
            return;
        }

        if(phn.isEmpty()){
            phone.setError("Phone number required");
            phone.requestFocus();
            return;
        }

        if(uname.isEmpty()){
            location.setError("Username required");
            location.requestFocus();
            return;
        }

        if(eml.isEmpty()){
            image.setError("Email required");
            image.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            cost.setError("Password required");
            cost.requestFocus();
            return;
        }

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<Service> call = serviceInterface.addService(service);

        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {

                Toast.makeText(MainActivity.this, "You have added the service successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

                Toast.makeText(MainActivity.this, "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
