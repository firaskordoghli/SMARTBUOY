package com.example.smartbuoy.UI;

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
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.MainActivity;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.SignUp.SignUpStep1Activity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //private static final String TAG = "LoginActivity";

    // User Session Manager Class
    UserSessionManager session;
    private Button toSignUpBtn, loginBtn;
    private EditText emailEditText, passwordEditText;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User Session Manager
        session = new UserSessionManager(getApplicationContext());

        toSignUpBtn = findViewById(R.id.btnToSignUp);
        loginBtn = findViewById(R.id.btnLogin);

        emailEditText = findViewById(R.id.etLoginEmail);
        passwordEditText = findViewById(R.id.etLoginPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    login();
                }

            }
        });

        /*

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = new User("testerjokfi","ahmedjokdH@gaamail.com","dqvs1Zvsv",12,"tunisia22","sdgzrgf", "aaaaaaaaA2");
                ApiUtil.getServiceClass().createUser(u).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        //Log.e(TAG, "onResponse: "+response.code() );
                        //System.out.println("$$$$$$$$"+response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        //System.out.println("$$$$$$$$ shit");

                    }
                });
            }
        });
*/
        toSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpStep1Activity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        displayLoader();
        JsonObject object = new JsonObject();

        object.addProperty("email", emailEditText.getText().toString().trim());
        object.addProperty("password", passwordEditText.getText().toString().trim());

        ApiUtil.getServiceClass().login(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body().has("errors")) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "wrong email or password", Toast.LENGTH_SHORT).show();
                            System.out.println("wrong email or password");
                        }
                    }, 1000);
                } else {
                    Gson gson = new Gson();
                    User responseUser = gson.fromJson(response.body(), User.class);
                    session.createUserLoginSession(gson.toJson(responseUser));
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pDialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 1000);

                    System.out.println(session.getUserDetails());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println(t.getMessage());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "please connect to the internet", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
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

        return true;

    }
}
