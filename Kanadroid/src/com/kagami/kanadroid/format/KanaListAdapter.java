package com.kagami.kanadroid.format;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class KanaListAdapter extends BaseAdapter {

	private List<Item> mList;
	public KanaListAdapter(List<Item> list){
		mList=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null)
			convertView=new TextView(parent.getContext());
		TextView text=(TextView)convertView;
		text.setText(getItem(position).toString());
		return convertView;
	}

}
