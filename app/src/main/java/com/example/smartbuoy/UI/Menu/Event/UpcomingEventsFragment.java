package com.example.smartbuoy.UI.Menu.Event;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.smartbuoy.DATA.Adapters.UpComingEventsAdapter;
import com.example.smartbuoy.DATA.Models.Event;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingEventsFragment extends Fragment {

    private RecyclerView mRecycleView;
    private UpComingEventsAdapter eventAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public UpcomingEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
        
        mRecycleView = view.findViewById(R.id.rvUpcomingEvents);
        mRecycleView.setHasFixedSize(true);
        
        listEvent();
        return view;
    }

    private void listEvent() {
        ApiUtil.getServiceClass().allEvents().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                final List<Event> listEvent = response.body();

                mLayoutManager = new LinearLayoutManager(getContext());
                eventAdapter = new UpComingEventsAdapter(listEvent);

                mRecycleView.setLayoutManager(mLayoutManager);
                mRecycleView.setAdapter(eventAdapter);

                eventAdapter.setOnItemClickListener(new UpComingEventsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //Toast.makeText(getContext(), listEvent.get(position).getId(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), DetailEventActivity.class);
                        intent.putExtra("idEventFromUpcoming", listEvent.get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }

}
