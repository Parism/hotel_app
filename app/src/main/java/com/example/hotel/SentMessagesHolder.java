package com.example.hotel;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class SentMessagesHolder extends RecyclerView.ViewHolder {

    private TextView messageText, timeText;

    public SentMessagesHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.message_sent);
        timeText = itemView.findViewById(R.id.message_time_sent);
    }

    void bind(Message message) {
        messageText.setText(message.getMessage());
        timeText.setText(message.getTime());//DateUtils.formatDateTime(message.getCreatedAt())
    }
}