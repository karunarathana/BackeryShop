package com.example.backeryshop.Codes;

public class Upload_Item_Details_Cart {
    private String shopName;
    private String productId;
    private String productName;
    private String productPrice;
    private String imageURL;
    private String quantity;
    private String itemStatus;
    private String userId;

    public Upload_Item_Details_Cart(String shopName,String productName,String productPrice, String imageURL,String productId,String quantity,String itemStatus,String userID) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
        this.productId = productId;
        this.shopName = shopName;
        this.quantity = quantity;
        this.itemStatus = itemStatus;
        this.userId = userID;
    }

    public String getUserId() {
        return userId;
    }
    public String getItemStatus() {
        return itemStatus;
    }
    public String getShopName() {
        return shopName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getQuantity() {
        return quantity;
    }
}
