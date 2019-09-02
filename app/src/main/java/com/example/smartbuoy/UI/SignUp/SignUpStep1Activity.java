package com.example.smartbuoy.UI.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpStep1Activity extends AppCompatActivity {

    Button step1btn;
    EditText emailEditText, passwordEditText, verifPasswordEditText,userNameEditText;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step1);

        step1btn = findViewById(R.id.btnStep1);

        emailEditText = findViewById(R.id.etSignUpEmail);
        passwordEditText = findViewById(R.id.etSignUpPassword);
        verifPasswordEditText = findViewById(R.id.etSignUpPasswordVerif);
        userNameEditText = findViewById(R.id.etUserName);


        step1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayLoader();
                ApiUtil.getServiceClass().verifyMmail(emailEditText.getText().toString()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Gson gson = new Gson();
                        //System.out.println("$$$$$$$$$$$$$$$$$$"+gson.toJson(response.body()));

                        JsonObject myJsonResponse = new JsonObject();
                        myJsonResponse.getAsJsonObject(gson.toJson(response.body()));

                        //System.out.println(""+response.body().get("exist"));

                        boolean existe = gson.toJson(response.body()).contains("true");

                        if (existe) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    pDialog.dismiss();
                                    emailEditText.setError("email already exists");
                                    emailEditText.requestFocus();
                                }
                            }, 1000);

                        } else {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    pDialog.dismiss();
                                    validateInputs();
                                    if (validateInputs()) {
                                        User newUser = new User();

                                        newUser.setEmail(emailEditText.getText().toString());
                                        newUser.setPassword(passwordEditText.getText().toString());
                                        newUser.setUsername(userNameEditText.getText().toString());

                                        Intent intent = new Intent(getApplicationContext(), SignUpStep3Activity.class);
                                        intent.putExtra("UserStep1", newUser);
                                        startActivity(intent);
                                    }
                                }
                            }, 1000);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(SignUpStep1Activity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean validateInputs() {
        if (emailEditText.getText().toString().equals("")) {
            emailEditText.setError("required");
            emailEditText.requestFocus();
            return false;
        }
        if (passwordEditText.getText().toString().equals("")) {
            passwordEditText.setError("required");
            passwordEditText.requestFocus();
            return false;
        }

        if (verifPasswordEditText.getText().toString().equals("")) {
            verifPasswordEditText.setError("required");
            verifPasswordEditText.requestFocus();
            return false;
        }

        if (!verifPasswordEditText.getText().toString().equals(passwordEditText.getText().toString())) {
            verifPasswordEditText.setError("unmatched password");
            verifPasswordEditText.requestFocus();
            return false;
        }

        return true;

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(SignUpStep1Activity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}


