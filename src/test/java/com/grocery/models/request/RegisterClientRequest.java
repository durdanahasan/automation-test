package com.grocery.models.request;

public class RegisterClientRequest {
    public String clientName;
    public String clientEmail;

    public RegisterClientRequest(String clientName, String clientEmail) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }
}