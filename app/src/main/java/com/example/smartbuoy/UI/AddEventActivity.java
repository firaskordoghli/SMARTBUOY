package com.example.smartbuoy.UI;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    UserSessionManager session;

    private ImageView eventImageView;
    private TextView eventNameTextView, eventDescriptionTextView, eventPlage, eventDate;
    private Button addEventBtn;
    private Spinner eventType;
    private Event newEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        eventImageView = findViewById(R.id.ivAddEvent);
        eventNameTextView = findViewById(R.id.etAddEventName);
        eventDescriptionTextView = findViewById(R.id.etAddEventDescription);
        eventPlage = findViewById(R.id.etAddEventBeachName);
        eventDate = findViewById(R.id.etAddEventDate);

        addEventBtn = findViewById(R.id.btnAddEvent);
        eventType = findViewById(R.id.spinnerEventType);

        session = new UserSessionManager(getApplicationContext());
        Gson gson = new Gson();
        User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        eventDate.setText(mDay + "." + (mMonth + 1) + "." + mYear);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newEvent = new Event(
                        eventNameTextView.getText().toString()
                        , eventDescriptionTextView.getText().toString()
                        , eventDate.getText().toString()
                        , eventType.getSelectedItem().toString()
                        , "https://www.teskerti.tn/wp-content/uploads/2019/08/IMG_0368-1024x576.jpg"
                        , "5d55b1533a2d0c236c12290b"
                        , "5d5a988e907f7824384470bb");

                addEvent(newEvent);
            }
        });
    }

    public void addEvent(final Event event) {
        ApiUtil.getServiceClass().addEvent(event).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Toast.makeText(AddEventActivity.this, "success" + event, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(AddEventActivity.this, "nope", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
