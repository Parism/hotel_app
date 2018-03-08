package com.example.hotel;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class HomeActivity extends AppCompatActivity {


    private ViewPager viewPager;


    public void setCurrentItem (int item, boolean smoothScroll) { //scrolls from the current fragment to the item fragment
        viewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the view pager that will allow the user to swipe between fragments
        viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);




    }




}
