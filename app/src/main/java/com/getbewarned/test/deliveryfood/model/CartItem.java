package com.getbewarned.test.deliveryfood.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.getbewarned.test.deliveryfood.util.LogTag;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "CartItems", id = "_id")
public class CartItem extends Model {

    @Column public String name, categoryName, imageSource;
    @Column
    public int quantity = 1;
    @Column public float price, totalPrice;

    public CartItem() {
        super();
    }

    public CartItem(String name, String categoryName, String imageSource, int quantity,
                    float price, float totalPrice) {
        super();
        this.name = name;
        this.categoryName = categoryName;
        this.imageSource = imageSource;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public static List<CartItem> getAllData() {
        return new Select()
                .from(CartItem.class)
                .execute();
    }

    public static float getTotalPrice() {
        float totalPrice = 0.0F;
        List<CartItem> cartItems = new Select().from(CartItem.class)
                .execute();
        LogTag.v("cartItems - " + cartItems);
        for (CartItem ci : cartItems) {
            totalPrice = totalPrice + ci.totalPrice;
        }
        totalPrice = round(totalPrice, 2);
        return totalPrice;
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}