package com.getbewarned.test.deliveryfood.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getbewarned.test.deliveryfood.R;
import com.getbewarned.test.deliveryfood.model.CategoryItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<CategoryItem> itemList;
    private Context context;

    public CategoriesAdapter(List<CategoryItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_categories_sub_categories_item, viewGroup, false);
        context = v.getContext();
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final CategoryItem item = itemList.get(i);
        myViewHolder.titleTv.setText(item.getName());
        Picasso.with(context).load(item.getImageUrl())
                .placeholder(R.drawable.loading)
                .error(R.drawable.def).into(myViewHolder.imageIv);
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
        TextView titleTv;
        ImageView imageIv;

        public MyViewHolder(View view) {
            super(view);
            titleTv = (TextView) view.findViewById(R.id.catTitleTv);
            imageIv = (ImageView) view.findViewById(R.id.catImage);
        }
    }
}