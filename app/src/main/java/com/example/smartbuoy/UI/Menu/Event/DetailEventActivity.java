package com.example.smartbuoy.UI.Menu.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {

    private ImageView eventImage;
    private TextView eventTitleTextView,eventTypeTextView,eventDateTextView,eventLocationTextView,eventDescriptiontextView,eventNumberTextView;
    private Button joinEventbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        Bundle extras = getIntent().getExtras();
        String idEventFromUpcoming = "null";
        if (extras != null) {
            idEventFromUpcoming = extras.getString("idEventFromUpcoming");
        }

        eventImage = findViewById(R.id.ivDetailEvent);
        eventTitleTextView = findViewById(R.id.tvDetailEventTitle);
        eventTypeTextView = findViewById(R.id.tvDetailEventType);
        eventDateTextView = findViewById(R.id.tvDetailEventDate);
        eventLocationTextView = findViewById(R.id.tvEventDetailLocation);
        eventDescriptiontextView = findViewById(R.id.etDetailEventDescription);
        eventNumberTextView = findViewById(R.id.tvDetailEventNumber);
        joinEventbtn = findViewById(R.id.btnJoinEvent);

        getEventById(idEventFromUpcoming);


    }

    public void getEventById(String id ){
        ApiUtil.getServiceClass().getEvent(id).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Event newEvent = response.body();
                //Toast.makeText(DetailEventActivity.this, newEvent.toString(), Toast.LENGTH_SHORT).show();

                Picasso.get().load(newEvent.getImage()).into(eventImage);
                eventTitleTextView.setText(newEvent.getTitre());
                eventTypeTextView.setText(newEvent.getType());
                eventDateTextView.setText(newEvent.getDate());
                eventLocationTextView.setText(newEvent.getPlage());
                eventDescriptiontextView.setText(newEvent.getDesc());
                eventNumberTextView.setText(newEvent.getParticipants().size() + " people are going");

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
    }
}
