package com.example.backeryshop.Codes;

public class Upload_Item_Details {

    private String shopName;
    private String productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String imageURL;

    public Upload_Item_Details(String shopName,String productName, String productDescription, String productPrice, String imageURL,String productId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
        this.productId = productId;
        this.shopName = shopName;
    }
    public Upload_Item_Details(String productName, String productDescription, String productPrice, String imageURL,String productId) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
        this.productId = productId;
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

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getImageURL() {
        return imageURL;
    }
}
