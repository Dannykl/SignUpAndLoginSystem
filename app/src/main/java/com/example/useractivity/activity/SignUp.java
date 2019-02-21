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
import com.example.useractivity.network.RetrofitInstance;
import com.example.useractivity.network.SendLoginDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

      private EditText firstName,lastName,emailAddress,password;
      private Button signUp;
      private TextView logIn;
      SendLoginDataService service;
      private final String emailAlreadyIn = "is already existed";
      private final String unknownError ="Unknown error occurred in registration!";

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sign_up);
            resetting();

            signUp.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String f_name = firstName.getText().toString().trim();
                        String l_name = lastName.getText().toString().trim();
                        String email = emailAddress.getText().toString().trim();
                        String pass = password.getText().toString().trim();

                        if(f_name.isEmpty()|| l_name.isEmpty() || email.isEmpty()|| pass.isEmpty())
                        {
                              Toast.makeText(SignUp.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                              postData(f_name,l_name,email,pass);
                        }
                  }
            });
            logIn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                  }
            });
      }

      void postData(String f_name, String l_name, final String email, String pass)
      {
            service = RetrofitInstance.getRetrofitInstance().create(SendLoginDataService.class);
            Call<Credential> call = service.sendSignUpAData(f_name,l_name,email,pass);
            call.enqueue(new Callback<Credential>()
            {
                  @Override
                  public void onResponse(Call<Credential> call, Response<Credential> response) {
                        Log.d("onResponse", "" + response.body().getErrorMessage());
                        String temp = email + " "+emailAlreadyIn;
                        if (response.isSuccessful()) {
                              Credential credential = response.body();
                              if((credential.getErrorResult()) && (credential.getErrorMessage().equals(temp))) {
                                    existingEmail();
                              }
                              else if((credential.getErrorResult()) && (credential.getErrorMessage().equals(unknownError)))
                              {
                                    unknownError();
                              }
                              else {
                                    signedUp();
                              }

//
                        } else { Toast.makeText(SignUp.this, "Unable to connect with Server", Toast.LENGTH_SHORT).show(); }
                  }
                  @Override
                  public void onFailure(Call<Credential> call, Throwable t) {

                  }
            });
      }

      private void existingEmail() {
            Toast.makeText(SignUp.this, "Email is already registered", Toast.LENGTH_SHORT).show();
      }

      private void unknownError() {
            Toast.makeText(SignUp.this, "Unknown error", Toast.LENGTH_SHORT).show();
      }

      private void signedUp() {
            Toast.makeText(SignUp.this, "Successfully signed up ", Toast.LENGTH_SHORT).show();
      }

      void resetting()
      {

            firstName = findViewById(R.id.f_name_signup);
            lastName = findViewById(R.id.l_name_signup);
            emailAddress = findViewById(R.id.email_signup);
            password = findViewById(R.id.pass_signup);
            signUp = findViewById(R.id.submit_signup);
            logIn = findViewById(R.id.log_in_signup);
      }
}
