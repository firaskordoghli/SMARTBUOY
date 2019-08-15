package com.example.smartbuoy.UI;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPlageActivity extends AppCompatActivity {
    ImageView detailPlageIv;
    TextView detailPlagetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plage);

        Bundle extras = getIntent().getExtras();
        String idPlageFromHome = "null";
        if (extras != null) {
            idPlageFromHome = extras.getString("idPlageFromHome");
        }

        detailPlagetv = findViewById(R.id.tvNomPlageDetail);
        detailPlageIv = findViewById(R.id.imagePlageDetail);

        getPlageById(idPlageFromHome);

    }

    void getPlageById(String id) {
        ApiUtil.getServiceClass().getPlageById(id).enqueue(new Callback<Plage>() {
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
}
