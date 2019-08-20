package com.example.smartbuoy.UI.Menu.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.smartbuoy.DATA.Adapters.SectionPagerAdapter;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    UserSessionManager session;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        ImageView profileImageView = view.findViewById(R.id.imageViewProfile);
        TextView fullNameTextView = view.findViewById(R.id.textviewFullname);

        session = new UserSessionManager(getContext());

        Gson gson = new Gson();
        User currentUser = gson.fromJson(session.getUserDetails(), User.class);

        Picasso.get().load(currentUser.getImage()).into(profileImageView);
        fullNameTextView.setText(currentUser.getUsername());

        Toast.makeText(getContext(), currentUser.getUsername(), Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new UpcomingFragment(), "Upcoming");
        adapter.addFragment(new FavoritesFragment(), "Favorites");
        adapter.addFragment(new HistoryFragment(), "History");

        viewPager.setAdapter(adapter);
    }
}
