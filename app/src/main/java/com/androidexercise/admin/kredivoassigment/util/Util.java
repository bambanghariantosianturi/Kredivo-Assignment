package com.androidexercise.admin.kredivoassigment.util;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidexercise.admin.kredivoassigment.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Util {

    /**
     * Create by Bambang Harianto Sianturi 20/05/2019
     *
     * @param activity
     * @return
     */
    public static CustomActionBar setCustomActionBar(AppCompatActivity activity) {
        activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        activity.getSupportActionBar().setDisplayShowCustomEnabled(true);
        activity.getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view = activity.getSupportActionBar().getCustomView();
        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);
        return new CustomActionBar(activity.getSupportActionBar());
    }

    public static class CustomActionBar {
        ImageView mIvClose;
        ImageView mIvRight;
        TextView mTvTitle;
        View mBtnOverflow;
        ActionBar mActionBar;
        float mElevation;
        Context mContext;

        public CustomActionBar(ActionBar actionBar) {
            mActionBar = actionBar;
            mElevation = mActionBar.getElevation();
            mContext = mActionBar.getCustomView().getContext();
            mIvClose = mActionBar.getCustomView().findViewById(R.id.iv_close);
            mIvRight = mActionBar.getCustomView().findViewById(R.id.iv_overflow);
            mTvTitle = mActionBar.getCustomView().findViewById(R.id.tv_title);
            mBtnOverflow = mActionBar.getCustomView().findViewById(R.id.iv_overflow);
            setMenus(null, null);
        }

        public CustomActionBar setTitle(String title) {
            if (mTvTitle != null) mTvTitle.setText(title);
            return this;
        }

        public CustomActionBar setLeftButton(Integer resID, View.OnClickListener onClickListener) {
            if (mIvClose != null) {
                mIvClose.setVisibility((resID == null || onClickListener == null) ?
                        View.GONE : View.VISIBLE);

                if (resID != null) mIvClose.setImageResource(resID);
                mIvClose.setOnClickListener(onClickListener);
            }
            return this;
        }

        public CustomActionBar setRightButton(Integer resID, View.OnClickListener onClickListener) {
            if (mIvRight != null) {
                mIvRight.setVisibility((resID == null || onClickListener == null) ?
                        View.GONE : View.VISIBLE);

                if (resID != null) mIvRight.setImageResource(resID);
                mIvRight.setOnClickListener(onClickListener);
            }
            return this;
        }

        public CustomActionBar setMenus(Integer menuResID, PopupMenu.OnMenuItemClickListener onMenuClickListener) {
            if (mBtnOverflow != null) {
                mBtnOverflow.setVisibility((menuResID == null || onMenuClickListener == null) ?
                        View.GONE : View.VISIBLE);

                final PopupMenu popupMenu = new PopupMenu(mContext, mBtnOverflow);
                if (menuResID != null)
                    popupMenu.getMenuInflater().inflate(menuResID, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(onMenuClickListener);
                mBtnOverflow.setOnClickListener(v -> popupMenu.show());
            }
            return this;
        }
    }

    public static String formatCurrency(Long number) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) format).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) format).setDecimalFormatSymbols(decimalFormatSymbols);
        format.setMinimumFractionDigits(0);
        return format.format(number);
    }
}
