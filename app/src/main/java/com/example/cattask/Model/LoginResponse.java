package com.example.cattask.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("userdata")
    List<User> userdata;
    @SerializedName("Message")
    String Message;
    @SerializedName("state")
    String state;


    public List<User> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<User> userdata) {
        this.userdata = userdata;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
