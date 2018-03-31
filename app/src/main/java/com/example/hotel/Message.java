package com.example.hotel;


public class Message {

    private String uId;//the sender's user Id (uid)
    private String message;
    private String time;

    public Message(){}

    public Message(String text, String name, String time) {
        this.message = text;
        this.uId = name;
        this.time = time;
    }


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
