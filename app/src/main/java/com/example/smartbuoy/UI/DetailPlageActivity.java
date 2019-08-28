package com.example.smartbuoy.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class DetailPlageActivity extends AppCompatActivity {
    ImageView detailPlageIv,favorisImageView;
    TextView detailPlagetv;
    UserSessionManager session;

    String idPlageFromHome = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plage);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            idPlageFromHome = extras.getString("idPlageFromHome");
        }

        detailPlagetv = findViewById(R.id.tvNomPlageDetail);
        detailPlageIv = findViewById(R.id.imagePlageDetail);

        favorisImageView= findViewById(R.id.ivFavoris);



        session = new UserSessionManager(getApplicationContext());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        favorisImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followPlage(currentUser.getId(),idPlageFromHome);
            }
        });

        getPlageById(idPlageFromHome, currentUser.getId());

    }

    void getPlageById(String id, String idUser) {
        ApiUtil.getServiceClass().getPlageById(id, idUser).enqueue(new Callback<Plage>() {


            @Override
            public void onResponse(Call<Plage> call, Response<Plage> response) {

                Plage responsePlage = response.body();
                Picasso.get().load(responsePlage.getMainImage()).into(detailPlageIv);
                detailPlagetv.setText(responsePlage.getNom());
            }

            @Override
            public void onFailure(Call<Plage> call, Throwable t) {

            }
        });
    }

    public void followPlage(String idUser, String idPlage) {
        JsonObject object = new JsonObject();
        object.addProperty("idPlage",idPlage);
        object.addProperty("idUser",idUser);

        ApiUtil.getServiceClass().followPlage(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject responseObject = new JsonObject();
                responseObject = response.body();

                Toast.makeText(DetailPlageActivity.this, responseObject.get("msg").toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DetailPlageActivity.this, "te7che", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
