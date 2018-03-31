package com.example.hotel;


public class UserInfo{

    public String chatRoomKey;
    public String lastMessageId;

    public UserInfo() {  }

    public UserInfo(String chatRoomKey, String lastMessageId) {
        this.chatRoomKey = chatRoomKey;
        this.lastMessageId = lastMessageId;
    }

    public String getChatRoomKey() {
        return chatRoomKey;
    }

    public void setChatRoomKey(String chatRoomKey) {
        this.chatRoomKey = chatRoomKey;
    }

    public String getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(String lastMessageId) {
        this.lastMessageId = lastMessageId;
    }



}