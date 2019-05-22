package com.androidexercise.admin.kredivoassigment.view_model;

import com.androidexercise.admin.kredivoassigment.R;

public class BannerVM {

    public int[] banner = {R.drawable.axis_new, R.drawable.indosat_new, R.drawable.telkomsel_new, R.drawable.xl_new};

    public void getBanner(final BannerVMListener listener) {
        listener.onPriceLoad();
    }

    public interface BannerVMListener {
        void onPriceLoad();
    }
}
