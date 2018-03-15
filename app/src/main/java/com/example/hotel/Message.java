package com.example.hotel;


public class Message {

    private String name;
    private String message;
    private String time;


    public Message(String text, String name, String time) {
        this.message = text;
        this.name = name;
        this.time = time;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
