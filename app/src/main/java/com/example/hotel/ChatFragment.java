package com.example.hotel;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ChatFragment extends Fragment {

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;//the allowed message maximum length


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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



        RecyclerView messagesRecycler = chatView.findViewById(R.id.messages_recycler_view);
        List<Message> messageList = new ArrayList<>();
        MessageListAdapter messagesAdapter = new MessageListAdapter(messageList);

        //messagesRecycler.setLayoutManager(new LinearLayoutManager(activity));

        messagesRecycler.setAdapter(messagesAdapter);

//        fillList(messageList);



        final EditText chatEditText = chatView.findViewById(R.id.chat_edit_text);
        final ImageView chatSendButton = chatView.findViewById(R.id.chat_send_button);
        chatEditText.addTextChangedListener(new TextWatcher() {     // Enable Send button when there's text to send
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    chatSendButton.setEnabled(true);
                } else {
                    chatSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        chatEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});


        // Send button sends a message and clears the EditText
        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Send messages on click
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Message friendlyMessage = new Message(chatEditText.getText().toString(), null, currentDateTimeString);
                //mMessagesDatabaseReference.push().setValue(friendlyMessage);


                chatEditText.setText("");// Clear input box
            }
        });





        return chatView;
    }


    private void fillList(List<Message> messageList){
        String name = "john";
        String message = "lalalalallal";
        String time = "1234";
        Message m = new Message(name,message,time);
        messageList.add(m);
    }




}
