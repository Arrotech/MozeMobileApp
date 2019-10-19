package com.example.moze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewServicesByLocation extends Fragment {

    private TextView tvServicesLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View searchView = inflater.inflate(R.layout.fragment_view_services_by_location, container, false);
        setHasOptionsMenu(true);

        tvServicesLocation = (TextView) searchView.findViewById(R.id.tvServicesLocation);

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<ServiceResponse> call = serviceInterface.getServicesByLocation();

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {

                if(!response.isSuccessful()){

                    tvServicesLocation.setText("Code: " + response.code());
                    return;
                }

                ServiceResponse services = response.body();


                tvServicesLocation.setText(
                        services.getServices().toString()
                );


            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

                tvServicesLocation.setText(t.getMessage());

            }
        });

        return searchView;
    }
}
