package com.example.hotel;


public class UserInfo{

    public String chatRoomKey;
    public String lastMessageId;
    public String name;

    public UserInfo() {  }

    @SuppressWarnings("WeakerAccess")
    public UserInfo(String chatRoomKey, String lastMessageId, String name) {
        this.chatRoomKey = chatRoomKey;
        this.lastMessageId = lastMessageId;
        this.name = name;
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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

}