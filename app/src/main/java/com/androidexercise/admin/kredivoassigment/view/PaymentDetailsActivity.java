package com.androidexercise.admin.kredivoassigment.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.util.Util;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_ok)
    TextView mBtnOk;
    @BindView(R.id.tv_phone_detail)
    TextView tvPhoneDetail;
    @BindView(R.id.tv_price_detail)
    TextView tvPriceDetail;
    @BindView(R.id.tv_admin_fee_detail)
    TextView tvAdminFeeDetail;
    @BindView(R.id.tv_price_pay_detail)
    TextView tvPricePayDetail;

    private String phone;
    private long price;
    private long adminFee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        ButterKnife.bind(this);

        Util.setCustomActionBar(this)
                .setTitle("Payment Details")
                .setLeftButton(R.drawable.ic_close_black_24dp, v -> finish())
                .setRightButton(null, null);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phone = bundle.getString(LoanConfirmationActivity.PHONE_NUMBER);
            price = Long.parseLong(Objects.requireNonNull(bundle.getString(LoanConfirmationActivity.PRICE)));

            mBtnOk.setOnClickListener(v -> {
                finish();
            });
        }

        setUI();
    }

    private void setUI() {
        tvPhoneDetail.setText(phone);
        String formatPrice = Util.formatCurrency(price);
        tvPriceDetail.setText("Rp " + formatPrice);
        tvAdminFeeDetail.setText("Rp " + adminFee);

        long calculatePrice = price + adminFee;

        tvPricePayDetail.setText("Rp " + Util.formatCurrency(calculatePrice));
    }
}