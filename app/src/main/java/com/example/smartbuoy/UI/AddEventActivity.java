package com.example.smartbuoy.UI;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.PictureUploader;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.MainActivity;
import com.example.smartbuoy.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private UserSessionManager session;
    private ProgressDialog pDialog;
    private ImageView eventImageView;
    private TextView eventNameTextView, eventDescriptionTextView, eventDate;
    private AutoCompleteTextView eventPlage;
    private Button addEventBtn;
    private Spinner eventType;
    private Event newEvent;
    private ArrayList<String> plageNameList = new ArrayList<String>();
    private Map plageNameMap = new HashMap();
    private String namePlageFromAutoComplete;

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
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plageNameList);
        eventPlage.setAdapter(adapter);
        eventPlage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                namePlageFromAutoComplete = (String) adapterView.getItemAtPosition(i);
            }
        });

        listePlage();

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
                displayLoader();
                newEvent = new Event(
                        eventNameTextView.getText().toString()
                        , eventDescriptionTextView.getText().toString()
                        , eventDate.getText().toString()
                        , eventType.getSelectedItem().toString()
                        , "https://www.teskerti.tn/wp-content/uploads/2019/08/IMG_0368-1024x576.jpg"
                        , plageNameMap.get(namePlageFromAutoComplete).toString()
                        , currentUser.getId());
                addEvent(newEvent);
                System.out.println("new user " + newEvent);
            }
        });

    }

    public void addEvent(final Event event) {
        ApiUtil.getServiceClass().addEvent(event).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                    }
                }, 1000);
                Toast.makeText(AddEventActivity.this, "success" + event, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddEventActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(AddEventActivity.this, "nope", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void listePlage() {
        ApiUtil.getServiceClass().allplage().enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();
                for (int i = 0; i < mlist.size(); i++) {
                    plageNameList.add(mlist.get(i).getName());
                    plageNameMap.put(mlist.get(i).getName(), mlist.get(i).getId());
                    System.out.println(plageNameMap.get(plageNameList.get(i)));
                }
            }

            @Override
            public void onFailure(Call<List<ItemHomePlage>> call, Throwable t) {

            }
        });

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(AddEventActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}
