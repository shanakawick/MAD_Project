package com.example.kitchenstories;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private int itemImage;

    public FoodData(String itemName, String itemDescription, String itemPrice,int itemImage){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }


    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getItemImage() {
        return itemImage;
    }
}
