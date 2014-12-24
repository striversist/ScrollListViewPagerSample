package com.example.scrolllistviewpagersample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";
    private ViewPager mPager;
    private View mPagerHeader;
    private View mRoot;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerHeader = findViewById(R.id.pager_header);
        
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
        
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        
        Log.d(TAG, "init");
    }
}
