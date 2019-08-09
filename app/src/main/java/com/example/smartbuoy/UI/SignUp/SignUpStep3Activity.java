package com.example.smartbuoy.UI.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpStep3Activity extends AppCompatActivity {

    Button signUpbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);

        signUpbtn = findViewById(R.id.btnSignUp);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp(){
        Intent intent = getIntent();
        final User newUser = (User)intent.getSerializableExtra("UserStep2");

        ApiUtil.getServiceClass().createUser(newUser).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(SignUpStep3Activity.this, "registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpStep3Activity.this, "error register", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
