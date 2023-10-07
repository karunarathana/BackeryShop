package com.example.backeryshop.Codes;

import java.util.Random;

public class User_Sign_Up_Details {

    private String Email ;
    private String phone;
    private String password;
    private String userID;


    public User_Sign_Up_Details(String userID,String email, String phone, String password) {
        this.Email = email;
        this.phone = phone;
        this.password = password;
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }


    public String getUserID() {
        return userID;
    }
}
