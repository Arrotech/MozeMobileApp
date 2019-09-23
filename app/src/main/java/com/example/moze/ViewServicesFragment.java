package com.example.moze;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewServicesFragment extends Fragment{

    Button btnPay;
    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    private List<Service> serviceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View searchView = inflater.inflate(R.layout.fragment_view_services, container, false);
        setHasOptionsMenu(true);

        btnPay = searchView.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PayPalPayments.class));
            }
        });

        return searchView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvGetServices);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<ServiceResponse> call = serviceInterface.getServices();

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {

                serviceList = response.body().getServices();

                adapter = new ServicesAdapter(getActivity(), serviceList);

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_service:
                startActivity(new Intent(getActivity(), AddServiceFragment.class));
                return true;
            case R.id.view_services:
                startActivity(new Intent(getActivity(), ViewServicesFragment.class));
                return true;
            case R.id.register:
                startActivity(new Intent(getActivity(), Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
