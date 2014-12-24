package com.example.scrolllistviewpagersample;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Fragment1 extends Fragment {

	public static final String TAG = "Fragment1";
	private ListView mListView;
	private MyArrayAdapter mAdapter;
	
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
        
        return layout;
    }
	
	private ArrayList<String> getData() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i=0; i<20; ++i) {
            result.add("Hello item " + i);
        }
        return result;
    }
}
