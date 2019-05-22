package com.androidexercise.admin.kredivoassigment.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.androidexercise.admin.kredivoassigment.R;
import com.androidexercise.admin.kredivoassigment.adapter.FragmentAdapter;
import com.androidexercise.admin.kredivoassigment.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.pager)
    ViewPager mPager;

    private long lastPressedTime;
    private static final long PERIOD = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Util.setCustomActionBar(this)
                .setTitle("Top Up")
                .setLeftButton(null, null)
                .setRightButton(null, null);

        initTabsLayout();
        initAdapter();
    }

    private void initTabsLayout() {
        mTabs.addTab(mTabs.newTab().setText("Pulsa"));
        mTabs.addTab(mTabs.newTab().setText("Data Package"));
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.holo_orange_dark));
        ViewCompat.setElevation(mTabs, 10);
    }

    private void initAdapter() {
        PagerAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Press Again To Exit", Toast.LENGTH_LONG).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
