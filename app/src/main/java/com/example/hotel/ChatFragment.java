package com.example.hotel;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ChatFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View chatView = inflater.inflate(R.layout.fragment_chat, container, false);

        Toolbar toolbar =  chatView.findViewById(R.id.chatToolbar); //toolbar creation
        final AppCompatActivity activity = (AppCompatActivity) getActivity(); //find current activity
        activity.setSupportActionBar(toolbar);

        if(activity.getSupportActionBar() != null){
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false); //hide app title from toolbar
        }

        ImageView arrow_icon = chatView.findViewById(R.id.action_home);
        arrow_icon.setOnClickListener(new View.OnClickListener() {  //arrow icon listener
            @Override
            public void onClick(View view) {
                ((HomeActivity)activity).setCurrentItem (0, true); //brings you to the chat fragment
            }
        });


        return chatView;
    }


}
