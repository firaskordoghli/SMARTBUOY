package com.example.smartbuoy.UI.SignUp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

    private Button signUpbtn;
    // User Session Manager Class
    private UserSessionManager session;
    private TextView surfingTextView,swimmingTextView,divingTextView,fishhingTextView,kayakingTextView;
    private ProgressDialog pDialog;

    private Boolean surfing,swimming,diving,fishing,kayaking;
    private List<String> interestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        signUpbtn = findViewById(R.id.btnSignUp);
        surfingTextView = findViewById(R.id.surfingtv);
        divingTextView = findViewById(R.id.divingtv);
        fishhingTextView = findViewById(R.id.fishingTv);
        kayakingTextView = findViewById(R.id.Kayakingtv);

        surfing = false;
        swimming = false;
        diving = false;
        fishing = false;
        kayaking = false;

        // User Session Manager
        session = new UserSessionManager(getApplicationContext());

        kayakingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!kayaking){
                    kayakingTextView.setBackgroundResource(R.drawable.rounded_corner);
                    kayakingTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    kayaking = true;
                }else if (kayaking){
                    kayakingTextView.setBackgroundResource(R.drawable.rounded_corner_white);
                    kayakingTextView.setTextColor(Color.parseColor("#686868"));
                    kayaking = false;
                }
            }
        });

        surfingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!surfing){
                    surfingTextView.setBackgroundResource(R.drawable.rounded_corner);
                    surfingTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    surfing = true;
                }else if (surfing){
                    surfingTextView.setBackgroundResource(R.drawable.rounded_corner_white);
                    surfingTextView.setTextColor(Color.parseColor("#686868"));
                    surfing = false;
                }
            }
        });

        divingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!diving){
                    divingTextView.setBackgroundResource(R.drawable.rounded_corner);
                    divingTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    diving = true;
                }else if (diving){
                    divingTextView.setBackgroundResource(R.drawable.rounded_corner_white);
                    divingTextView.setTextColor(Color.parseColor("#686868"));
                    diving = false;
                }
            }
        });

        fishhingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fishing){
                    fishhingTextView.setBackgroundResource(R.drawable.rounded_corner);
                    fishhingTextView.setTextColor(Color.parseColor("#FFFFFF"));
                    fishing = true;
                }else if (fishing){
                    fishhingTextView.setBackgroundResource(R.drawable.rounded_corner_white);
                    fishhingTextView.setTextColor(Color.parseColor("#686868"));
                    fishing = false;
                }
            }
        });



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

        if (fishing){ interestList.add("Fishing"); }
        if (diving){ interestList.add("Diving"); }
        if (surfing){ interestList.add("Surfing"); }
        if (kayaking){ interestList.add("Kayaking"); }


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
