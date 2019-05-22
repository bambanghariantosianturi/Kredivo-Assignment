package com.androidexercise.admin.kredivoassigment.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidexercise.admin.kredivoassigment.R;
import com.bumptech.glide.Glide;

public class BannerPromoAdapter extends RecyclerView.Adapter<BannerPromoAdapter.BannerHolder> {

    int[] banner;
    Activity activity;

    public BannerPromoAdapter(int[] banner, Activity activity) {
        this.banner = banner;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_banner, viewGroup, false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHolder bannerHolder, int i) {

        Glide.with(activity.getApplicationContext())
                .load(banner[i])
                .into(bannerHolder.ivBanner);

    }

    @Override
    public int getItemCount() {
        return banner.length;
    }

    public class BannerHolder extends RecyclerView.ViewHolder {

        ImageView ivBanner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.img_banner);
        }
    }
}
