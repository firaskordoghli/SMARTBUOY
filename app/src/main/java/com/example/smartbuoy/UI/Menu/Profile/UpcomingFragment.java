package com.example.smartbuoy.UI.Menu.Profile;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Adapters.PlanProfileAdapter;
import com.example.smartbuoy.DATA.Models.Plan;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.RecyclerItemTouchHelper;
import com.example.smartbuoy.DATA.RecyclerItemTouchHelperListener;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements RecyclerItemTouchHelperListener {
    private RecyclerView mRecycleView;
    private PlanProfileAdapter planProfileAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Plan> list;
    private TextView emptyUpcommingText;


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
        emptyUpcommingText = view.findViewById(R.id.emptyUpcommingTv);

        mLayoutManager = new LinearLayoutManager(getContext());
        planProfileAdapter = new PlanProfileAdapter(getContext(), list);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecycleView.setAdapter(planProfileAdapter);

        list = new ArrayList<>();

        UserSessionManager session = new UserSessionManager(getContext());
        Gson gson = new Gson();
        User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        listPlan(currentUser.getId());

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mRecycleView);
        return view;
    }

    private void listPlan(String id) {
        ApiUtil.getServiceClass().getPlan(id).enqueue(new Callback<List<Plan>>() {
            @Override
            public void onResponse(Call<List<Plan>> call, Response<List<Plan>> response) {
                try {
                    List<Plan> listPlan = response.body();
                    if (listPlan.size() == 0) {
                        emptyUpcommingText.setText("Nothing is scheduled yet, Add Plans in the beach of your choosing");
                    } else {
                        list.clear();
                        list.addAll(listPlan);
                        planProfileAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Plan>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PlanProfileAdapter.PlanViewHolder) {
            String name = list.get(viewHolder.getAdapterPosition()).getNomPlage();
            final Plan deletedPlan = list.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            planProfileAdapter.removeItem(deletedIndex);

            Snackbar snackbar = Snackbar.make(getView(), name + "removed from list", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    planProfileAdapter.restoreItem(deletedPlan, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
