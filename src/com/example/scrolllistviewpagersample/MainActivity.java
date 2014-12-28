package com.example.scrolllistviewpagersample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";
    private ViewPager mPager;
    private MyPagerAdapter mAdapter;
    private View mPagerHeader;
    private View mRoot;
    private OverScrollableScrollView mScrollView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerHeader = findViewById(R.id.pager_header);
        mScrollView = (OverScrollableScrollView) findViewById(R.id.scroll);
        
        final View root = findViewById(R.id.root);
        ViewTreeObserver vto2 = root.getViewTreeObserver();    
        vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {  
            @Override    
            public void onGlobalLayout() {
                root.getViewTreeObserver().removeGlobalOnLayoutListener(this); 
                int height = root.getHeight();
                init();
                Log.d(TAG, "");
            }    
        });
        mRoot = root;
    }
    
    private void init() {
        int top = mPagerHeader.getTop();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mPager.getLayoutParams();
//        params.height = (int) getResources().getDimension(R.dimen.view_pager_init_height);
        params.height = mRoot.getHeight() - mPagerHeader.getHeight();
        mPager.setLayoutParams(params);
        
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == MyPagerAdapter.TAB_PARAMS_INDEX) {
                    Fragment1 fragment = (Fragment1) mAdapter.getItem(position);
                    mScrollView.setController(fragment);
                }
            }
            
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
        Log.d(TAG, "init");
    }
}
