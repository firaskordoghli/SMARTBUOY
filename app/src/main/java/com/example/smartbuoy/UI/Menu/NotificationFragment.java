package com.example.smartbuoy.UI.Menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Adapters.NotificationAdapter;
import com.example.smartbuoy.DATA.Models.Notification;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    private Button logoutBtn;
    private UserSessionManager session;

    private RecyclerView mRecycleView;
    private NotificationAdapter notificationAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        session = new UserSessionManager(getContext());

        ArrayList<Notification> exampleList = new ArrayList<>();

        exampleList.add(new Notification( "Great swim day in Maamoura today"));
        exampleList.add(new Notification( "Great swim day in Sidi Mansour today"));
        exampleList.add(new Notification( "Jamila Mesgheni has joined your event Cleaning the beach"));
        exampleList.add(new Notification( "Great swim day in El Fatha today"));
        exampleList.add(new Notification( "Sami Benghazi has joined your event Cleaning the beach"));
        exampleList.add(new Notification( "Ahmed Mtir has joined your event fishing jmi3"));
        exampleList.add(new Notification( "Great swim day in Maamoura today"));

        mRecycleView = view.findViewById(R.id.rvNotification);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        notificationAdapter = new NotificationAdapter(exampleList);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(notificationAdapter);


        logoutBtn = view.findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
                getActivity().finish();
            }
        });

        return view;
    }

}
