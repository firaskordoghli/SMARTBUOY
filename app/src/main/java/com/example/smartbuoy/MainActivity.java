package com.example.smartbuoy;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smartbuoy.DATA.UserSessionManager;
import com.example.smartbuoy.UI.Menu.Event.EventsFragment;
import com.example.smartbuoy.UI.Menu.ExploreFragment;
import com.example.smartbuoy.UI.Menu.HomeFragment;
import com.example.smartbuoy.UI.Menu.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    // User Session Manager Class
    UserSessionManager session;

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
                            selectedFragment = new ExploreFragment();
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

        Gson gson = new Gson();
        // get user data from session

        /*
        String json = session.getUserDetails();
        User user = gson.fromJson(json,User.class);
        System.out.println("$$$$$$$$$$$$$$$$$"+user.toString());
        */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

    }
}
