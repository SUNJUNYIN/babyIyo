package com.steven.babyiyo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup.LayoutParams;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steven.babyiyo.MyApp;
import com.steven.babyiyo.R;
import com.steven.babyiyo.adapter.MyHeaderPageAdapter;
import com.steven.babyiyo.adapter.TabViewPagerAdapter;
import com.steven.babyiyo.component.ViewPagerScroller;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Field;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_zixun)
public class FragmentCar extends BaseFragment{
	@ViewInject(R.id.second_viewpagers)
	ViewPager viewPager;
	@ViewInject(R.id.second_tabs)
	TabLayout tabLayout;
	@ViewInject(R.id.pointcity)
	TextView pointcity;






	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return x.view().inject(this,inflater,container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		setupViewPager();

	}

	//设置tab下的viewpager
	private void setupViewPager() {
		pointcity.setText(MyApp.CITY);


		setupViewPager(viewPager);
		tabLayout.setupWithViewPager(viewPager);
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				final 	int f=tab.getPosition();
				viewPager.setCurrentItem(f);
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}
			@Override
			public void onTabReselected(TabLayout.Tab tab) {


			}
		});

	}


	private void setupViewPager(ViewPager viewPager) {
		TabViewPagerAdapter adapter = new TabViewPagerAdapter(getActivity().getSupportFragmentManager());
		adapter.addFrag(new FragmentImage(), "美食");
		adapter.addFrag(new FragmentImage(), "玩乐");//fragmentfour
		adapter.addFrag(new FragmentImage(), "生活学院");
		viewPager.setAdapter(adapter);
	}





}
