package com.example.smartbuoy.UI.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.R;

public class SignUpStep2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Button step2btn;
    Spinner typeSpinner;
    EditText ageEditText,locationEditText,userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);

        Intent intent = getIntent();
        final User newUser = (User)intent.getSerializableExtra("UserStep1");

        step2btn = findViewById(R.id.btnStep2);
        typeSpinner = findViewById(R.id.typeSpinner);
        ageEditText = findViewById(R.id.etAge);
        locationEditText = findViewById(R.id.etLocation);
        userNameEditText = findViewById(R.id.etUserName);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(this);
        
        step2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()){
                    newUser.setAge(Integer.parseInt(ageEditText.getText().toString()));
                    newUser.setLat(locationEditText.getText().toString());
                    newUser.setLng(locationEditText.getText().toString());
                    newUser.setType(typeSpinner.getSelectedItem().toString());
                    newUser.setUsername(userNameEditText.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), SignUpStep3Activity.class);
                    intent.putExtra("UserStep2", newUser);
                    startActivity(intent);
                }

            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validateInputs() {
        if (ageEditText.getText().toString().equals("")) {
            ageEditText.setError("required");
            ageEditText.requestFocus();
            return false;
        }
        if (locationEditText.getText().toString().equals("")) {
            locationEditText.setError("required");
            locationEditText.requestFocus();
            return false;
        }

        return true;
    }
}
