package com.example.smartbuoy.UI.Menu.Event;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {

    String idEventFromUpcoming = "null";
    private ImageView eventImage;
    private ImageView eventSimilaireImage;
    private TextView eventTitleTextView, eventTypeTextView, eventDateTextView, eventLocationTextView, eventDescriptiontextView, eventNumberTextView;
    private TextView eventTitleSimilaireTextView, eventDateSimilaireTextView, eventLocationSimilaireTextView;
    private Button joinEventbtn;
    private Plage newPlage;
    private UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        Bundle extras = getIntent().getExtras();

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

        session = new UserSessionManager(getApplicationContext());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        getEventById(idEventFromUpcoming, currentUser.getId());

        System.out.println();

        joinEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                joinEvent(idEventFromUpcoming, currentUser.getId());
            }
        });


    }

    /*
        public void getEventById(String id,String idUser) {
            ApiUtil.getServiceClass().getEvent(id,idUser).enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    Event newEvent = response.body();
                    Toast.makeText(DetailEventActivity.this, newEvent.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println("new event"+newEvent);
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    Toast.makeText(DetailEventActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("error"+t.getMessage());
                }
            });
        }
     */
    public void getEventById(String id, String idUser) {
        ApiUtil.getServiceClass().getEvent(id, idUser).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Event newEvent = response.body();
                Picasso.get().load(newEvent.getImage()).into(eventImage);
                eventTitleTextView.setText(newEvent.getTitre());
                eventTypeTextView.setText(newEvent.getType());
                eventDateTextView.setText(newEvent.getDate().substring(0, 10));

                //eventLocationTextView.setText(newEvent.getPlage());

                eventDescriptiontextView.setText(newEvent.getDesc());
                eventNumberTextView.setText(newEvent.getParticipants().size() + " people are going");

                Picasso.get().load(newEvent.getSimEvent().getImage()).into(eventSimilaireImage);
                eventDateSimilaireTextView.setText(newEvent.getSimEvent().getDate().substring(0, 10));
                eventTitleSimilaireTextView.setText(newEvent.getSimEvent().getTitre());
                eventLocationSimilaireTextView.setText(newEvent.getSimEvent().getPlage());

                session = new UserSessionManager(getApplicationContext());
                Gson gson = new Gson();
                final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

                getPlageById(newEvent.getPlage(), currentUser.getId());

            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });
    }

    public void getPlageById(String id, String idUser) {
        ApiUtil.getServiceClass().getPlageById(id, idUser).enqueue(new Callback<Plage>() {
            @Override
            public void onResponse(Call<Plage> call, Response<Plage> response) {
                Plage responsePlage = response.body();
                eventLocationTextView.setText(responsePlage.getNom());
            }

            @Override
            public void onFailure(Call<Plage> call, Throwable t) {

            }
        });
    }

    public void joinEvent(String idEvent, String idUser) {
        JsonObject object = new JsonObject();
        object.addProperty("user", idUser);
        object.addProperty("event", idEvent);

        ApiUtil.getServiceClass().joinEvent(object).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Toast.makeText(DetailEventActivity.this, "joined", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                Toast.makeText(DetailEventActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
