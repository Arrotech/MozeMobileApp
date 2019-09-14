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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServiceFragment extends Fragment implements View.OnClickListener {

    private EditText portfolio, occupation, phone, location, image, cost;
    Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View add_view = inflater.inflate(R.layout.fragment_add, container, false);
        setHasOptionsMenu(true);

        portfolio = (EditText) add_view.findViewById(R.id.etPortfolio);
        occupation = (EditText) add_view.findViewById(R.id.etOccupation);
        phone = (EditText) add_view.findViewById(R.id.etPhone);
        location = (EditText) add_view.findViewById(R.id.etLocation);
        image = (EditText) add_view.findViewById(R.id.etImage);
        cost = (EditText) add_view.findViewById(R.id.etCost);

        btnAdd = (Button) add_view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);


        return add_view;

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
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            case R.id.view_services:
                startActivity(new Intent(getActivity(), ViewServices.class));
                return true;
            case R.id.register:
                startActivity(new Intent(getActivity(), Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

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

        if (fname.isEmpty()) {
            portfolio.setError("First name required");
            portfolio.requestFocus();
            return;
        }

        if (lname.isEmpty()) {
            occupation.setError("Last name required");
            occupation.requestFocus();
            return;
        }

        if (phn.isEmpty()) {
            phone.setError("Phone number required");
            phone.requestFocus();
            return;
        }

        if (uname.isEmpty()) {
            location.setError("Username required");
            location.requestFocus();
            return;
        }

        if (eml.isEmpty()) {
            image.setError("Email required");
            image.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            cost.setError("Password required");
            cost.requestFocus();
            return;
        }

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<Service> call = serviceInterface.addService(service);

        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {

                Toast.makeText(getActivity(), "You have added the service successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

                Toast.makeText(getActivity(), "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
