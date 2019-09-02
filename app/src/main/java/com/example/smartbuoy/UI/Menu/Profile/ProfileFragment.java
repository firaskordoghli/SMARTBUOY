package com.example.smartbuoy.UI.Menu.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.smartbuoy.DATA.Adapters.SectionPagerAdapter;
import com.example.smartbuoy.DATA.Models.User;
import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.R;
import com.google.android.material.navigation.NavigationView;
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

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;


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

        //Toast.makeText(getContext(), currentUser.getUsername(), Toast.LENGTH_SHORT).show();


        // Find our drawer view
        mDrawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);

        // Find our drawer view
        nvDrawer = (NavigationView) view.findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);


        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Toast.makeText(getContext(), "nav_first_fragment", Toast.LENGTH_SHORT).show();
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$ nav_third_fragment");
                break;
            case R.id.nav_second_fragment:
                Toast.makeText(getContext(), "nav_second_fragment", Toast.LENGTH_SHORT).show();
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$ nav_second_fragment");
                break;
            case R.id.nav_third_fragment:
                Toast.makeText(getContext(), "nav_third_fragment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                Toast.makeText(getContext(), "nav_about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_log_out:
                Toast.makeText(getContext(), "nav_log_out", Toast.LENGTH_SHORT).show();
                break;

        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        //setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
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
