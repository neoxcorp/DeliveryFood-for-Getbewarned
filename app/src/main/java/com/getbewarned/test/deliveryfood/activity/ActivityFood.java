package com.getbewarned.test.deliveryfood.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.fragment.FragmentCart;
import com.getbewarned.test.deliveryfood.fragment.FragmentCategories;
import com.getbewarned.test.deliveryfood.model.CartItem;
import com.getbewarned.test.deliveryfood.util.AppConst;

import java.math.BigDecimal;

public class ActivityFood extends AppCompatActivity {

    private final int LAYOUT = R.layout.activity_food;

    private FrameLayout orderPanelFl;
    private TextView totalPriceTv, rulesTv;
    private Button orderBtn;

    private float totalPrice = 0.0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        init();
        openCategories();
    }

    private void init() {
        orderPanelFl = (FrameLayout) findViewById(R.id.orderPanelFl);
        totalPriceTv = (TextView) findViewById(R.id.totalPriceTv);
        rulesTv = (TextView) findViewById(R.id.rulesTv);
        orderBtn = (Button) findViewById(R.id.orderBtn);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containerFl, new FragmentCart())
                        .addToBackStack(null).commit();
            }
        });

        totalPrice = CartItem.getTotalPrice();
        totalPriceTv.setText(AppConst.EVRO + String.valueOf(totalPrice));
        checkPrice();
    }

    private void openCategories() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerFl, new FragmentCategories()).commit();
    }

    public void showOrderPanel(boolean show) {
        if (totalPrice > 0) {
            orderPanelFl.setVisibility(View.VISIBLE);
        }
        if (show) {
            if (totalPrice > 0)
                orderPanelFl.setVisibility(View.VISIBLE);
        } else {
            orderPanelFl.setVisibility(View.GONE);
        }
        checkPrice();
    }

    private void checkPrice() {
        if (totalPrice >= 20) {
            rulesTv.setVisibility(View.GONE);
        } else {
            rulesTv.setVisibility(View.VISIBLE);
        }
    }

    public void setTotalPrice(float totalPrice, boolean isCart) {
        this.totalPrice = totalPrice;
        totalPriceTv.setText(AppConst.EVRO + String.valueOf(totalPrice));
        if (totalPrice > 0) {
            orderPanelFl.setVisibility(View.VISIBLE);
        }
        if (isCart) {
            showOrderPanel(true);
        } else if (!isCart && totalPrice == 0) {
            showOrderPanel(false);
        }
        checkPrice();
    }
}