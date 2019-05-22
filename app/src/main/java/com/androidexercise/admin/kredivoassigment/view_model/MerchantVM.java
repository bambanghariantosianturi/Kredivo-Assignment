package com.androidexercise.admin.kredivoassigment.view_model;

import com.androidexercise.admin.kredivoassigment.R;

public class MerchantVM {

    public int[] bannerMerchant = {R.drawable.merchant_promo_picture};

    public void getMerchantBanner(final MerchantBannerListener listener) {
        listener.onLoadMerchantBanner();
    }

    public interface MerchantBannerListener {
        void onLoadMerchantBanner();
    }
}
