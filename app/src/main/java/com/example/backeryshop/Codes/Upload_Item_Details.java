package com.example.backeryshop.Codes;

public class Upload_Item_Details {

    private String productName;
    private String productDescription;
    private String productPrice;
    private String imageURL;

    public Upload_Item_Details(String productName, String productDescription, String productPrice, String imageURL) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
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
