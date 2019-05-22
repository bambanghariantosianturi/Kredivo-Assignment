package com.androidexercise.admin.kredivoassigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidexercise.admin.kredivoassigment.R;
import com.bumptech.glide.Glide;

public class BannerAdapter extends PagerAdapter {

    int[] banner;
    Activity activity;

    public BannerAdapter(int[] banner, Activity activity) {
        this.banner = banner;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return banner.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_banner, container, false);
        ImageView imageView = view.findViewById(R.id.img_banner);

//        view.setTag(position);
//        view.setOnClickListener(v -> {
//            int position1 = (Integer) v.getTag();
//            Toast.makeText(activity, "Click Gambar Ke " + position1, Toast.LENGTH_LONG).show();
//            Intent intentPromos = new Intent(activity, MerchantPromoActivity.class);
//            activity.startActivity(intentPromos);
//        });

        Glide.with(activity)
                .load(banner[position])
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
