package com.example.moze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    Button bLogin;
    private TextView tvRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        bLogin = findViewById(R.id.bLogin);

        tvRegisterLink = findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bLogin:
                UserLogin user = new UserLogin(
                    email.getText().toString(),
                    password.getText().toString()
                );
                userLogin(user);
                break;
            case R.id.tvRegisterLink:
                Intent loginIntent = new Intent(Login.this, Register.class);
                startActivity(loginIntent);
                break;
        }

    }

    private void userLogin(UserLogin user){

        String eml = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

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
            email.setError("Password required");
            email.requestFocus();
            return;
        }

        ServiceInterface serviceInterface = ServiceGenerator.createService(ServiceInterface.class);

        Call<UserLogin> call = serviceInterface.loginUser(user);

        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                UserLogin user = response.body();

                Toast.makeText(Login.this, user.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {

                Toast.makeText(Login.this, "something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
