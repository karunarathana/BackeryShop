package com.example.backeryshop.Codes;

public class Shop_Owner_Signup_Details {
    private String userEmailAdd;
    private String userCompanyName;
    private String userPassword;

    private String userTelNumber;

    public Shop_Owner_Signup_Details(String userEmailAdd, String userCompanyName, String userPassword,String userTelNumber) {
        this.userEmailAdd = userEmailAdd;
        this.userCompanyName = userCompanyName;
        this.userPassword = userPassword;
        this.userTelNumber = userTelNumber;
    }

    public String getUserTelNumber() {
        return userTelNumber;
    }

    public String getUserEmailAdd() {
        return userEmailAdd;
    }

    public String getUserCompanyName() {
        return userCompanyName;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
