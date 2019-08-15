package com.example.smartbuoy.UI.Menu;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Adapters.HomePlageAdapter;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.DetailPlageActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HomePlageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.rvHome);
        mRecyclerView.setHasFixedSize(true);

        listePlage();

        return view;
    }

    void listePlage() {
        ApiUtil.getServiceClass().allplage().enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();

                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new HomePlageAdapter(mlist);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new HomePlageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getContext(), mlist.get(position).getId(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), DetailPlageActivity.class);
                        intent.putExtra("idPlageFromHome", mlist.get(position).getId());
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<ItemHomePlage>> call, Throwable t) {

            }
        });

    }

}
