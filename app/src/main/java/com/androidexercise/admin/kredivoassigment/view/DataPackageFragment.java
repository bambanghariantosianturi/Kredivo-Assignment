package com.androidexercise.admin.kredivoassigment.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.adapter.BannerPromoAdapter;
import com.androidexercise.admin.kredivoassigment.adapter.PriceListAdapter;
import com.androidexercise.admin.kredivoassigment.util.RecyclerItemTouchListener;
import com.androidexercise.admin.kredivoassigment.view_model.BannerVM;
import com.androidexercise.admin.kredivoassigment.view_model.PriceVM;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataPackageFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.rv_list_price)
    RecyclerView rvListPrice;
    @BindView(R.id.et_number_phone)
    EditText etNumberPhone;
    @BindView(R.id.ln_empty_data)
    LinearLayout lnEmptyData;
    @BindView(R.id.calc_clear_txt_Prise)
    ImageView mClearNumber;
    @BindView(R.id.iv_read_contact)
    ImageView mReadContact;
    @BindView(R.id.rv_banner_promos)
    RecyclerView rvBannerPromos;

    private PriceListAdapter adapter;
    private BannerPromoAdapter promoAdapter;
    private PriceVM priceVM = new PriceVM();
    private BannerVM bannerVM = new BannerVM();

    final int duration = 5;
    final int pixelsToMove = 20;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            rvBannerPromos.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() != 0 && s.length() > 3) {
                rvListPrice.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etNumberPhone.getText().toString().length() > 0 && etNumberPhone.getText().toString().length() > 3) {
                rvListPrice.setVisibility(View.VISIBLE);
            } else {
                rvListPrice.setVisibility(View.GONE);
            }
        }
    };

    public DataPackageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_package, container, false);
        ButterKnife.bind(this, view);

        etNumberPhone.addTextChangedListener(textWatcher);

        initAdapter();
        initAdapterPromos();
        loadBanner();
        loadDataDummy();
        setClick();

        return view;
    }

    private void initAdapterPromos() {
        promoAdapter = new BannerPromoAdapter(bannerVM.banner, getActivity());
        RecyclerView.LayoutManager layoutPromos = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBannerPromos.setLayoutManager(layoutPromos);
        rvBannerPromos.addOnItemTouchListener(new RecyclerItemTouchListener(getActivity(), rvBannerPromos,
                new RecyclerItemTouchListener.onItemClickListener() {
                    @Override
                    public void onClickSingle(View view, int position) {
                        Intent intentPromos = new Intent(getActivity(), MerchantPromoActivity.class);
                        startActivity(intentPromos);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
        rvBannerPromos.setAdapter(promoAdapter);

        /**
         * Animate Banner
         * Create By Bambang Harianto Sianturi 5/22/2019
         */
        rvBannerPromos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = ((LinearLayoutManager) layoutPromos).findLastCompletelyVisibleItemPosition();
                if (lastItem == layoutPromos.getItemCount() - 1) {
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rvBannerPromos.setAdapter(null);
                            rvBannerPromos.setAdapter(promoAdapter);
                            mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                        }
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
    }

    private void setClick() {
        mClearNumber.setOnClickListener(this);
        mReadContact.setOnClickListener(this);
    }

    private void loadBanner() {
        bannerVM.getBanner(() -> {
            if (!getActivity().isFinishing()) {
                promoAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initAdapter() {
        adapter = new PriceListAdapter(priceVM.priceList, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvListPrice.setLayoutManager(layoutManager);
        rvListPrice.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvListPrice.addOnItemTouchListener(new RecyclerItemTouchListener(getActivity(), rvListPrice,
                new RecyclerItemTouchListener.onItemClickListener() {
                    @Override
                    public void onClickSingle(View view, int position) {
                        long price = adapter.getItemId(position);
                        Intent intent = new Intent(getActivity(), LoanConfirmationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(LoanConfirmationActivity.PHONE_NUMBER, etNumberPhone.getText().toString().trim());
                        bundle.putString(LoanConfirmationActivity.PRICE, String.valueOf(price));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
        rvListPrice.setAdapter(adapter);
    }

    private void loadDataDummy() {
        priceVM.getPrices(() -> {
            if (!getActivity().isFinishing()) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calc_clear_txt_Prise:
                etNumberPhone.getText().clear();
                break;
            case R.id.iv_read_contact:
                readContactNumber();
                break;
        }
    }

    private void readContactNumber() {
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Can't Read Contact No Case For This", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
