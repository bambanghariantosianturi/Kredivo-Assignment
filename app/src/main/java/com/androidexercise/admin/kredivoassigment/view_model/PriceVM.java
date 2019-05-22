package com.androidexercise.admin.kredivoassigment.view_model;

import com.androidexercise.admin.kredivoassigment.model.Price;

import java.util.ArrayList;

public class PriceVM {

    public ArrayList<Price> priceList = new ArrayList<>();

    public void getPrices(final PriceVMListener listener) {
        Price price = new Price((long) 5000);
        priceList.add(price);

        price = new Price((long) 10000);
        priceList.add(price);

        price = new Price((long) 15000);
        priceList.add(price);

        price = new Price((long) 20000);
        priceList.add(price);

        price = new Price((long) 25000);
        priceList.add(price);

        price = new Price((long) 30000);
        priceList.add(price);

        price = new Price((long) 35000);
        priceList.add(price);

        price = new Price((long) 40000);
        priceList.add(price);

        price = new Price((long) 45000);
        priceList.add(price);

        price = new Price((long) 50000);
        priceList.add(price);

        listener.onPriceLoad();
    }

    public interface PriceVMListener {
        void onPriceLoad();
    }
}
