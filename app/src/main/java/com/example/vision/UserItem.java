package com.example.vision;

public class UserItem {
    private int imageResource;
    private String username;

    public UserItem(int imageResource, String username) {
        this.imageResource = imageResource;
        this.username = username;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getUsername() {
        return username;
    }
}
