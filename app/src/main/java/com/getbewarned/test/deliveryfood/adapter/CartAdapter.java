package com.getbewarned.test.deliveryfood.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.listener.TotalPriceListener;
import com.getbewarned.test.deliveryfood.model.CartItem;
import com.getbewarned.test.deliveryfood.util.AppConst;
import com.getbewarned.test.deliveryfood.util.LogTag;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private TotalPriceListener totalPriceListener;

    private List<CartItem> itemList;
    private Context context;

    public CartAdapter(List<CartItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_cart_item, viewGroup, false);
        context = v.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final CartItem item = itemList.get(i);
        myViewHolder.foodTitleCartTv.setText(item.name);
        myViewHolder.foodPriceCartTv.setText(AppConst.EVRO + String.valueOf(item.price));
        myViewHolder.foodTotalPriceCartTv
                .setText(AppConst.EVRO + String.valueOf(CartItem.round(item.totalPrice, 2)));
        myViewHolder.foodCartQuantityTv.setText(String.valueOf(item.quantity));
        Picasso.with(context).load(item.imageSource)
                .placeholder(R.drawable.loading)
                .error(R.drawable.def).into(myViewHolder.foodCartImage);

        checkData(item, myViewHolder);

        myViewHolder.foodAddOneCartImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuantity(item, myViewHolder);
            }
        });
        myViewHolder.foodRemoveOneCartImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeQuantity(item, myViewHolder);
            }
        });
        myViewHolder.foodRemoveItemCartImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFromOrder(item);
            }
        });
    }

    private void checkData(CartItem foodItem, final MyViewHolder myViewHolder) {
        if (foodItem.quantity > 1) {
            myViewHolder.foodRemoveOneCartImb.setEnabled(true);
            myViewHolder.foodRemoveOneCartImb.setColorFilter(R.color.color_disable_btn);
        } else {
            myViewHolder.foodRemoveOneCartImb.setEnabled(false);
            myViewHolder.foodRemoveOneCartImb.setColorFilter(android.R.color.darker_gray);
        }
        if (foodItem.quantity < 99) {
            myViewHolder.foodAddOneCartImb.setEnabled(true);
            myViewHolder.foodAddOneCartImb.setColorFilter(R.color.color_disable_btn);
        } else {
            myViewHolder.foodAddOneCartImb.setEnabled(false);
            myViewHolder.foodAddOneCartImb.setColorFilter(android.R.color.darker_gray);
        }
    }

    private void deleteFromOrder(CartItem item) {
        LogTag.v("name - " + item.name);
        item.delete();
        itemList.remove(item);
        if (totalPriceListener != null) {
            totalPriceListener.upDateTotalPrice();
        }
        notifyDataSetChanged();
    }

    private void addQuantity(CartItem cartItem, final MyViewHolder myViewHolder) {
        if (cartItem.quantity < 99) {
            cartItem.quantity = cartItem.quantity + 1;
            cartItem.totalPrice = cartItem.quantity * cartItem.price;
            cartItem.totalPrice = CartItem.round(cartItem.totalPrice, 2);
            myViewHolder.foodCartQuantityTv.setText(String.valueOf(cartItem.quantity));
            myViewHolder.foodTotalPriceCartTv
                    .setText(String.valueOf(CartItem.round(cartItem.totalPrice, 2)));
        }

        checkData(cartItem, myViewHolder);
        cartItem.save();
        if (totalPriceListener != null) {
            totalPriceListener.upDateTotalPrice();
        }
    }

    private void removeQuantity(CartItem cartItem, final MyViewHolder myViewHolder) {
        if (cartItem.quantity > 1) {
            cartItem.quantity = cartItem.quantity - 1;
            cartItem.totalPrice = cartItem.quantity * cartItem.price;
            cartItem.totalPrice = CartItem.round(cartItem.totalPrice, 2);
            myViewHolder.foodCartQuantityTv.setText(String.valueOf(cartItem.quantity));
            myViewHolder.foodTotalPriceCartTv
                    .setText(String.valueOf(CartItem.round(cartItem.totalPrice, 2)));
        }

        checkData(cartItem, myViewHolder);
        cartItem.save();
        if (totalPriceListener != null) {
            totalPriceListener.upDateTotalPrice();
        }
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
        TextView foodTitleCartTv, foodPriceCartTv, foodTotalPriceCartTv, foodCartQuantityTv;
        ImageView foodCartImage;
        ImageButton foodRemoveOneCartImb, foodAddOneCartImb, foodRemoveItemCartImb;

        public MyViewHolder(View view) {
            super(view);
            foodTitleCartTv = (TextView) view.findViewById(R.id.foodTitleCartTv);
            foodPriceCartTv = (TextView) view.findViewById(R.id.foodPriceCartTv);
            foodTotalPriceCartTv = (TextView) view.findViewById(R.id.foodTotalPriceCartTv);
            foodCartQuantityTv = (TextView) view.findViewById(R.id.foodCartQuantityTv);
            foodCartImage = (ImageView) view.findViewById(R.id.foodCartImage);
            foodRemoveOneCartImb = (ImageButton) view.findViewById(R.id.foodRemoveOneCartImb);
            foodAddOneCartImb = (ImageButton) view.findViewById(R.id.foodAddOneCartImb);
            foodRemoveItemCartImb = (ImageButton) view.findViewById(R.id.foodRemoveItemCartImb);
        }
    }

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }
}