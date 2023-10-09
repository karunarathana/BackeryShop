package com.example.backeryshop.Model;

public class ItemDetails {
    private String itemName;
    private String itemPrice;
    private String productDsc;
    private String dataImage;

    private String shopName;
    private String productId;

    private String uPhoneNumber;
    private String userID;

    private String quantity;

    public ItemDetails(String itemName, String itemPrice, String productDsc, String dataImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.productDsc = productDsc;
        this.dataImage = dataImage;
        this.productId = productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setuPhoneNumber(String uPhoneNumber) {
        this.uPhoneNumber = uPhoneNumber;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setProductDsc(String productDsc) {
        this.productDsc = productDsc;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public String getProductDsc() {
        return productDsc;
    }

    public String getDataImage() {
        return dataImage;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getuPhoneNumber() {
        return uPhoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public ItemDetails(){

    }
}
