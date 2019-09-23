package com.example.moze;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText firstname, lastname, phone, username,  email, password;
    Button bRegister;
    private TextView tvLoginLink;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname = (EditText) findViewById(R.id.etFirstName);
        lastname = (EditText) findViewById(R.id.etLastName);
        phone = (EditText) findViewById(R.id.etPhone);
        username = (EditText) findViewById(R.id.etUsername);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPassword);

        bRegister = findViewById(R.id.bRegister);

        tvLoginLink = findViewById(R.id.tvLoginLink);

        bRegister.setOnClickListener(this);
        tvLoginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bRegister:
                User user = new User(
                    firstname.getText().toString(),
                    lastname.getText().toString(),
                    phone.getText().toString(),
                    username.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()
                );
                userRegister(user);
                break;
            case R.id.tvLoginLink:
                Intent registerIntent = new Intent(Register.this, Login.class);
                startActivity(registerIntent);
                break;

        }
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void userRegister(User user){

        String fname = firstname.getText().toString().trim();
        String lname = lastname.getText().toString().trim();
        String phn = phone.getText().toString().trim();
        String uname = username.getText().toString().trim();
        String eml = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(fname.isEmpty()){
            firstname.setError("First name required");
            firstname.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            lastname.setError("Last name required");
            lastname.requestFocus();
            return;
        }

        if(phn.isEmpty()){
            phone.setError("Phone number required");
            phone.requestFocus();
            return;
        }

        if(uname.isEmpty()){
            username.setError("Username required");
            username.requestFocus();
            return;
        }

        if(eml.isEmpty()){
            email.setError("Email required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()){
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("Password required");
            password.requestFocus();
            return;
        }

        pDialog = new ProgressDialog(Register.this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Creating Account...");
        pDialog.setCancelable(false);

        showpDialog();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<User> call = serviceInterface.registerUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                hidepDialog();


                Toast.makeText(Register.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                Intent registerIntent = new Intent(Register.this, Login.class);
                startActivity(registerIntent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                hidepDialog();

                Toast.makeText(Register.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
