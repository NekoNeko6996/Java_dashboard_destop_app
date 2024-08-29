package com.application.models;


public class HttpResponse {
    private final String status;
    private final String message;
    private final String token;
    
    public HttpResponse(String status, String message, String token) {
        this.status = status;
        this.message = message;
        this.token = token;
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
