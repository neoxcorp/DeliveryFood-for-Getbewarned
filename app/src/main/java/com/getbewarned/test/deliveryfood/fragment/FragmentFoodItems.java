package com.getbewarned.test.deliveryfood.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.activity.ActivityFood;
import com.getbewarned.test.deliveryfood.adapter.CategoriesAdapter;
import com.getbewarned.test.deliveryfood.adapter.FoodAdapter;
import com.getbewarned.test.deliveryfood.listener.TotalPriceListener;
import com.getbewarned.test.deliveryfood.model.CartItem;
import com.getbewarned.test.deliveryfood.model.CategoryItem;
import com.getbewarned.test.deliveryfood.model.FoodItem;
import com.getbewarned.test.deliveryfood.util.AppConst;
import com.getbewarned.test.deliveryfood.util.DataManager;
import com.getbewarned.test.deliveryfood.util.LogTag;

import java.util.ArrayList;
import java.util.List;

public class FragmentFoodItems extends Fragment implements TotalPriceListener {

    private final int LAYOUT = R.layout.fragment_categories;

    private ActivityFood activityFood;

    private List<FoodItem> foodItems = new ArrayList<>();

    private RecyclerView foodsRv;
    private FoodAdapter foodAdapter;

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

        Bundle args = getArguments();
        activityFood.setTitle(args.getString(AppConst.CAT_NAME));
        foodItems.addAll(DataManager.getFoods(args.getString(AppConst.CAT_NAME)));

        foodsRv = (RecyclerView) view.findViewById(R.id.categoriesRv);
        foodAdapter = new FoodAdapter(foodItems);
        foodAdapter.setTotalPriceListener(this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        foodsRv.setLayoutManager(linearLayoutManager);
        foodsRv.setAdapter(foodAdapter);

        foodsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) {//check for scroll down
                    if (state != DOWN) {
                        state = DOWN;
                        LogTag.v("down");
                        activityFood.showOrderPanel(false);
                    }
                } else if (dy < 0) {
                    if (state != UP) {
                        state = UP;
                        LogTag.v("up");
                        activityFood.showOrderPanel(true);
                    }
                }
            }
        });
    }

    @Override
    public void upDateTotalPrice() {
        activityFood.setTotalPrice(CartItem.getTotalPrice(), false);
    }
}