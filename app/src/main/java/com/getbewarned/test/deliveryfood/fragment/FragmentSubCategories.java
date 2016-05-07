package com.getbewarned.test.deliveryfood.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.activity.ActivityFood;
import com.getbewarned.test.deliveryfood.adapter.CategoriesAdapter;
import com.getbewarned.test.deliveryfood.listener.OnItemClickListener;
import com.getbewarned.test.deliveryfood.model.CategoryItem;
import com.getbewarned.test.deliveryfood.util.AppConst;
import com.getbewarned.test.deliveryfood.util.DataManager;
import com.getbewarned.test.deliveryfood.util.LogTag;

import java.util.ArrayList;
import java.util.List;

public class FragmentSubCategories extends Fragment {

    private final int LAYOUT = R.layout.fragment_categories;

    private ActivityFood activityFood;

    private List<CategoryItem> categoryItems = new ArrayList<>();

    private RecyclerView categoriesRv;
    private CategoriesAdapter categoriesAdapter;

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

        categoryItems.addAll(DataManager.getSubCategories(args.getInt(AppConst.CAT_TYPE)));

        categoriesRv = (RecyclerView) view.findViewById(R.id.categoriesRv);
        categoriesAdapter = new CategoriesAdapter(categoryItems);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        categoriesRv.setLayoutManager(gridLayoutManager);
        categoriesRv.setAdapter(categoriesAdapter);

        categoriesRv.addOnItemTouchListener(new OnItemClickListener(getActivity(),
                new OnItemClickListener.MyOnItemClickListener() {
                    @Override
                    public void OnItemClick(View view, int position) {
                        CategoryItem categoryItem = categoryItems.get(position);
                        LogTag.v("sub categoryItem - " + categoryItem.getName());

                        Bundle args = new Bundle();
                        args.putInt(AppConst.CAT_TYPE, categoryItem.getCategoryType());
                        args.putString(AppConst.CAT_NAME, categoryItem.getName());

                        FragmentFoodItems fragmentFoodItems = new FragmentFoodItems();
                        fragmentFoodItems.setArguments(args);
                        activityFood.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerFl, fragmentFoodItems)
                                .addToBackStack(null).commit();
                    }
                }));

        categoriesRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//check for scroll down
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
}