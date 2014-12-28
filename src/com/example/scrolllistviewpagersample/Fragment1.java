package com.example.scrolllistviewpagersample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scrolllistviewpagersample.OverScrollableScrollView.OverScrollController;

public class Fragment1 extends Fragment implements OverScrollController {

	public static final String TAG = "Fragment1";
	private ListView mListView;
	private MyArrayAdapter mAdapter;
	private boolean mCanScrollUp = false;
	
	private class MyArrayAdapter extends ArrayAdapter<String> {
        List<String> mObjects;
        public MyArrayAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
        }
        
        @Override
        public long getItemId (int position) {
            return getItem(position).hashCode();
        }
        
        public void add(int position, String item) {
            mObjects.add(position, item);
            notifyDataSetChanged();
        }
    }

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment1, container, false);
        mListView = (ListView) layout.findViewById(R.id.list);
        mAdapter = new MyArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getData());
        
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int childTop = 0;
                if (view.getChildCount() > 0) {
                    childTop = view.getChildAt(0).getTop();
                }
                Log.d(TAG, "childTop=" + childTop);
                if (firstVisibleItem == 0 && childTop == 0) {
                    mCanScrollUp = true;
                } else {
                    mCanScrollUp = false;
                }
            }
        });
        
        return layout;
    }
	
	private ArrayList<String> getData() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i=0; i<20; ++i) {
            result.add("Hello item " + i);
        }
        return result;
    }

    @Override
    public boolean canScrollUp() {
        return mCanScrollUp;
    }
}
