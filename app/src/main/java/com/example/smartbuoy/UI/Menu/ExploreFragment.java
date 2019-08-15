package com.example.smartbuoy.UI.Menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    Button logoutBtn;
    UserSessionManager session;


    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        session = new UserSessionManager(getContext());

        logoutBtn = view.findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
            }
        });

        return view;
    }

}
