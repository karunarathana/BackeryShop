package com.example.backeryshop.Codes;

public class Order_Item_Details {
   private String customerName;
   private String customerBuyItemName;
   private String customerBuyItemTotal;
   private String customerLongitude;
   private String customerLatitude;
   private String customerPhoneNumber;
   private String customerAddress;
   private String productId;
   private String userID;

    public Order_Item_Details(String customerName, String customerBuyItemName, String customerBuyItemTotal, String customerLongitude, String customerLatitude, String customerPhoneNumber, String customerAddress,String productId,String userID) {
        this.customerName = customerName;
        this.customerBuyItemName = customerBuyItemName;
        this.customerBuyItemTotal = customerBuyItemTotal;
        this.customerLongitude = customerLongitude;
        this.customerLatitude = customerLatitude;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.productId = productId;
        this.userID = userID;
    }

    public Order_Item_Details(){

    }

    public String getProductId() {
        return productId;
    }

    public String getUserID() {
        return userID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerBuyItemName() {
        return customerBuyItemName;
    }

    public String getCustomerBuyItemTotal() {
        return customerBuyItemTotal;
    }

    public String getCustomerLongitude() {
        return customerLongitude;
    }

    public String getCustomerLatitude() {
        return customerLatitude;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerBuyItemName(String customerBuyItemName) {
        this.customerBuyItemName = customerBuyItemName;
    }

    public void setCustomerBuyItemTotal(String customerBuyItemTotal) {
        this.customerBuyItemTotal = customerBuyItemTotal;
    }

    public void setCustomerLongitude(String customerLongitude) {
        this.customerLongitude = customerLongitude;
    }

    public void setCustomerLatitude(String customerLatitude) {
        this.customerLatitude = customerLatitude;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
