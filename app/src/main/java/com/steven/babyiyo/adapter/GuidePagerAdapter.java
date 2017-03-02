package com.steven.babyiyo.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GuidePagerAdapter extends PagerAdapter{

	private List<LinearLayout> list_linearLayout;
	public GuidePagerAdapter(List<LinearLayout> list_linearLayout){
		this.list_linearLayout = list_linearLayout;
	}

	@Override
	public int getCount() {
		return list_linearLayout.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list_linearLayout.get(position));
		return list_linearLayout.get(position);
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list_linearLayout.get(position));
	}
}
