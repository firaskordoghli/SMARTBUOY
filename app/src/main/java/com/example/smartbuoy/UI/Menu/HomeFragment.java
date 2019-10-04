package com.example.smartbuoy.UI.Menu;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbuoy.DATA.Adapters.HomePlageAdapter;
import com.example.smartbuoy.DATA.Models.ItemHomePlage;
import com.example.smartbuoy.DATA.Models.Plan;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.Retrofite.ApiUtil;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.DetailPlageActivity;
import com.example.smartbuoy.UI.MapSearchActivity;
import com.example.smartbuoy.UI.SearshActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private ImageView planImageView;
    private TextView planDateTextView, planDayTextView, planPlageTextView, planCityTextView, welcomeTextView, recommendedTextView, nearYouTextView, fishingTextView, othresTextView, emptyPlanTextView;
    private RecyclerView mRecyclerView;
    private HomePlageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String plageId = "none";
    private CardView cardPlan;

    private LinearLayout dateLinearLayout, iconLinearLayout;

    private UserSessionManager session;

    private ShimmerFrameLayout mShimmerViewContainer;

    private User currentUser;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Session class instance
        session = new UserSessionManager(getContext());
        Gson gson = new Gson();
        currentUser = gson.fromJson(session.getUserDetails(), User.class);

        EditText toSearshEditText = view.findViewById(R.id.etToSearsh);

        cardPlan = view.findViewById(R.id.cardViewHomePlan);

        planImageView = view.findViewById(R.id.ivPlanHome);
        planDateTextView = view.findViewById(R.id.tvDatePlanHome);
        planDayTextView = view.findViewById(R.id.tvDayPlanHome);
        planPlageTextView = view.findViewById(R.id.tvNamePlagePlanHome);
        planCityTextView = view.findViewById(R.id.tvCityPlanHome);
        welcomeTextView = view.findViewById(R.id.tvWelcome);
        emptyPlanTextView = view.findViewById(R.id.textView9);

        recommendedTextView = view.findViewById(R.id.etRecommended);
        nearYouTextView = view.findViewById(R.id.etNearYou);
        fishingTextView = view.findViewById(R.id.etFishingSpots);
        othresTextView = view.findViewById(R.id.textView9efef);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

        iconLinearLayout = view.findViewById(R.id.linearLayoutPlanHome);
        dateLinearLayout = view.findViewById(R.id.linearLayoutPlanHomeDate);

        cardPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), plageId, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailPlageActivity.class);
                intent.putExtra("idPlageFromHome", plageId);
                startActivity(intent);
            }
        });

        recommendedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendedTextView.setBackgroundResource(R.drawable.rounded_corner);
                recommendedTextView.setTextColor(Color.parseColor("#FFFFFF"));

                nearYouTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                nearYouTextView.setTextColor(Color.parseColor("#CBCBCB"));

                fishingTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                fishingTextView.setTextColor(Color.parseColor("#CBCBCB"));

                othresTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                othresTextView.setTextColor(Color.parseColor("#CBCBCB"));

                Toast.makeText(getContext(), "recommended list", Toast.LENGTH_LONG).show();
                recommendedPlage(currentUser.getId());
            }
        });

        nearYouTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShimmerViewContainer.startShimmerAnimation();

                nearYouTextView.setBackgroundResource(R.drawable.rounded_corner);
                nearYouTextView.setTextColor(Color.parseColor("#FFFFFF"));

                recommendedTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                recommendedTextView.setTextColor(Color.parseColor("#CBCBCB"));


                fishingTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                fishingTextView.setTextColor(Color.parseColor("#CBCBCB"));

                othresTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                othresTextView.setTextColor(Color.parseColor("#CBCBCB"));

                nearPlage("36.858197","11.135506","5");
            }
        });

        fishingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fishingTextView.setBackgroundResource(R.drawable.rounded_corner);
                fishingTextView.setTextColor(Color.parseColor("#FFFFFF"));

                nearYouTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                nearYouTextView.setTextColor(Color.parseColor("#CBCBCB"));

                recommendedTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                recommendedTextView.setTextColor(Color.parseColor("#CBCBCB"));

                othresTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                othresTextView.setTextColor(Color.parseColor("#CBCBCB"));

                Toast.makeText(getContext(), "fishing list", Toast.LENGTH_LONG).show();
            }
        });

        othresTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                othresTextView.setBackgroundResource(R.drawable.rounded_corner);
                othresTextView.setTextColor(Color.parseColor("#FFFFFF"));

                nearYouTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                nearYouTextView.setTextColor(Color.parseColor("#CBCBCB"));

                fishingTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                fishingTextView.setTextColor(Color.parseColor("#CBCBCB"));

                recommendedTextView.setBackgroundResource(R.drawable.rounded_corner_transparent);
                recommendedTextView.setTextColor(Color.parseColor("#CBCBCB"));

                Toast.makeText(getContext(), "others list", Toast.LENGTH_LONG).show();
            }
        });


        welcomeTextView.setText("Welcome " + currentUser.getUsername());

        mRecyclerView = view.findViewById(R.id.rvHome);
        mRecyclerView.setHasFixedSize(true);


        recommendedPlage(currentUser.getId());


        listPlan(currentUser.getId());

        toSearshEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearshActivity.class);
                startActivity(intent);
            }
        });

        ImageButton mapIbtn = view.findViewById(R.id.ibtnToMap);

        mapIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapSearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    private void listePlage() {
        ApiUtil.getServiceClass().allplage().enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();

                // Stopping Shimmer Effect's animation after data is loaded to ListView
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);


                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new HomePlageAdapter(mlist);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new HomePlageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //Toast.makeText(getContext(), mlist.get(position).getId(), Toast.LENGTH_SHORT).show();

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

    private void recommendedPlage(String idUser) {
        ApiUtil.getServiceClass().plageRecommended(idUser).enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();

                // Stopping Shimmer Effect's animation after data is loaded to ListView
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);

                Toast.makeText(getContext(), "rec", Toast.LENGTH_SHORT).show();

                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new HomePlageAdapter(mlist);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new HomePlageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //Toast.makeText(getContext(), mlist.get(position).getId(), Toast.LENGTH_SHORT).show();

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

    private void nearPlage(String lat, String lng, String ditance) {
        ApiUtil.getServiceClass().plageNearYou(lat,lng,ditance).enqueue(new Callback<List<ItemHomePlage>>() {
            @Override
            public void onResponse(Call<List<ItemHomePlage>> call, Response<List<ItemHomePlage>> response) {
                final List<ItemHomePlage> mlist = response.body();

                // Stopping Shimmer Effect's animation after data is loaded to ListView
                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);

                Toast.makeText(getContext(), "rec", Toast.LENGTH_SHORT).show();

                mLayoutManager = new LinearLayoutManager(getContext());
                mAdapter = new HomePlageAdapter(mlist);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new HomePlageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //Toast.makeText(getContext(), mlist.get(position).getId(), Toast.LENGTH_SHORT).show();

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

    private void listPlan(String id) {
        ApiUtil.getServiceClass().getPlan(id).enqueue(new Callback<List<Plan>>() {
            @Override
            public void onResponse(Call<List<Plan>> call, Response<List<Plan>> response) {
                List<Plan> mList = response.body();

                try {
                    Plan firstPlan = mList.get(0);
                    SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = inFormat.parse(firstPlan.getDate());
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                    String goal = outFormat.format(date);
                    System.out.println(goal);
                    //Picasso.get().load(firstPlan.getMainImage()).into(planImageView);
                    planDateTextView.setText(firstPlan.getDate().substring(5, 10));
                    planDayTextView.setText(goal);
                    planPlageTextView.setText(firstPlan.getNomPlage());
                    planCityTextView.setText(firstPlan.getVillePlage());
                    plageId = firstPlan.getIdPlage();
/*
                    System.out.println(firstPlan.getDate());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    SimpleDateFormat format2 = new SimpleDateFormat("E");
                    Date date = format.parse(firstPlan.getDate());
                    String res = format2.format(date);
                    //String stringDate = DateFormat.getDateTimeInstance().format(format);
                    Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();*/


                } catch (Exception e) {
                    dateLinearLayout.setVisibility(INVISIBLE);
                    planPlageTextView.setVisibility(INVISIBLE);
                    planCityTextView.setVisibility(INVISIBLE);
                    iconLinearLayout.setVisibility(INVISIBLE);
                    emptyPlanTextView.setText("Nothing is scheduled yet, Add Plans in the beach of your choosing");
                    // This will catch any exception, because they are all descended from Exception
                    System.out.println("Error " + e.getMessage());
                    Toast.makeText(getContext(), "plan empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Plan>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}
