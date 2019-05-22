package com.androidexercise.admin.kredivoassigment.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.util.Util;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanConfirmationActivity extends AppCompatActivity {

    public static final String PHONE_NUMBER = "phone_number";
    public static final String PRICE = "price";

    @BindView(R.id.tv_pay)
    TextView mBtnPay;
    @BindView(R.id.tv_phone_number_loan)
    TextView tvPhoneNumberLoan;
    @BindView(R.id.tv_price_loan)
    TextView tvPriceLoan;
    @BindView(R.id.tv_price_pay)
    TextView tvPricePay;
    @BindView(R.id.tv_admin_fee)
    TextView tvAdminFee;
    @BindView(R.id.et_pin_password)
    EditText etPinPassword;
    @BindView(R.id.iv_see_password)
    ImageView seePassword;
    private String phone;
    private long price;
    private long adminFee = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_confirmation);
        ButterKnife.bind(this);

        Util.setCustomActionBar(this)
                .setTitle("Loan Confirmation")
                .setLeftButton(R.drawable.ic_arrow_back_black_24dp, v -> finish())
                .setRightButton(null, null);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phone = bundle.getString(PHONE_NUMBER);
            price = Long.parseLong(Objects.requireNonNull(bundle.getString(PRICE)));
        }

        mBtnPay.setOnClickListener(v -> {
            if (!isFinishing()) {
                if (mBtnPay == v) {
                    String pinPassword = etPinPassword.getText().toString().trim();
                    if (!TextUtils.isEmpty(pinPassword)) {
                        Intent intent = new Intent(LoanConfirmationActivity.this, PaymentDetailsActivity.class);
                        Bundle b = new Bundle();
                        b.putString(LoanConfirmationActivity.PHONE_NUMBER, phone);
                        b.putString(LoanConfirmationActivity.PRICE, String.valueOf(price));
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    } else {
                        etPinPassword.setError("Cannot Be Empty or Your PIN Wrong");
                    }

                }
            }
        });

        seePassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    seePassword.setImageResource(R.drawable.ic_visibility_gray_24dp);
                    etPinPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else if (action == MotionEvent.ACTION_UP) {
                    seePassword.setImageResource(R.drawable.ic_visibility_off_gray_24dp);
                    etPinPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                return true;
            }
        });

        setUI();
    }

    private void setUI() {
        tvPhoneNumberLoan.setText(phone);
        String formatPrice = Util.formatCurrency(price);
        tvPriceLoan.setText("Rp " + formatPrice);
        tvAdminFee.setText("Rp " + adminFee);

        long calculatePrice = price + adminFee;

        tvPricePay.setText("Rp " + Util.formatCurrency(calculatePrice));
    }
}
