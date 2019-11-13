package com.example.moze.UserAccount.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moze.PayPal.PayPalPayments;
import com.example.moze.UserAccount.Model.LoginResponse;
import com.example.moze.R;
import com.example.moze.Services.ServiceGenerator;
import com.example.moze.Services.interfaces.ServiceInterface;
import com.example.moze.UserAccount.Register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    Button bLogin;
    private ProgressDialog pDialog;
    private TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        bLogin = findViewById(R.id.bLogin);

        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bLogin:
                LoginResponse loginResponse = new LoginResponse(
                    etEmail.getText().toString(),
                    etPassword.getText().toString()
                );
                userLogin(loginResponse);
                break;
            case R.id.tvRegisterLink:
                Intent loginIntent = new Intent(Login.this, Register.class);
                startActivity(loginIntent);
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

    private void userLogin(LoginResponse loginResponse){

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        if(email.isEmpty()){
            etEmail.setError("Email required");
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError("Password required");
            etPassword.requestFocus();
            return;
        }

        pDialog = new ProgressDialog(Login.this);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Authenticating...");
        pDialog.setCancelable(false);

        showpDialog();

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<LoginResponse> call = serviceInterface.loginUser(loginResponse);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                hidepDialog();

                if(response.isSuccessful()){
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();

                    Intent registerIntent = new Intent(Login.this, PayPalPayments.class);
                    startActivity(registerIntent);
                }else{

                    Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(Login.this, Login.class);
                    startActivity(registerIntent);

                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                hidepDialog();

                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
