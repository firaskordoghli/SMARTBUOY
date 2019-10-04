package com.example.smartbuoy.UI;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.smartbuoy.DATA.Models.Detail;
import com.example.smartbuoy.DATA.Models.Meteo;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.Menu.Event.DetailEventActivity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.smartbuoy.DATA.Constants.ERROR_DIALOG_REQUEST;
import static com.example.smartbuoy.DATA.Constants.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;
import static com.example.smartbuoy.DATA.Constants.PERMISSIONS_REQUEST_ENABLE_GPS;

public class DetailPlageActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnInfoWindowClickListener  {
    private static final String TAG = "DetailPlageActivity";
    private ImageView detailPlageIv, favorisImageView;
    private TextView detailPlageNomtv, detailPlageVilletv, detailPlageRatetv, detailPlageDescription;
    private TextView detailPlageFlagtv, detailPlageCroudedtv, detailPlageCloudytv;
    private UserSessionManager session;
    private ImageView parking, shower, resto, wc, bar, beachVolley, chienAdmis, parasol;
    private ImageView flagIv, croudedIv, cloudyIv;
    private List<Meteo> previsionList = null;
    private Meteo meteo;
    private String idPlageFromHome = "null";

    private TextView date1tv,date1tvDate,date2tv,date2tvDate,date3tv,date3tvDate,date4tv,date4tvDate;
    private LinearLayout date1Lin,date2Lin,date3Lin,date4Lin;

    private ProgressDialog pDialog;

