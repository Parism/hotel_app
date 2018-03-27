package com.example.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);


        Toolbar toolbar =  homeView.findViewById(R.id.homeToolbar); //toolbar creation
        final AppCompatActivity activity = (AppCompatActivity) getActivity(); //find current activity
        activity.setSupportActionBar(toolbar);

        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }


        final DrawerLayout mDrawerLayout = activity.findViewById(R.id.menu_drawer);

        ImageView menu_icon = homeView.findViewById(R.id.action_menu);
        ImageView chat_icon = homeView.findViewById(R.id.action_chat);

        menu_icon.setOnClickListener(new View.OnClickListener() {  //menu listener creation
            @Override
            public void onClick(View view) {  //menu icon listener creation
                mDrawerLayout.openDrawer(GravityCompat.START);//
            }
        });



        chat_icon.setOnClickListener(new View.OnClickListener() {  //chat listener creation
            @Override
            public void onClick(View view) {  //chat icon listener
                ((HomeActivity)activity).setCurrentItem (1, true); //brings you to the chat fragment
            }
        });


        ImageView roomsButton = homeView.findViewById(R.id.roomsButtonImage);

        roomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,RoomsActivity.class);
                startActivity(intent);
            }
        });




        homeView.setOnTouchListener(new OnSwipeTouchListener(activity) {  //Handles swipes
            public void onSwipeTop() {
                //Toast.makeText(activity, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {    //Right swipe opens the drawer
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            public void onSwipeLeft() {
                //Toast.makeText(activity, "left", Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();
            }
            public void onSwipeBottom() {
                //Toast.makeText(activity, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        return homeView;
    }


}
