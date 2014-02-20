package com.kagami.kanadroid;

import java.util.ArrayList;
import java.util.List;

import com.kagami.kanadroid.format.Item;

import android.app.Application;

public class KanaApplication extends Application {
	
	public List<Item> getData(){
		List<Item> ret=new ArrayList<Item>();
		ret.add(new Item("yi","1","一"));
		ret.add(new Item("er","2","二"));
		ret.add(new Item("san","3","三"));
		ret.add(new Item("si","4","四"));
		ret.add(new Item("wu","5","五"));
		return ret;
	}

}
