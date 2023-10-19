package com.example.backeryshop.Codes;

public class Cart_Item_Details {
    private String cartImageUrl;
    private String cartItemId;
    private String cartItemName;
    private String cartItemPrice;
    private String cartItemQuantity;
    private String cartItemShopName;

    private String pID;
    private String userID;
    private String userPhoneNumber;
    private String cartItemStatus;
    private String userLatitude;
    private String userLongitude;


    public String getUserLatitude() {
        return userLatitude;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getUserLongitude() {
        return userLongitude;
    }

    public String getpID() {
        return pID;
    }

    public String getCartItemStatus() {
        return cartItemStatus;
    }

    public String getUserID() {
        return userID;
    }

    public String getCartImageUrl() {
        return cartImageUrl;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public String getCartItemPrice() {
        return cartItemPrice;
    }

    public String getCartItemQuantity() {
        return cartItemQuantity;
    }

    public String getCartItemShopName() {
        return cartItemShopName;
    }

    public void setCartImageUrl(String cartImageUrl) {
        this.cartImageUrl = cartImageUrl;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setCartItemName(String cartItemName) {
        this.cartItemName = cartItemName;
    }

    public void setCartItemPrice(String cartItemPrice) {
        this.cartItemPrice = cartItemPrice;
    }

    public void setCartItemQuantity(String cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

    public void setCartItemShopName(String cartItemShopName) {
        this.cartItemShopName = cartItemShopName;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCartItemStatus(String cartItemStatus) {
        this.cartItemStatus = cartItemStatus;
    }

    public void setUserLatitude(String userLatitude) {
        this.userLatitude = userLatitude;
    }

    public void setUserLongitude(String userLongitude) {
        this.userLongitude = userLongitude;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Cart_Item_Details(){

    }
}