    private GoogleMap mGoogleMap;
    private boolean mLocationPermissionGranted = false;
    private GoogleApiClient mGoogleApiClient;
    private MapFragment mapFragment;
    private LatLng plageLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plage);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            idPlageFromHome = extras.getString("idPlageFromHome");
        }

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapPlage);
        mapFragment.getMapAsync(this);

        detailPlageNomtv = findViewById(R.id.tvNomPlageDetail);
        detailPlageIv = findViewById(R.id.imagePlageDetail);
        detailPlageVilletv = findViewById(R.id.tvVillePlageDetail);

        date1Lin = findViewById(R.id.date1lin);
        date2Lin = findViewById(R.id.date2lin);
        date3Lin = findViewById(R.id.date3lin);
        date4Lin = findViewById(R.id.date4lin);

        date1tv = findViewById(R.id.date1tv);
        date1tvDate = findViewById(R.id.date1tvDate);
        date2tv = findViewById(R.id.date2tv);
        date2tvDate = findViewById(R.id.date2tvDate);
        date3tv = findViewById(R.id.date3tv);
        date3tvDate = findViewById(R.id.date3tvDate);
        date4tv = findViewById(R.id.date4tv);
        date4tvDate = findViewById(R.id.date4tvDate);

        detailPlageRatetv = findViewById(R.id.tvRatingPlageDetail);
        detailPlageDescription = findViewById(R.id.tvPlageDescription);

        detailPlageFlagtv = findViewById(R.id.tvFlag);
        detailPlageCroudedtv = findViewById(R.id.tvCrouded);
        detailPlageCloudytv = findViewById(R.id.tvCloudy);

        flagIv = findViewById(R.id.ivFlag);
        croudedIv = findViewById(R.id.ivCloudy);
        cloudyIv = findViewById(R.id.ivCrouded);

        parking = findViewById(R.id.icParking);
        shower = findViewById(R.id.icShower);
        resto = findViewById(R.id.icResto);
        wc = findViewById(R.id.icWc);
        bar = findViewById(R.id.icBear);
        beachVolley = findViewById(R.id.icSport);
        chienAdmis = findViewById(R.id.icDog);
        parasol = findViewById(R.id.icParasol);

        favorisImageView = findViewById(R.id.ivFavoris);

        date1tv.setTextColor(Color.parseColor("#000000"));
        date1tvDate.setTextColor(Color.parseColor("#000000"));
        date1tvDate.setBackgroundResource(R.drawable.edit_text_bottom_border);

        session = new UserSessionManager(getApplicationContext());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        favorisImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followPlage(currentUser.getId(), idPlageFromHome);
            }
        });

        //getPlageById(idPlageFromHome, currentUser.getId());

        date1Lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date1tv.setTextColor(Color.parseColor("#000000"));
                date1tvDate.setTextColor(Color.parseColor("#000000"));
                date1tvDate.setBackgroundResource(R.drawable.edit_text_bottom_border);

                date2tv.setTextColor(Color.parseColor("#CBCBCB"));
                date2tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date3tv.setTextColor(Color.parseColor("#CBCBCB"));
                date3tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date4tv.setTextColor(Color.parseColor("#CBCBCB"));
                date4tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date2tvDate.setBackgroundResource(0);
                date3tvDate.setBackgroundResource(0);
                date4tvDate.setBackgroundResource(0);

                if (previsionList != null)
                    switch (previsionList.get(0).getFlag()) {
                        case 1:
                            detailPlageFlagtv.setText("safe");
                            flagIv.setImageResource(R.drawable.ic_green_flag_icon);
                            break;
                        case 2:
                            detailPlageFlagtv.setText("risquy");
                            flagIv.setImageResource(R.drawable.ic_groupe_351);
                            break;
                        case 3:
                            detailPlageFlagtv.setText("dangerous");
                            flagIv.setImageResource(R.drawable.ic_red_flag_icon_);
                            break;
                    }
                if (previsionList != null)
                    switch (previsionList.get(0).getCrowded()) {
                        case 1:
                            detailPlageCroudedtv.setText("empty");
                            croudedIv.setImageResource(R.drawable.ic_empty_icon);
                            break;
                        case 2:
                            detailPlageCroudedtv.setText("little crouded");
                            croudedIv.setImageResource(R.drawable.ic_groupe_363);
                            break;
                        case 3:
                            detailPlageCroudedtv.setText("crouded");
                            croudedIv.setImageResource(R.drawable.ic_crowded_icon_);
                            break;
                    }
            }
        });

        date2Lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date2tv.setTextColor(Color.parseColor("#000000"));
                date2tvDate.setTextColor(Color.parseColor("#000000"));
                date2tvDate.setBackgroundResource(R.drawable.edit_text_bottom_border);

                date1tv.setTextColor(Color.parseColor("#CBCBCB"));
                date1tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date3tv.setTextColor(Color.parseColor("#CBCBCB"));
                date3tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date4tv.setTextColor(Color.parseColor("#CBCBCB"));
                date4tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date1tvDate.setBackgroundResource(0);
                date3tvDate.setBackgroundResource(0);
                date4tvDate.setBackgroundResource(0);
            }
        });

        date3Lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date3tv.setTextColor(Color.parseColor("#000000"));
                date3tvDate.setTextColor(Color.parseColor("#000000"));
                date3tvDate.setBackgroundResource(R.drawable.edit_text_bottom_border);

                date2tv.setTextColor(Color.parseColor("#CBCBCB"));
                date2tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date1tv.setTextColor(Color.parseColor("#CBCBCB"));
                date1tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date4tv.setTextColor(Color.parseColor("#CBCBCB"));
                date4tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date2tvDate.setBackgroundResource(0);
                date1tvDate.setBackgroundResource(0);
                date4tvDate.setBackgroundResource(0);
            }
        });

        date4Lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date4tv.setTextColor(Color.parseColor("#000000"));
                date4tvDate.setTextColor(Color.parseColor("#000000"));
                date4tvDate.setBackgroundResource(R.drawable.edit_text_bottom_border);

                date2tv.setTextColor(Color.parseColor("#CBCBCB"));
                date2tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date1tv.setTextColor(Color.parseColor("#CBCBCB"));
                date1tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date3tv.setTextColor(Color.parseColor("#CBCBCB"));
                date3tvDate.setTextColor(Color.parseColor("#CBCBCB"));

                date2tvDate.setBackgroundResource(0);
                date1tvDate.setBackgroundResource(0);
                date3tvDate.setBackgroundResource(0);
            }
        });
    }

    void getPlageById(String id, String idUser) {
        displayLoader();
        ApiUtil.getServiceClass().getPlageById(id, idUser).enqueue(new Callback<Plage>() {
            @Override
            public void onResponse(Call<Plage> call, Response<Plage> response) {

                final Plage responsePlage = response.body();
                Picasso.get().load(responsePlage.getMainImage()).into(detailPlageIv);
                detailPlageNomtv.setText(responsePlage.getNom());
                detailPlageVilletv.setText(responsePlage.getVille());
                detailPlageRatetv.setText(String.valueOf(responsePlage.getRate()).substring(0, 3));
                detailPlageDescription.setText(responsePlage.getDesc());
                previsionList = responsePlage.getPrev();
                meteo = responsePlage.getMeteo();

                date1tv.setText("Today");
                date1tvDate.setText(meteo.getDate().substring(5, 10));

                SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date date = inFormat.parse(previsionList.get(0).getDate());
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String goal = outFormat.format(date);
                    date2tv.setText(goal);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date2tvDate.setText(previsionList.get(0).getDate().substring(5, 10));

                try {
                    Date date = inFormat.parse(previsionList.get(1).getDate());
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String goal = outFormat.format(date);
                    date3tv.setText(goal);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date3tvDate.setText(previsionList.get(1).getDate().substring(5, 10));

                try {
                    Date date = inFormat.parse(previsionList.get(2).getDate());
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String goal = outFormat.format(date);
                    date4tv.setText(goal);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                date4tvDate.setText(previsionList.get(2).getDate().substring(5, 10));

                Detail detail = responsePlage.getDetail();

                if (detail.getParking()) {
                    parking.setVisibility(View.VISIBLE);
                }
                if (detail.getShower()) {
                    shower.setVisibility(View.VISIBLE);
                }
                if (detail.getResto()) {
                    resto.setVisibility(View.VISIBLE);
                }
                if (detail.getWc()) {
                    wc.setVisibility(View.VISIBLE);
                }
                if (detail.getBar()) {
                    bar.setVisibility(View.VISIBLE);
                }
                if (detail.getBeachVolley()) {
                    beachVolley.setVisibility(View.VISIBLE);
                }
                if (detail.getChienAdmis()) {
                    chienAdmis.setVisibility(View.VISIBLE);
                }
                if (detail.getParasol()) {
                    parasol.setVisibility(View.VISIBLE);
                }

                Meteo observation = responsePlage.getMeteo();
                if (observation != null)
                    switch (observation.getFlag()) {
                        case 1:
                            detailPlageFlagtv.setText("safe");
                            flagIv.setImageResource(R.drawable.ic_green_flag_icon);
                            break;
                        case 2:
                            detailPlageFlagtv.setText("risquy");
                            flagIv.setImageResource(R.drawable.ic_groupe_351);
                            break;
                        case 3:
                            detailPlageFlagtv.setText("dangerous");
                            flagIv.setImageResource(R.drawable.ic_red_flag_icon_);
                            break;
                    }
                if (observation != null)
                    switch (observation.getCrowded()) {
                        case 1:
                            detailPlageCroudedtv.setText("empty");
                            croudedIv.setImageResource(R.drawable.ic_empty_icon);
                            break;
                        case 2:
                            detailPlageCroudedtv.setText("little crouded");
                            croudedIv.setImageResource(R.drawable.ic_groupe_363);
                            break;
                        case 3:
                            detailPlageCroudedtv.setText("crouded");
                            croudedIv.setImageResource(R.drawable.ic_crowded_icon_);
                            break;
                    }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pDialog.dismiss();
                    }
                }, 1000);

                plageLatLng = new LatLng(responsePlage.getLat(),responsePlage.getLng());
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
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    public void followPlage(String idUser, String idPlage) {

        JsonObject object = new JsonObject();
        object.addProperty("idPlage", idPlage);
        object.addProperty("idUser", idUser);

        ApiUtil.getServiceClass().followPlage(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject responseObject = new JsonObject();
                responseObject = response.body();

                Drawable addFavoris = getResources().getDrawable(R.drawable.ic_favoris_added);
                favorisImageView.setImageDrawable(addFavoris);
                Toast.makeText(DetailPlageActivity.this, responseObject.get("msg").toString(), Toast.LENGTH_SHORT).show();
/*
                if (responseObject.get("msg").toString().equals("added")) {
                    Drawable addFavoris = getResources().getDrawable(R.drawable.ic_favoris);
                    favorisImageView.setImageDrawable(addFavoris);
                    Toast.makeText(DetailPlageActivity.this, responseObject.get("msg").toString(), Toast.LENGTH_SHORT).show();
                } else if (responseObject.get("msg").toString().equals("deleted")) {
                    Drawable addFavoris = getResources().getDrawable(R.drawable.ic_favoris_added);
                    favorisImageView.setImageDrawable(addFavoris);
                    Toast.makeText(DetailPlageActivity.this, responseObject.get("msg").toString(), Toast.LENGTH_SHORT).show();
                }
                */
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DetailPlageActivity.this, "mamchetesh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(DetailPlageActivity.this);
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
        if (ContextCompat.checkSelfPermission(DetailPlageActivity.this,
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

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DetailPlageActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(DetailPlageActivity.this, available, ERROR_DIALOG_REQUEST);
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
        //getEventById(idEventFromUpcoming, currentUser.getId());

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}

