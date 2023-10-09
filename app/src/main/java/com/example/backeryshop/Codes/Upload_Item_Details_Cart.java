package com.example.backeryshop.Codes;

public class Upload_Item_Details_Cart {
    private String shopName;
    private String productId;
    private String productName;
    private String productPrice;
    private String imageURL;
    private String quantity;

    public Upload_Item_Details_Cart(String shopName,String productName,String productPrice, String imageURL,String productId,String quantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
        this.productId = productId;
        this.shopName = shopName;
        this.quantity = quantity;
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
