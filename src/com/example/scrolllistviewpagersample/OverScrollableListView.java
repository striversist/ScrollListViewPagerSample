package com.example.scrolllistviewpagersample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class OverScrollableListView extends ListView {

//    85691800
//    ME4GC035
    public static final String TAG = "OverScrollableListView";
    private OnBorderListener mBorderListener;
    public interface OnBorderListener {
        public void OnTop(View view);
        public void OnBottom(View view);
    }
    
    public OverScrollableListView(Context context) {
        this(context, null, 0);
    }
    
    public OverScrollableListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public OverScrollableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setOnBorderListener(OnBorderListener listener) {
        mBorderListener = listener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        doOnBorderListener();
    }
    
    private void doOnBorderListener() {
        View contentView = getChildAt(0);
        if (contentView != null && contentView.getMeasuredHeight() == getScrollY() + getHeight()) {
            OnBottom();
        } else if (getScrollY() == 0) {
            OnTop();
        }
    }
    
    private void OnTop() {
        Log.d(TAG, "OnTop");
        if (mBorderListener != null) {
            mBorderListener.OnTop(this);
        }
    }
    
    private void OnBottom() {
        Log.d(TAG, "OnBottom");
        if (mBorderListener != null) {
            mBorderListener.OnBottom(this);
        }
    }
}
