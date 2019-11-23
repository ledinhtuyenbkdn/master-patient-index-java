package com.ledinhtuyenbkdn.masterpersonindex.service.dto;

public class LoginSuccessDTO {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "{" +
                "\"accessToken\":\"" + accessToken + "\"" +
                '}';
    }
}
