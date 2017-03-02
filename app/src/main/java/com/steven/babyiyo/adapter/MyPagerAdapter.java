package com.steven.babyiyo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter{
	private ArrayList<Fragment> listFragments;

	public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
		super(fm);
		listFragments = al;
	}

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return listFragments.get(position);
	}

	@Override
	public int getCount() {
		return listFragments.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

	}
}
