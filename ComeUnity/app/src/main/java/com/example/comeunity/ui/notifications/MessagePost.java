package com.example.comeunity.ui.notifications;

public class MessagePost {
    private String user;
    private String date;
    private String message;
    private String receiver;

    public MessagePost (String user, String date, String message, String receiver){
        this.user = user;
        this.date = date;
        this.message = message;
        this.receiver = "TODO";
    }
    public MessagePost(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
