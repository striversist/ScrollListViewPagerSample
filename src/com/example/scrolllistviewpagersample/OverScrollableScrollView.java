package com.example.scrolllistviewpagersample;

import android.app.ActionBar.Tab;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class OverScrollableScrollView extends ScrollView {

    public static final String TAG = "OverScrollableScrollView";
    private float xDistance, yDistance, xLast, yLast;
    public OverScrollableScrollView(Context context) {
        this(context, null, 0);
    }
    
    public OverScrollableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public OverScrollableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            xDistance = yDistance = 0f;
            xLast = ev.getX();
            yLast = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
//            final float curX = ev.getX();
//            final float curY = ev.getY();
//            xDistance += Math.abs(curX - xLast);
//            yDistance += Math.abs(curY - yLast);
//            xLast = curX;
//            yLast = curY;
//            if (xDistance > yDistance) {
//                return false;
//            }
            
            int scrollY = getScrollY();
            int height = getHeight();
            int scrollViewMeasuredHeight = getMeasuredHeight();
            Log.d(TAG, "scrollY=" + scrollY + ", height=" + height
                    + ", scrollViewMeasuredHeight=" + scrollViewMeasuredHeight);
            if (scrollY == 0) {
                Log.d(TAG, "滑动到了顶端 view.getScrollY()=" + scrollY);
            }
            if ((scrollY + height) == scrollViewMeasuredHeight) {
                Log.d(TAG, "滑动到了底部 scrollY=" + scrollY);
                Log.d(TAG, "滑动到了底部 height=" + height);
                Log.d(TAG, "滑动到了底部 scrollViewMeasuredHeight="
                        + scrollViewMeasuredHeight);
            }
            break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
