package com.example.moze;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServiceFragment extends Fragment implements View.OnClickListener {

    private EditText etName, etBusinessName, etDescription, etPhone, etLocation, etWorkingHours, etCost;
    private Spinner spPortfolio, spOccupation;
    private ProgressDialog pDialog;
    String[] portfolio = {"Education", "Health", "Technical", "Entertainment", "Domestic"};
    String[] occupation = {"Carpentry", "Music", "Foods & Drinks", "Electrician", "Computer Services", "Mechanic", "Teacher", "Gym", "Transport Services", "Farming Services", "Doctor"};
    Button btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View add_view = inflater.inflate(R.layout.fragment_add, container, false);
        setHasOptionsMenu(true);

        etName = (EditText) add_view.findViewById(R.id.etName);
        etBusinessName = (EditText) add_view.findViewById(R.id.etBusinessName);
        spPortfolio = (Spinner) add_view.findViewById(R.id.spPortfolio);
        spOccupation = (Spinner) add_view.findViewById(R.id.spOccupation);
        etDescription = (EditText) add_view.findViewById(R.id.etDescription);
        etPhone = (EditText) add_view.findViewById(R.id.etPhone);
        etLocation = (EditText) add_view.findViewById(R.id.etLocation);
        etWorkingHours = (EditText) add_view.findViewById(R.id.etWorkingHours);
        etCost = (EditText) add_view.findViewById(R.id.etCost);
        btnAdd = (Button) add_view.findViewById(R.id.btnAdd);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,portfolio);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPortfolio.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,occupation);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOccupation.setAdapter(adapter2);

        btnAdd.setOnClickListener(this);


        return add_view;

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

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
                String name = etName.getText().toString();
                String business_name = etBusinessName.getText().toString();
                String portfolio = spPortfolio.getSelectedItem().toString();
                String occupation = spOccupation.getSelectedItem().toString();
                String description = etDescription.getText().toString();
                String phone = etPhone.getText().toString();
                String location = etLocation.getText().toString();
                String working_hours = etWorkingHours.getText().toString();
                String cost = etCost.getText().toString();
                Service service = new Service(name, business_name, portfolio, occupation, description, phone, location, working_hours, cost);
                addService(service);
                break;

        }
    }

    private void addService(Service service){

        String name = etName.getText().toString().trim();
        String business_name = etBusinessName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String working_hours = etWorkingHours.getText().toString().trim();
        String cost = etCost.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Name required");
            etName.requestFocus();
            return;
        }

        if (business_name.isEmpty()) {
            etBusinessName.setError("Business name required");
            etBusinessName.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            etDescription.setError("Description required");
            etDescription.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            etPhone.setError("Phone number required");
            etPhone.requestFocus();
            return;
        }

        if (location.isEmpty()) {
            etLocation.setError("Username required");
            etLocation.requestFocus();
            return;
        }

        if (working_hours.isEmpty()) {
            etWorkingHours.setError("Email required");
            etWorkingHours.requestFocus();
            return;
        }

        if (cost.isEmpty()) {
            etCost.setError("Password required");
            etCost.requestFocus();
            return;
        }

        pDialog = new ProgressDialog(getActivity());
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Processing...");
        pDialog.setCancelable(false);

        showpDialog();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<Service> call = serviceInterface.addService(service);

        call.enqueue(new Callback<Service>() {
            @Override
            public void onResponse(Call<Service> call, Response<Service> response) {

                hidepDialog();

                Toast.makeText(getActivity(), "You have added the service successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Service> call, Throwable t) {

                hidepDialog();

                Toast.makeText(getActivity(), "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
