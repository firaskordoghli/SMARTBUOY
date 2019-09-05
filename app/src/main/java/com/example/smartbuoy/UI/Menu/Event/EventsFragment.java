package com.example.smartbuoy.UI.Menu.Event;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.smartbuoy.DATA.Adapters.SectionPagerAdapter;
import com.example.smartbuoy.R;
import com.example.smartbuoy.UI.AddEventActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private com.github.clans.fab.FloatingActionButton fabEvent;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        viewPager = view.findViewById(R.id.viewEventPager);
        tabLayout = view.findViewById(R.id.tabEventLayout);

        fabEvent = view.findViewById(R.id.menu_item_add_event);

        fabEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), AddEventActivity.class);
                startActivity(intent);
            }
        });

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
        adapter.addFragment(new UpcomingEventsFragment(), "Upcoming");
        adapter.addFragment(new PreviousEventsFragment(), "Previous");
        adapter.addFragment(new MyEventsFragment(), "My events");

        viewPager.setAdapter(adapter);
    }

}
