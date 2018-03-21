package com.example.hotel;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ReceivedMessagesHolder extends RecyclerView.ViewHolder {

    private TextView messageText, timeText, nameText;

    public ReceivedMessagesHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.message_received);
        timeText = itemView.findViewById(R.id.message_time_received);
        nameText = itemView.findViewById(R.id.name_message_received);
    }

    void bind(Message message) {
        messageText.setText(message.getMessage());
        timeText.setText(message.getTime());
        //nameText.setText(message.getName());
    }
}