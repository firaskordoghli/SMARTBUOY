package com.example.smartbuoy.UI.Menu.Profile;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Models.Plage;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {
    UserSessionManager session;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);


        session = new UserSessionManager(getContext());
        Gson gson = new Gson();
        final User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        getListFavoris(currentUser.getId());

        return view;
    }

    public void getListFavoris (String idUser){
        ApiUtil.getServiceClass().getListFavoris(idUser).enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();
                System.out.println("$$$$$$$$$$$$"+mlist.size());
                //Toast.makeText(getContext(), mlist.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ItemHomePlage>> call, Throwable t) {

            }
        });
    }

}
