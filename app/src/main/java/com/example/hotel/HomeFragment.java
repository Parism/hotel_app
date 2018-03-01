package com.example.hotel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home, container, false);


        Toolbar toolbar =  homeView.findViewById(R.id.homeToolbar); //toolbar creation
        final AppCompatActivity activity = (AppCompatActivity) getActivity(); //find current activity
        activity.setSupportActionBar(toolbar);

        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }

        ImageView menu_icon = homeView.findViewById(R.id.action_menu);
        ImageView chat_icon = homeView.findViewById(R.id.action_chat);

        menu_icon.setOnClickListener(new View.OnClickListener() {  //menu listener creation
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Menu !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        chat_icon.setOnClickListener(new View.OnClickListener() {  //chat listener creation
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Chat !!!",
                        Toast.LENGTH_SHORT).show();
            }
        });




        return homeView;
    }






}
