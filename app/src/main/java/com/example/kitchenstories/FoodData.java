package com.example.kitchenstories;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private String itemImage;

    public FoodData(String itemName, String itemDescription, String itemPrice,String itemImage){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public FoodData(String prawn_curry, String itemDescription, String itemPrice, int prawn) {

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

    public String getItemImage() {
        return itemImage;
    }
}
