package com.getbewarned.test.deliveryfood.util;

import com.getbewarned.test.deliveryfood.model.CategoryItem;
import com.getbewarned.test.deliveryfood.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static final int FIRST_CAT = 1;
    public static final int SECOND_CAT = 2;
    public static final int THIRD_CAT = 3;

    public static List<CategoryItem> getCategories() {
        List<CategoryItem> categoryItems = new ArrayList<>();
        categoryItems.add(new CategoryItem(FIRST_CAT, "Meat 1", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(SECOND_CAT, "Meat 2", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(THIRD_CAT, "Meat 3", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(0, "Meat 4", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(0, "Meat 5", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(0, "Meat 6", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(0, "Meat 7", AppConst.DEF_IMG_CAT));
        categoryItems.add(new CategoryItem(0, "Meat 8", AppConst.DEF_IMG_CAT));
        return categoryItems;
    }

    public static List<CategoryItem> getSubCategories(int PARENT_CATEGORY) {
        List<CategoryItem> categoryItems = new ArrayList<>();
        switch (PARENT_CATEGORY) {
            case FIRST_CAT:
                categoryItems.add(new CategoryItem(0, "Sub 11", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 12", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 13", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 14", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 15", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 16", AppConst.DEF_IMG_SUB));
                break;
            case SECOND_CAT:
                categoryItems.add(new CategoryItem(0, "Sub 21", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 22", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 23", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 24", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 25", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 26", AppConst.DEF_IMG_SUB));
                break;
            case THIRD_CAT:
                categoryItems.add(new CategoryItem(0, "Sub 31", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 32", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 33", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 34", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 35", AppConst.DEF_IMG_SUB));
                categoryItems.add(new CategoryItem(0, "Sub 36", AppConst.DEF_IMG_SUB));
                break;
        }
        return categoryItems;
    }

    public static List<FoodItem> getFoods(String catName) {
        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem(catName + " - Food 1", "Description for food 1. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 7.5F, 1));
        foodItems.add(new FoodItem(catName + " - Food 2", "Description for food 2. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 5.1F, 1));
        foodItems.add(new FoodItem(catName + " - Food 3", "Description for food 3. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 8.9F, 1));
        foodItems.add(new FoodItem(catName + " - Food 4", "Description for food 4. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 1.2F, 1));
        foodItems.add(new FoodItem(catName + " - Food 5", "Description for food 5. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 4.7F, 1));
        foodItems.add(new FoodItem(catName + " - Food 6", "Description for food 6. And...",
                AppConst.DEF_CAT_NAME, AppConst.DEF_IMG_FOOD, 6.2F, 1));
        return foodItems;
    }
}