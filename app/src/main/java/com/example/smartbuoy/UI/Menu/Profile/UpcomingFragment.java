package com.example.smartbuoy.UI.Menu.Profile;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Adapters.PlanProfileAdapter;
import com.example.smartbuoy.DATA.Models.Plan;
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
public class UpcomingFragment extends Fragment {
    private RecyclerView mRecycleView;
    private PlanProfileAdapter planProfileAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        mRecycleView = view.findViewById(R.id.rvUpcoming);
        mRecycleView.setHasFixedSize(true);

        UserSessionManager session = new UserSessionManager(getContext());
        Gson gson = new Gson();
        User currentUser = gson.fromJson(session.getUserDetails(), User.class);
        
        listPlan(currentUser.getId());
        
        return view;
    }

    private void listPlan(String id) {
        ApiUtil.getServiceClass().getPlan(id).enqueue(new Callback<List<Plan>>() {
            @Override
            public void onResponse(Call<List<Plan>> call, Response<List<Plan>> response) {
                List<Plan> mList = response.body();

                mLayoutManager = new LinearLayoutManager(getContext());
                planProfileAdapter = new PlanProfileAdapter(mList);

                mRecycleView.setLayoutManager(mLayoutManager);
                mRecycleView.setAdapter(planProfileAdapter);
            }

            @Override
            public void onFailure(Call<List<Plan>> call, Throwable t) {

            }
        });
    }

}
