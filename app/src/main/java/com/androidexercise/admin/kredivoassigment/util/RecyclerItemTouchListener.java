package com.androidexercise.admin.kredivoassigment.util;
/*
 * Create by Bambang Harianto Sianturi on 5/22/2019.
 *
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

    public interface onItemClickListener {
        void onClickSingle(View view, int position);

        void onLongClick(View view, int position);
    }

    private GestureDetector gestureDetector;
    private onItemClickListener onItemClickListener;

    public RecyclerItemTouchListener(Context context, RecyclerView recyclerView, RecyclerItemTouchListener.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }


            @Override
            public void onLongPress(MotionEvent e) {
                View xView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (xView != null && onItemClickListener != null) {
                    onItemClickListener.onLongClick(xView, recyclerView.getChildAdapterPosition(xView));
                }
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View xView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (xView != null && onItemClickListener != null && gestureDetector.onTouchEvent(motionEvent)) {
            onItemClickListener.onClickSingle(xView, recyclerView.getChildAdapterPosition(xView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
