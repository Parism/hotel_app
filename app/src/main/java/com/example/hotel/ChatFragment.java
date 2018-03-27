package com.example.hotel;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class ChatFragment extends Fragment {

    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;//the allowed message maximum length
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListener;

    private RecyclerView messagesRecyclerView;
    private MessageListAdapter messagesAdapter;
    private List<Message> messageList;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private String uId;
    private String roomId;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        uId = ((HomeActivity)getActivity()).getUId();//the current user's id

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    attachDatabaseReadListener();
                } else {
                    detachDatabaseListener();
                    messageList.clear(); //clear list
                    messagesAdapter.notifyDataSetChanged(); //let your adapter know about the changes and reload view.
                }
            }
        };


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


        messageList = new ArrayList<>();

        messagesRecyclerView = chatView.findViewById(R.id.messages_recycler_view);

        messagesAdapter = new MessageListAdapter(messageList,uId);
        messagesRecyclerView.setAdapter(messagesAdapter);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messagesRecyclerView.setLayoutManager(mLayoutManager);
        messagesRecyclerView.setItemAnimator(new DefaultItemAnimator());




        final EditText chatEditText = chatView.findViewById(R.id.chat_edit_text);
        final ImageView chatSendButton = chatView.findViewById(R.id.chat_send_button);
        chatSendButton.setEnabled(false);   //disable the send button
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
        chatEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});//set editext maximum length limit


        // Send button sends a message and clears the EditText
        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {    //Send messages on click


                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                final Message mMessage = new Message(chatEditText.getText().toString(), uId, currentDateTimeString);//message creation


                mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(uId);
                mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserInfo userInfo= dataSnapshot.getValue(UserInfo.class);

                        if(userInfo != null) {
                            String roomKey = userInfo.chatRoomKey;

                            mDatabaseReference = mFirebaseDatabase.getReference().child("chatRooms");
                            String messageId = mDatabaseReference.child(roomKey).push().getKey();
                            mDatabaseReference.child(roomKey).child(messageId).setValue(mMessage);//insert message in chatRooms

                            mDatabaseReference = mFirebaseDatabase.getReference().child("users");//update messageId in users
                            mDatabaseReference.child(uId).child("lastMessageId").setValue(messageId);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                chatEditText.setText("");// Clear input box


            }
        });

        return chatView;
    }



    @Override
    public void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        detachDatabaseListener();

        messageList.clear(); //clear list
        messagesAdapter.notifyDataSetChanged(); //let your adapter know about the changes and reload view.
    }


    private void attachDatabaseReadListener(){
        if(mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Message mMessage = dataSnapshot.getValue(Message.class);
                    messageList.add(mMessage);
                    messagesRecyclerView.scrollToPosition(messageList.size()-1);
                    messagesAdapter.notifyItemInserted(messageList.size() - 1);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };


            mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(uId);
            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {    // User Exists

                        UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                        roomId = userInfo.chatRoomKey;//find the user's room Id

                        mDatabaseReference = mFirebaseDatabase.getReference().child("chatRooms").child(roomId);
                        mDatabaseReference.addChildEventListener(mChildEventListener);

                    } else{    //user doesn't exist
                        mDatabaseReference = mFirebaseDatabase.getReference().child("chatRooms");//a reference to the chatRooms' node
                        roomId = mDatabaseReference.push().getKey();//create and retrieve the chatRoom's id

                        mDatabaseReference = mFirebaseDatabase.getReference().child("users");//reference to the users' node

                        String userName = mFirebaseAuth.getCurrentUser().getDisplayName();
                        UserInfo userInfo = new UserInfo(roomId,null,userName);
                        mDatabaseReference.child(uId).setValue(userInfo);//save the room's id & the user's name in users node

                        mDatabaseReference = mFirebaseDatabase.getReference().child("chatRooms").child(roomId);
                        mDatabaseReference.addChildEventListener(mChildEventListener);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });


        }
    }


    private void detachDatabaseListener(){
        if(mChildEventListener != null){
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }



}
