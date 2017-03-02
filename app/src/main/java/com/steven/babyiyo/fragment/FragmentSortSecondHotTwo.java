package com.steven.babyiyo.fragment;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;


public class FragmentSortSecondHotTwo extends Fragment {


	private View view;
	private ImageView ivs;
	private ViewPager viewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_sortsecondtwo, container, false);

		setupViewPager();
		return view;
	}


	private void setupViewPager() {
		viewPager = (ViewPager) view.findViewById(R.id.second_viewpagertwo);
		setupViewPager(viewPager);
		TabLayout tabLayout = (TabLayout) view.findViewById(R.id.second_tabstwo);
		ivs= (ImageView) view.findViewById(R.id.header);
		Log.i("===","这里是");


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
		ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
		adapter.addFrag(new TabFragment(), "最新");
		adapter.addFrag(new TabFragmentnew(), "最热");
		viewPager.setAdapter(adapter);
	}

	static class ViewPagerAdapter extends FragmentPagerAdapter {

		private final List<Fragment> mFragmentList = new ArrayList<>();
		private final List<String> mFragmentTitleList = new ArrayList<>();

		public ViewPagerAdapter(FragmentManager manager) {
			super(manager);
		}

		@Override
		public Fragment getItem(int position) {
			return mFragmentList.get(position);
		}

		@Override
		public int getCount() {
			return mFragmentList.size();
		}

		public void addFrag(Fragment fragment, String title) {
			mFragmentList.add(fragment);
			mFragmentTitleList.add(title);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mFragmentTitleList.get(position);
		}
	}

}
