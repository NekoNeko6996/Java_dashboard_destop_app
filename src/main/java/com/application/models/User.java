package com.application.models;

public class User {
    private final String userID;
    private final String fullName;
    
    private final String status;
    private final String message;
    private final String token;

    public User(String userID, String fullName, String status, String message, String token) {
        this.userID = userID;
        this.fullName = fullName;
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
