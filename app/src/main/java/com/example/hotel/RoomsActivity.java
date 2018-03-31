package com.example.hotel;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class RoomsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.rooms_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.rooms_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Junior Suites"));
        tabLayout.addTab(tabLayout.newTab().setText("Master Suites"));
        tabLayout.addTab(tabLayout.newTab().setText("Apartments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        if(this.getSupportActionBar() != null){
            this.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }

        ImageView arrow_icon = RoomsActivity.this.findViewById(R.id.rooms_back_button);
        arrow_icon.setOnClickListener(new View.OnClickListener() {  //arrow icon listener
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final RoomsFragmentAdapter adapter = new RoomsFragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
