package com.example.hotel;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private View homeView;
    private AppCompatActivity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        mDrawerLayout = homeView.findViewById(R.id.menu_drawer);

        Toolbar toolbar =  homeView.findViewById(R.id.homeToolbar); //toolbar creation
        activity = (AppCompatActivity) getActivity(); //find current activity
        activity.setSupportActionBar(toolbar);

        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }

        ImageView menu_icon = homeView.findViewById(R.id.action_menu);
        ImageView chat_icon = homeView.findViewById(R.id.action_chat);

        menu_icon.setOnClickListener(new View.OnClickListener() {  //menu listener creation
            @Override
            public void onClick(View view) {  //menu listener creation
                //Toast.makeText(activity, "Menu !!!", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.openDrawer(GravityCompat.START);//
            }
        });

        chat_icon.setOnClickListener(new View.OnClickListener() {  //chat listener creation
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Chat !!!", Toast.LENGTH_SHORT).show();
            }
        });



       NavigationView navigationView = homeView.findViewById(R.id.menu);
        navigationView.setNavigationItemSelectedListener(                   //Handle the
               new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        int id = menuItem.getItemId();

                        if (id == R.id.nav_delete_account) {
                            // Handle the camera action
                            Toast.makeText(activity, "yeeeeeeeeeeeeeeeeeeeeeeeeee !!!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, "yeooooooooooooooooooooooooooo !!!",
                                    Toast.LENGTH_LONG).show();
                        }



                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });







        return homeView;
    }






}
