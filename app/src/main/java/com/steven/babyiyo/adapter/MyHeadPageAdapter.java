package com.steven.babyiyo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyHeadPageAdapter extends PagerAdapter {
	private List<ImageView> list;

	public MyHeadPageAdapter(List<ImageView> list) {
		this.list = list;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

	/*	ViewGroup parent = (ViewGroup) list.get(position % list.size())
				.getParent();

		if (parent != null) {
			parent.removeView(list.get(position % list.size()));
		}
		ImageView view = list.get(position % list.size());
		System.out.println("==适配器传过来的list" + list);
		container.addView(view);*/
		return  list.get(position % list.size());
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

	}

}
