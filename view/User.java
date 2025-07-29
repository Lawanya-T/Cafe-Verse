package com.cafeverse.view;

public class User {
    private String name;
    private String email;
    private String phone;
    private String location;

    public User(String name, String email, String phone, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
}