package com.getbewarned.test.deliveryfood.model;

public class FoodItem {

    private String name, description, categoryName, imageSource;
    private float price;
    private int quantity = 1;

    public FoodItem(String name, String description, String categoryName, String imageSource,
                    float price, int quantity) {
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.imageSource = imageSource;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}