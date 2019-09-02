package com.example.smartbuoy.UI.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.R;

public class SignUpStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);

        final Intent intent = getIntent();
        final User newUser = (User) intent.getSerializableExtra("UserStep1");

        Button step2btn = findViewById(R.id.btnStep2);

        final NumberPicker agePicker = findViewById(R.id.agePicker);

        agePicker.setMaxValue(60);
        agePicker.setMinValue(12);
        agePicker.setWrapSelectorWheel(true);

        agePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //newUser.setAge(i1);
            }
        });

        final String[] userType = {"Ba7ar", "Sayen", "Bazness", "3awem"};
        final NumberPicker typePicker = findViewById(R.id.typePicker);
        typePicker.setMinValue(0);
        typePicker.setMaxValue(userType.length - 1);
        typePicker.setDisplayedValues(userType);
        typePicker.setWrapSelectorWheel(true);

        typePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //newUser.setType(userType[i1]);
                //Toast.makeText(SignUpStep2Activity.this, userType[i1], Toast.LENGTH_SHORT).show();
            }
        });

        step2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //newUser.setAge(agePicker.getValue());
                //newUser.setType(userType[typePicker.getValue()]);

                Intent intent = new Intent(getApplicationContext(), SignUpStep3Activity.class);
                intent.putExtra("UserStep2", newUser);
                startActivity(intent);

            }
        });

    }

}
