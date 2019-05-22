package com.androidexercise.admin.kredivoassigment.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.model.Price;
import com.androidexercise.admin.kredivoassigment.util.Util;

import java.util.List;

public class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.PriceHolder> {

    private List<Price> list;
    private Activity activity;

    public PriceListAdapter(List<Price> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PriceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_price, viewGroup, false);
        return new PriceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceHolder priceHolder, int i) {
        Price price = list.get(i);
        priceHolder.onBind(price);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getPrice();
    }

    public class PriceHolder extends RecyclerView.ViewHolder {

        private TextView tvPrice;
        private Button btnPrice;

        public PriceHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tv_price);
            btnPrice = itemView.findViewById(R.id.btn_book);

        }

        public void onBind(Price price) {
            String formatPrice = Util.formatCurrency(price.getPrice());
            tvPrice.setText(formatPrice);
            btnPrice.setText("Rp " + formatPrice);
        }
    }
}
