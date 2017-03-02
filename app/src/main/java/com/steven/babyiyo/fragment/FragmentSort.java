package com.steven.babyiyo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.steven.babyiyo.R;
import com.steven.babyiyo.adapter.TabViewPagerAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_sort)
public class FragmentSort extends Fragment {
	@ViewInject(R.id.collapse_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@ViewInject(R.id.viewpagersort)
	ViewPager viewPager;
	@ViewInject(R.id.tabs)
	TabLayout tabLayout;
	@ViewInject(R.id.header)
	ImageView ivs;
	@ViewInject(R.id.toolbar )
	Toolbar toolbar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return x.view().inject(this,inflater,container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setupToolbar();
		setupViewPager();
		setupCollapsingToolbar();
	}

	private void setupCollapsingToolbar() {
		collapsingToolbar.setTitleEnabled(false);
	}

	private void setupViewPager() {

		setupViewPager(viewPager);
		Log.i("===","这里是");
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
			final 	int f=tab.getPosition();
				Log.i("===","这里是"+f);
				if (f==0){
					ivs.setBackgroundResource(R.drawable.photo);
				}else{
					ivs.setBackgroundResource(R.drawable.p1);
				}
				viewPager.setCurrentItem(f);


			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				int f=tab.getPosition();
				Log.i("===","这里是"+f);
				if (f==0){
					ivs.setBackgroundResource(R.drawable.photo);
				}else{
					ivs.setBackgroundResource(R.drawable.p1);
				}
			}
		});

	}

	private void setupToolbar() {
		toolbar.setTitle("yueizi");


	}

	private void setupViewPager(ViewPager viewPager) {
		TabViewPagerAdapter adapter = new TabViewPagerAdapter(getActivity().getSupportFragmentManager());
		adapter.addFrag(new FragmentSortSecondHot(), "上海");
		adapter.addFrag(new FragmentSortSecondHotTwo(), "杭州");
		viewPager.setAdapter(adapter);
	}



}
