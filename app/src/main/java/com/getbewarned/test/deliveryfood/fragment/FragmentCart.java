package com.getbewarned.test.deliveryfood.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.activity.ActivityFood;
import com.getbewarned.test.deliveryfood.adapter.CartAdapter;
import com.getbewarned.test.deliveryfood.listener.TotalPriceListener;
import com.getbewarned.test.deliveryfood.model.CartItem;
import com.getbewarned.test.deliveryfood.util.AppConst;

import java.util.ArrayList;
import java.util.List;

public class FragmentCart extends Fragment implements TotalPriceListener {

    private final int LAYOUT = R.layout.fragment_cart;

    private ActivityFood activityFood;

    private List<CartItem> cartItems = new ArrayList<>();

    private TextView totalPriceInCartTv;
    private RecyclerView cartItemsRv;
    private CartAdapter cartAdapter;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private int state = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(LAYOUT, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        activityFood = (ActivityFood) getActivity();

        activityFood.setTitle("Cart");
        cartItems.addAll(CartItem.getAllData());

        totalPriceInCartTv = (TextView) view.findViewById(R.id.totalPriceInCartTv);
        cartItemsRv = (RecyclerView) view.findViewById(R.id.cartItemsRv);
        cartAdapter = new CartAdapter(cartItems);
        cartAdapter.setTotalPriceListener(this);

        upDateTotalPriceInCart();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        cartItemsRv.setLayoutManager(linearLayoutManager);
        cartItemsRv.setAdapter(cartAdapter);

        this.getView().setFocusableInTouchMode(true);
        this.getView().requestFocus();
        this.getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    activityFood.setTotalPrice(CartItem.getTotalPrice(), false);
                    activityFood.getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void upDateTotalPrice() {
        upDateTotalPriceInCart();
    }

    private void upDateTotalPriceInCart() {
        float tp = CartItem.getTotalPrice();
        activityFood.setTotalPrice(tp, true);
        totalPriceInCartTv.setText(AppConst.EVRO + String.valueOf(tp));
    }
}