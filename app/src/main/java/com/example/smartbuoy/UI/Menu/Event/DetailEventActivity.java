package com.example.smartbuoy.UI.Menu.Event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {

    private ImageView eventImage;
    private ImageView eventSimilaireImage;
    private TextView eventTitleTextView,eventTypeTextView,eventDateTextView,eventLocationTextView,eventDescriptiontextView,eventNumberTextView;
    private TextView eventTitleSimilaireTextView,eventDateSimilaireTextView,eventLocationSimilaireTextView;
    private Button joinEventbtn;
    private Plage newPlage ;
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

        eventTitleSimilaireTextView = findViewById(R.id.evenementTitleSimilaire);
        eventDateSimilaireTextView = findViewById(R.id.evenementDateSimilaire);
        eventLocationSimilaireTextView = findViewById(R.id.evenementLocationSimilaire);
        eventSimilaireImage = findViewById(R.id.evenementSimilaireImage);

        getEventById(idEventFromUpcoming);

        joinEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailEventActivity.this, "join event", Toast.LENGTH_SHORT).show();
            }
        });


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

                Picasso.get().load(newEvent.getSimEvent().getImage()).into(eventSimilaireImage);
                eventDateSimilaireTextView.setText(newEvent.getSimEvent().getDate());
                eventTitleSimilaireTextView.setText(newEvent.getSimEvent().getTitre());
                eventLocationSimilaireTextView.setText(newEvent.getSimEvent().getPlage());

                Toast.makeText(DetailEventActivity.this, newEvent.getSimEvent().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
    }

    public Plage getPlageById(String id) {


        ApiUtil.getServiceClass().getPlageById(id).enqueue(new Callback<Plage>() {
            @Override
            public void onResponse(Call<Plage> call, Response<Plage> response) {
                newPlage = response.body();


            }

            @Override
            public void onFailure(Call<Plage> call, Throwable t) {

            }
        });
        return newPlage;
    }
}
