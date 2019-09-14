package com.example.moze;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewServices extends AppCompatActivity {

    private TextView tvViewServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        tvViewServices = findViewById(R.id.tvViewServices);


        getAllServices();


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
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @SuppressLint("RestrictedApi")
    private void getAllServices(){

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<List<Service>> call = serviceInterface.getServices("Electrical");

        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {

                List<Service> services = response.body();

                for(Service service: services){

                    String content = "";
                    content += "Occupation" + service.getOccupation() + "\n";
                    content += "Phone" + service.getPhone() + "\n";
                    content += "Location" + service.getLocation() + "\n";
                    content += "Cost" + service.getCost() + "\n\n";

                    tvViewServices.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {

                tvViewServices.setText(t.getMessage());

            }
        });

    }


}
