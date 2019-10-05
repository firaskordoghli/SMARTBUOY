package com.example.smartbuoy.UI.Menu.Event;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.LoginActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartbuoy.DATA.Constants.ERROR_DIALOG_REQUEST;
import static com.example.smartbuoy.DATA.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.example.smartbuoy.DATA.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class DetailEventActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "DetailEventActivity";
    private String idEventFromUpcoming = "null";
    private ImageView eventImage;
    private ImageView eventSimilaireImage;
    private TextView eventTitleTextView, eventTypeTextView, eventDateTextView, eventLocationTextView, eventDescriptiontextView, eventNumberTextView;
    private TextView eventTitleSimilaireTextView, eventDateSimilaireTextView, eventLocationSimilaireTextView;
    private Button joinEventbtn,deleteEventbtn;
    private Plage newPlage;
    private UserSessionManager session;

    private GoogleMap mGoogleMap;
    private boolean mLocationPermissionGranted = false;
    private GoogleApiClient mGoogleApiClient;
    private MapFragment mapFragment;
    private LatLng plageLatLng;

    private User currentUser;

    private ProgressDialog pDialog;

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
        deleteEventbtn = findViewById(R.id.btnDeleteEvent);

        eventTitleSimilaireTextView = findViewById(R.id.evenementTitleSimilaire);
        eventDateSimilaireTextView = findViewById(R.id.evenementDateSimilaire);
        eventLocationSimilaireTextView = findViewById(R.id.evenementLocationSimilaire);
        eventSimilaireImage = findViewById(R.id.evenementSimilaireImage);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapEvent);
        mapFragment.getMapAsync(this);


        session = new UserSessionManager(DetailEventActivity.this);
        Gson gson = new Gson();
        currentUser = gson.fromJson(session.getUserDetails(), User.class);


        System.out.println();


        joinEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinEvent(idEventFromUpcoming, currentUser.getId());
            }
        });
        
        deleteEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailEventActivity.this, "deletre", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getEventById(String id, String idUser) {
        displayLoader();

        ApiUtil.getServiceClass().getEvent(id, idUser).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Event newEvent = response.body();
                Picasso.get().load(newEvent.getImage()).into(eventImage);
                eventTitleTextView.setText(newEvent.getTitre());
                eventTypeTextView.setText(newEvent.getType());
                eventDateTextView.setText(newEvent.getDate().substring(0, 10));

                if (newEvent.getType().equals("sport")){
                        eventTypeTextView.setTextColor(Color.parseColor("#F4AD1C"));
                        eventTypeTextView.setBackgroundResource(R.drawable.rounded_corner_event_type_yello);
                }else if (newEvent.getType().equals("cleaning")){
                    eventTypeTextView.setTextColor(Color.parseColor("#2262F8"));
                    eventTypeTextView.setBackgroundResource(R.drawable.rounded_corner_event_type_blue);
                }

                if (newEvent.getJoined()){
                    joinEventbtn.setText("annuler");
                }else {
                    joinEventbtn.setText("join");
                }

                if (newEvent.getUser().equals(currentUser.getId())){
                    joinEventbtn.setVisibility(View.GONE);
                    deleteEventbtn.setVisibility(View.VISIBLE);
                }else {
                    joinEventbtn.setVisibility(View.VISIBLE);
                    deleteEventbtn.setVisibility(View.GONE);
                }

                //eventLocationTextView.setText(newEvent.getPlage());

                eventDescriptiontextView.setText(newEvent.getDesc());
                eventNumberTextView.setText(newEvent.getParticipants().size() + " people are going");

                Picasso.get().load(newEvent.getSimEvent().getImage()).into(eventSimilaireImage);
                eventDateSimilaireTextView.setText(newEvent.getSimEvent().getDate().substring(0, 10));
                eventTitleSimilaireTextView.setText(newEvent.getSimEvent().getTitre());
                eventLocationSimilaireTextView.setText(newEvent.getSimEvent().getPlage());

                session = new UserSessionManager(DetailEventActivity.this);
                Gson gson = new Gson();
                final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

                getPlageById(newEvent.getPlage(), currentUser.getId());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                    }
                }, 1000);

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
                final Plage responsePlage = response.body();
                eventLocationTextView.setText(responsePlage.getNom());
                plageLatLng = new LatLng( responsePlage.getLat(),responsePlage.getLng());
                mGoogleMap.addMarker(new MarkerOptions().position(plageLatLng));
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plageLatLng, 8.0f));

                mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        Uri gmmIntentUri = Uri.parse("geo:" + responsePlage.getLat() + "," + responsePlage.getLng() + "?q=" + responsePlage.getLat() + "," + responsePlage.getLng());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        }
                    }
                });
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

        ApiUtil.getServiceClass().joinEvent(object).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.body().equals("participe")){
                    joinEventbtn.setText("annuler");
                    Toast.makeText(DetailEventActivity.this, "joined", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body());
                }else if (response.body().equals("annuler")){
                    joinEventbtn.setText("join");
                    Toast.makeText(DetailEventActivity.this, "annuer", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body());
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DetailEventActivity.this, "error", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());

            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(DetailEventActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    private boolean checkMapServices() {
        if (isServicesOK()) {
            return isMapsEnabled();
        }
        return false;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent enableGpsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(enableGpsIntent, PERMISSIONS_REQUEST_ENABLE_GPS);
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(DetailEventActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            Toast.makeText(this, "do work", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DetailEventActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(DetailEventActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: called.");
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ENABLE_GPS: {
                if (mLocationPermissionGranted) {
                    Toast.makeText(this, "do work", Toast.LENGTH_SHORT).show();
                } else {
                    getLocationPermission();
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkMapServices()) {
            if (mLocationPermissionGranted) {
                Toast.makeText(this, "do work", Toast.LENGTH_SHORT).show();
            } else {
                getLocationPermission();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        mGoogleMap.getUiSettings().setAllGesturesEnabled(false);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
        getEventById(idEventFromUpcoming, currentUser.getId());

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
