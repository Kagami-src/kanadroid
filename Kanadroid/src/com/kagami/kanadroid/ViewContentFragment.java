package com.kagami.kanadroid;

import java.util.List;

import com.kagami.kanadroid.format.Item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewContentFragment extends Fragment {
	private ViewPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v=inflater.inflate(R.layout.content_view, container, false);
		mViewPager=(ViewPager)v.findViewById(R.id.pager);
		mViewPager.setAdapter(new ContentPagerAdapter(getFragmentManager(), ((KanaApplication)getActivity().getApplication()).getData()));
		return v;
	}

	public static class ContentPagerAdapter extends FragmentStatePagerAdapter {
		private List<Item> mList;

		public ContentPagerAdapter(FragmentManager fm, List<Item> list) {
			super(fm);
			mList = list;
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment fragment = new ItemFragment();
			Bundle args = new Bundle();
			args.putParcelable("item", mList.get(arg0));
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return mList.get(position).getRoman();
		}

	}

	public static class ItemFragment extends Fragment {
		private Item mItem;

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View v = inflater.inflate(R.layout.fragment_item_view, container,
					false);
			Bundle args = getArguments();
			mItem = args.getParcelable("item");
			((TextView)v.findViewById(R.id.textView1)).setText(mItem.getRoman());
			((TextView)v.findViewById(R.id.textView2)).setText(mItem.getHirakana());
			((TextView)v.findViewById(R.id.textView3)).setText(mItem.getKatakana());
			return v;
		}
		
	}

}
