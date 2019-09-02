package com.example.smartbuoy.UI.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.MainActivity;
import com.example.smartbuoy.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpStep3Activity extends AppCompatActivity {

    Button signUpbtn;
    // User Session Manager Class
    UserSessionManager session;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        signUpbtn = findViewById(R.id.btnSignUp);

        // User Session Manager
        session = new UserSessionManager(getApplicationContext());

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp() {
        displayLoader();

        Intent intent = getIntent();
        final User newUser = (User) intent.getSerializableExtra("UserStep1");

        List<String> interestList = new ArrayList<String>();
        interestList.add("Fishing");
        interestList.add("Surfing");

        newUser.setInterest(interestList);

        Gson gson = new Gson();
        // Creating user login session
        String userString = gson.toJson(newUser);
        session.createUserLoginSession(userString);

        ApiUtil.getServiceClass().createUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        pDialog.dismiss();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        //finish();
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpStep3Activity.this, "error register", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(SignUpStep3Activity.this);
        pDialog.setMessage("Creating account...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}
