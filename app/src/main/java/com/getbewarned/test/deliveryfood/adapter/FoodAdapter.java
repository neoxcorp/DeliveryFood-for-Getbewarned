package com.getbewarned.test.deliveryfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.listener.TotalPriceListener;
import com.getbewarned.test.deliveryfood.model.CartItem;
import com.getbewarned.test.deliveryfood.model.FoodItem;
import com.getbewarned.test.deliveryfood.util.AppConst;
import com.getbewarned.test.deliveryfood.util.LogTag;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private TotalPriceListener totalPriceListener;

    private List<FoodItem> itemList;
    private Context context;

    public FoodAdapter(List<FoodItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_food_item, viewGroup, false);
        context = v.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final FoodItem item = itemList.get(i);
        myViewHolder.foodTitleTv.setText(item.getName());
        myViewHolder.foodDescriptionTv.setText(item.getDescription());
        myViewHolder.foodPriceTv.setText(AppConst.EVRO + String.valueOf(item.getPrice()));
        myViewHolder.foodQuantityTv.setText(String.valueOf(item.getQuantity()));
        Picasso.with(context).load(item.getImageSource())
                .placeholder(R.drawable.loading)
                .error(R.drawable.def).into(myViewHolder.foodImage);

        checkData(item, myViewHolder);

        myViewHolder.foodAddOneImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuantity(item, myViewHolder);
            }
        });
        myViewHolder.foodRemoveOneImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeQuantity(item, myViewHolder);
            }
        });
        myViewHolder.addToOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder(item);
            }
        });
    }

    private void checkData(FoodItem foodItem, final MyViewHolder myViewHolder) {
        if (foodItem.getQuantity() > 1) {
            myViewHolder.foodRemoveOneImb.setEnabled(true);
            myViewHolder.foodRemoveOneImb.setColorFilter(R.color.color_disable_btn);
        } else {
            myViewHolder.foodRemoveOneImb.setEnabled(false);
            myViewHolder.foodRemoveOneImb.setColorFilter(android.R.color.darker_gray);
        }
        if (foodItem.getQuantity() < 99) {
            myViewHolder.foodAddOneImb.setEnabled(true);
            myViewHolder.foodAddOneImb.setColorFilter(R.color.color_disable_btn);
        } else {
            myViewHolder.foodAddOneImb.setEnabled(false);
            myViewHolder.foodAddOneImb.setColorFilter(android.R.color.darker_gray);
        }
    }

    private void addToOrder(FoodItem item) {
        LogTag.v("item - " + item.getName());
        CartItem cartItem = new CartItem(item.getName(), item.getCategoryName(), item.getImageSource(),
                item.getQuantity(), item.getPrice(), item.getQuantity() * item.getPrice());
        cartItem.save();
        LogTag.v("cartItem - " + cartItem.name);
        if (totalPriceListener != null) {
            totalPriceListener.upDateTotalPrice();
        }
    }

    private void addQuantity(FoodItem foodItem, final MyViewHolder myViewHolder) {
        if (foodItem.getQuantity() < 99) {
            foodItem.setQuantity(foodItem.getQuantity() + 1);
            myViewHolder.foodQuantityTv.setText(String.valueOf(foodItem.getQuantity()));
        }

        checkData(foodItem, myViewHolder);
    }

    private void removeQuantity(FoodItem foodItem, final MyViewHolder myViewHolder) {
        if (foodItem.getQuantity() > 1) {
            foodItem.setQuantity(foodItem.getQuantity() - 1);
            myViewHolder.foodQuantityTv.setText(String.valueOf(foodItem.getQuantity()));
        }

        checkData(foodItem, myViewHolder);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView foodTitleTv, foodDescriptionTv, foodPriceTv, foodQuantityTv;
        ImageView foodImage;
        ImageButton foodRemoveOneImb, foodAddOneImb;
        Button addToOrderBtn;

        public MyViewHolder(View view) {
            super(view);
            foodTitleTv = (TextView) view.findViewById(R.id.foodTitleTv);
            foodDescriptionTv = (TextView) view.findViewById(R.id.foodDescriptionTv);
            foodPriceTv = (TextView) view.findViewById(R.id.foodPriceTv);
            foodQuantityTv = (TextView) view.findViewById(R.id.foodQuantityTv);
            foodImage = (ImageView) view.findViewById(R.id.foodImage);
            foodRemoveOneImb = (ImageButton) view.findViewById(R.id.foodRemoveOneImb);
            foodAddOneImb = (ImageButton) view.findViewById(R.id.foodAddOneImb);
            addToOrderBtn = (Button) view.findViewById(R.id.addToOrderBtn);
        }
    }

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }
}