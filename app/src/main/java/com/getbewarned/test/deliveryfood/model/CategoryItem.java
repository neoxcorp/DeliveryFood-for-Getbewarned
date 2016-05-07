package com.getbewarned.test.deliveryfood.model;

public class CategoryItem {

    private int categoryType = 0; // 0 - is sub category, 1,2,3 - is category
    private String name, imageUrl;

    public CategoryItem(int categoryType, String name, String imageUrl) {
        this.categoryType = categoryType;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}