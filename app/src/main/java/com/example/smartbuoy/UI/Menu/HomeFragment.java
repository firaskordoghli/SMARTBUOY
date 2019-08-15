package com.example.smartbuoy.UI.Menu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Adapters.HomePlageAdapter;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<ItemHomePlage> homePlageListe = new ArrayList<>();
        homePlageListe.add(new ItemHomePlage("5d5565be9292201e3c60beaf","klibia beach","klibia","https://www.66kif.tn/wp-content/uploads/2017/10/cover_Snapseed.jpg","5"));
        homePlageListe.add(new ItemHomePlage("5d5565be9292201e3c60beaf","klibia beach","klibia","https://www.66kif.tn/wp-content/uploads/2017/10/cover_Snapseed.jpg","5"));
        homePlageListe.add(new ItemHomePlage("5d5565be9292201e3c60beaf","klibia beach","klibia","https://www.66kif.tn/wp-content/uploads/2017/10/cover_Snapseed.jpg","5"));
        homePlageListe.add(new ItemHomePlage("5d5565be9292201e3c60beaf","klibia beach","klibia","https://www.66kif.tn/wp-content/uploads/2017/10/cover_Snapseed.jpg","5"));
        homePlageListe.add(new ItemHomePlage("5d5565be9292201e3c60beaf","klibia beach","klibia","https://www.66kif.tn/wp-content/uploads/2017/10/cover_Snapseed.jpg","5"));

        mRecyclerView = view.findViewById(R.id.rvHome);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new HomePlageAdapter(homePlageListe);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    void listePlage(){

    }

}
