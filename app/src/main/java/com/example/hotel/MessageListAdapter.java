package com.example.hotel;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter{

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List<Message> messages;
    private String mId;

    MessageListAdapter(List<Message> messageList,String uId) {
        messages = messageList;
        mId = uId;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message =  messages.get(position);

        if (message.getName().equals(mId)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }


    // Inflates the appropriate layout according to the ViewType.
    @Override @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView messageDateTime = view.findViewById(R.id.message_time_sent);
                    if(messageDateTime.getVisibility() == View.VISIBLE){//if the message dateTime is visible
                        messageDateTime.setVisibility(View.GONE);
                    } else {
                        messageDateTime.setVisibility(View.VISIBLE);
                    }
                }
            });
            return new SentMessagesHolder(view);

        } else { //if (viewType == VIEW_TYPE_MESSAGE_RECEIVED){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView messageDateTime = view.findViewById(R.id.message_time_received);
                    TextView messageName = view.findViewById(R.id.name_message_received);
                    if(messageDateTime.getVisibility() == View.VISIBLE){//if the message dateTime is visible
                        messageDateTime.setVisibility(View.GONE);
                        messageName.setVisibility(View.GONE);
                    } else {
                        messageDateTime.setVisibility(View.VISIBLE);
                        messageName.setVisibility(View.VISIBLE);
                    }
                }
            });
            return new ReceivedMessagesHolder(view);
        }
    }


    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessagesHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessagesHolder) holder).bind(message);
        }
    }




}
