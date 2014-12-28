package com.example.scrolllistviewpagersample;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	public static final int TAB_PARAMS_INDEX = 0;
	public static final int TAB_DETAILS_INDEX = 1;
	public static final int TAB_REVIEWS_INDEX = 2;
	private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
	
	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
		
		mFragmentList.add(TAB_PARAMS_INDEX, new Fragment1());
		mFragmentList.add(TAB_DETAILS_INDEX, new Fragment2());
		mFragmentList.add(TAB_REVIEWS_INDEX, new Fragment3());
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position);
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

}
