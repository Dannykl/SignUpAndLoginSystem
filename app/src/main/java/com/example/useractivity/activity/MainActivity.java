package com.example.useractivity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.useractivity.R;
import com.example.useractivity.model.Credential;
import com.example.useractivity.network.SendLoginDataService;
import com.example.useractivity.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

      private EditText email_login,pass_login;
      private Button submit_login;
      private TextView signup_login,login_reset_pass;
      private final String unfoundEmail= "Email was not found! Try sign-up";
      private final String incorrectPassword= "Login credentials are wrong. Please try again!";
      SendLoginDataService service;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setting();
            submit_login.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String email = email_login.getText().toString().trim();
                        String pass = pass_login.getText().toString().trim();
                        if(email.isEmpty()|| pass.isEmpty())
                        {
                              Toast.makeText(MainActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                              postData(email,pass);
                        }
                  }
            });
            signup_login.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "sign up was pressed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SignUp.class);
                        startActivity(intent);
                  }
            });
            login_reset_pass.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "reset was pressed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ResetPassword.class);
                        startActivity(intent);
                  }
            });
      }

      void setting()
      {
            email_login = findViewById(R.id.email_login);
            pass_login = findViewById(R.id.pass_login);
            submit_login = findViewById(R.id.submit_login);
            signup_login = findViewById(R.id.signup_login);
            login_reset_pass = findViewById(R.id.login_reset_pass);
      }

      void postData(String email, final String pass)
      {
            service = RetrofitInstance.getRetrofitInstance().create(SendLoginDataService.class);
            Call<Credential> call = service.sendLogInAData(email,pass);
            call.enqueue(new Callback<Credential>()
            {
                  @Override
                  public void onResponse(Call<Credential> call, Response<Credential> response) {
                        Log.d("onResponse", "" + response.body().getErrorMessage());
                        if (response.isSuccessful()) {
                              Credential credential = response.body();

                              if((credential.getErrorResult()) && (credential.getErrorMessage().equals(unfoundEmail))) {
                                    unfoundEmail();
                              }
                              else if((credential.getErrorResult()) && (credential.getErrorMessage().equals(incorrectPassword)))
                              {
                                    wrongPassword();
                              }
                              else {
                                    loggedIn();
                              }
//
                        } else { Toast.makeText(MainActivity.this, "Unable to connect with Server", Toast.LENGTH_SHORT).show(); }
                  }
                  @Override
                  public void onFailure(Call<Credential> call, Throwable t) {

                  }
            });
      }
      void wrongPassword()
      {
            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
      }
      void unfoundEmail()
      {
            Toast.makeText(MainActivity.this, "Email is not in DB", Toast.LENGTH_SHORT).show();
      }

      void loggedIn()
      {
            Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
      }

}
