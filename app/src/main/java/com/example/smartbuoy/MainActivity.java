package com.example.smartbuoy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.UI.Menu.Event.EventsFragment;
import com.example.smartbuoy.UI.Menu.HomeFragment;
import com.example.smartbuoy.UI.Menu.NotificationFragment;
import com.example.smartbuoy.UI.Menu.Profile.ProfileFragment;
import com.example.smartbuoy.lib.FluidBottomNavigation;
import com.example.smartbuoy.lib.FluidBottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // User Session Manager Class
    private UserSessionManager session;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = new HomeFragment();

                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.explore:
                            selectedFragment = new NotificationFragment();
                            break;


                        case R.id.profile:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.events:
                            selectedFragment = new EventsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new UserSessionManager(getApplicationContext());

        // Check user login (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if (session.checkLogin())
            finish();
/*
        Gson gson = new Gson();
        // get user data from session

        String json = session.getUserDetails();
        User user = gson.fromJson(json,User.class);
        System.out.println("$$$$$$$$$$$$$$$$$"+user.toString());
        */

         FluidBottomNavigation navigation = findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        ArrayList<FluidBottomNavigationItem> array = new ArrayList<>();
        array.add(new FluidBottomNavigationItem("Home", getDrawable(R.drawable.ic_home_black_24dp)));
        array.add(new FluidBottomNavigationItem("Events", getDrawable(R.drawable.ic_event_bar)));
        array.add(new FluidBottomNavigationItem("Notification", getDrawable(R.drawable.ic_notification)));
        array.add(new FluidBottomNavigationItem("Profile", getDrawable(R.drawable.ic_profile_bar)));
        navigation.setItems(array);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }
}
