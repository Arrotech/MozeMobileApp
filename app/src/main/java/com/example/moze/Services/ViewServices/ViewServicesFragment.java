package com.example.moze.Services.ViewServices;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moze.Services.AddServices.AddServiceFragment;
import com.example.moze.R;
import com.example.moze.UserAccount.Register.Register;
import com.example.moze.Services.model.Service;
import com.example.moze.Services.ServiceGenerator;
import com.example.moze.Services.interfaces.ServiceInterface;
import com.example.moze.Services.model.ServiceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewServicesFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ServicesAdapter adapter;
    Button btnGetServicesOccupation;
    Spinner spGetServicesOccupation;
    String[] occupation = {"Carpentry", "Music", "Foods & Drinks", "Electrician", "Computer Services", "Mechanic", "Teacher", "Gym", "Transport Services", "Farming Services", "Doctor"};
    List<Service> serviceList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View searchView = inflater.inflate(R.layout.fragment_view_services, container, false);
        setHasOptionsMenu(true);

        recyclerView = searchView.findViewById(R.id.rvGetServices);
        spGetServicesOccupation = (Spinner) searchView.findViewById(R.id.spGetServicesOccupation);
        btnGetServicesOccupation = (Button) searchView.findViewById(R.id.btnGetServicesOccupation);

        btnGetServicesOccupation.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,occupation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGetServicesOccupation.setAdapter(adapter);

        return searchView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnGetServicesOccupation:
                getServicesByOccupation();
                break;

        }

    }

    public void getServicesByOccupation(){

        String occupation = spGetServicesOccupation.getSelectedItem().toString();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<ServiceResponse> call = serviceInterface.getServices(occupation);

        call.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {

                if(response.isSuccessful()){
                    serviceList = response.body().getServices();
                    adapter = new ServicesAdapter(getActivity(), serviceList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
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
