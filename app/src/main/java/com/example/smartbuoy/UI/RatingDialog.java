package com.example.smartbuoy.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingDialog extends AppCompatDialogFragment {
    private RatingBar ratingBar;
    private TextView rateBtn;
    private String plageId;
    private UserSessionManager session;

    public static RatingDialog newInstance(String idPlage) {
        RatingDialog dialogue = new RatingDialog();
        dialogue.plageId = idPlage;
        return dialogue;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setRetainInstance(true);
        final AlertDialog.Builder builer = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_rate, null);

        builer.setView(view);

        ratingBar = view.findViewById(R.id.ratingBarDialog);
        rateBtn = view.findViewById(R.id.rateBtnText);

        session = new UserSessionManager(getActivity());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float rate = ratingBar.getRating();
                ratePlage(currentUser.getId(),plageId,rate);
                dismiss();
            }
        });

        return builer.create();
    }

    private void ratePlage(String idUser, String idPlage, Float rate) {
        JsonObject object = new JsonObject();
        object.addProperty("idPlage", idPlage);
        object.addProperty("idUser", idUser);
        object.addProperty("rate", rate);

        ApiUtil.getServiceClass().ratePlage(object).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}
