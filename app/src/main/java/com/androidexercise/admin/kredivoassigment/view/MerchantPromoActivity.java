package com.androidexercise.admin.kredivoassigment.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.adapter.BannerAdapter;
import com.androidexercise.admin.kredivoassigment.util.Util;
import com.androidexercise.admin.kredivoassigment.view_model.MerchantVM;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantPromoActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.vp_banner_promos)
    ViewPager vpBannerPromos;
    @BindView(R.id.btn_copy)
    Button btnCopy;

    private BannerAdapter bannerAdapter;
    private MerchantVM vm = new MerchantVM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_promo);
        ButterKnife.bind(this);

        Util.setCustomActionBar(this)
                .setTitle("Merchant Promo")
                .setLeftButton(R.drawable.ic_arrow_back_black_24dp, v -> finish())
                .setRightButton(null, null);


        btnCopy.setOnClickListener(this);
        /**
         * This case for Merchant Banner make it possible to many images
         * it set in adapter and view model
         */
        initAdapter();
        loadBannerMerchant();
    }

    private void loadBannerMerchant() {
        vm.getMerchantBanner(() -> bannerAdapter.notifyDataSetChanged());
    }

    private void initAdapter() {
        bannerAdapter = new BannerAdapter(vm.bannerMerchant, this);
        vpBannerPromos.setAdapter(bannerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_copy:
                Toast.makeText(MerchantPromoActivity.this, "Copy", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
