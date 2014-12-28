package com.example.scrolllistviewpagersample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class OverScrollableScrollView extends ScrollView {

    public static final String TAG = "OverScrollableScrollView";
    private float xDistance, yDistance, xLast, yLast;
    private boolean mIsTop = true, mIsBottom = false;
    private boolean mIsScrollingUp = false;
    private OverScrollController mController;
    
    public interface OverScrollController {
        public boolean canScrollUp();
    }
    
    public OverScrollableScrollView(Context context) {
        this(context, null, 0);
    }
    
    public OverScrollableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public OverScrollableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setController(OverScrollController controller) {
        mController = controller;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
//            xDistance = yDistance = 0f;
            xLast = ev.getX();
            yLast = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            final float curX = ev.getX();
            final float curY = ev.getY();
//            xDistance += Math.abs(curX - xLast);
//            yDistance += Math.abs(curY - yLast);
            if (curY > yLast) {
                mIsScrollingUp = true;
            } else {
                mIsScrollingUp = false;
            }
            xLast = curX;
            yLast = curY;
//            if (xDistance > yDistance) {
//                return false;
//            }
            
//            int scrollY = getScrollY();
//            int height = getHeight();
//            View contentView = getChildAt(0);
//            int measuredHeight = contentView.getMeasuredHeight();
//            Log.d(TAG, "scrollY=" + scrollY + ", height=" + height
//                    + ", measuredHeight=" + measuredHeight);
//            if (scrollY == 0) {
//                Log.d(TAG, "滑动到了顶端 view.getScrollY()=" + scrollY);
//            }
//            if ((scrollY + height) == measuredHeight) {
//                Log.d(TAG, "滑动到了底部 scrollY=" + scrollY);
//                Log.d(TAG, "滑动到了底部 height=" + height);
//                Log.d(TAG, "滑动到了底部 measuredHeight=" + measuredHeight);
//            }
            
            int childTop = 0;
            if (getChildCount() > 0) {
                childTop = getChildAt(getChildCount() - 1).getTop();
            }
            Log.d(TAG, "onInterceptTouchEvent childCount=" + getChildCount() + ", childTop=" + childTop);
            
            if (mController != null && mIsBottom) {
                if (mIsScrollingUp && mController.canScrollUp()) {
                    break;
                } else {
                    return false;
                }
            }
            
            break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        Log.d(TAG, "onScrollChanged x=" + x + ", y=" + y + ", oldx=" + oldx + ", oldy=" + oldy);
        super.onScrollChanged(x, y, oldx, oldy);
        doOnBorderListener();
    }
    
    private void doOnBorderListener() {
        View contentView = getChildAt(0);
        if (contentView != null && contentView.getMeasuredHeight() == getScrollY() + getHeight()) {
            mIsBottom = true;
            OnBottom();
        } else if (getScrollY() == 0) {
            mIsTop = true;
            OnTop();
        } else {
            mIsTop = mIsBottom = false;
        }
    }
    
    private void OnTop() {
        Log.d(TAG, "OnTop");
    }
    
    private void OnBottom() {
        Log.d(TAG, "OnBottom");
    }
}
