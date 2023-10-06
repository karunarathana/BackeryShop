package com.example.backeryshop.Model;

public class ItemDetails {
    private String itemName;
    private String itemPrice;
    private String productDsc;
    private String dataImage;

    private String shopName;

    public ItemDetails(String itemName, String itemPrice, String productDsc, String dataImage) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.productDsc = productDsc;
        this.dataImage = dataImage;
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

    public ItemDetails(){

    }
}
