package com.example.smartbuoy.UI;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private ImageView detailPlageIv, favorisImageView;
    private TextView detailPlageNomtv, detailPlageVilletv;
    private UserSessionManager session;
    private Button date1Button,date2Button,date3Button;

    private String idPlageFromHome = "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plage);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            idPlageFromHome = extras.getString("idPlageFromHome");
        }

        detailPlageNomtv = findViewById(R.id.tvNomPlageDetail);
        detailPlageIv = findViewById(R.id.imagePlageDetail);
        detailPlageVilletv = findViewById(R.id.tvVillePlageDetail);

        date1Button = findViewById(R.id.date1btn);
        date2Button = findViewById(R.id.date2btn);
        date3Button = findViewById(R.id.date3btn);

        favorisImageView = findViewById(R.id.ivFavoris);


        session = new UserSessionManager(getApplicationContext());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        favorisImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followPlage(currentUser.getId(), idPlageFromHome);
            }
        });

        getPlageById(idPlageFromHome, currentUser.getId());

        date1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date1Button.setBackgroundResource(R.drawable.edit_text_bottom_border);
                date1Button.setTextColor(Color.parseColor("#000000"));
            }
        });

    }

    void getPlageById(String id, String idUser) {
        ApiUtil.getServiceClass().getPlageById(id, idUser).enqueue(new Callback<Plage>() {
            @Override
            public void onResponse(Call<Plage> call, Response<Plage> response) {

                Plage responsePlage = response.body();
                Picasso.get().load(responsePlage.getMainImage()).into(detailPlageIv);
                detailPlageNomtv.setText(responsePlage.getNom());
                detailPlageVilletv.setText(responsePlage.getVille());
            }

            @Override
            public void onFailure(Call<Plage> call, Throwable t) {

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
}
