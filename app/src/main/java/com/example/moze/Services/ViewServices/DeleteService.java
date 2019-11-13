package com.example.moze.Services.ViewServices;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moze.R;
import com.example.moze.Services.ServiceGenerator;
import com.example.moze.Services.interfaces.ServiceInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteService extends Fragment implements View.OnClickListener {

    EditText etDeleteService;
    Button btnDeleteService;
    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View searchView = inflater.inflate(R.layout.fragment_delete_service, container, false);
        setHasOptionsMenu(true);

        etDeleteService = (EditText) searchView.findViewById(R.id.etDeleteService);
        btnDeleteService = (Button) searchView.findViewById(R.id.btnDeleteService);

        btnDeleteService.setOnClickListener(this);

        return searchView;
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDeleteService:
                deleteService();
                break;
        }
    }


    private void deleteService(){

        int id = Integer.parseInt(etDeleteService.getText().toString());

        pDialog = new ProgressDialog(getActivity());
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Deleting...");
        pDialog.setCancelable(false);

        showpDialog();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<Void> call = serviceInterface.deleteService(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hidepDialog();
                Toast.makeText(getActivity(), "Deleted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hidepDialog();
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
