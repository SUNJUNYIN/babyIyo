package com.steven.babyiyo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MarkerOptions;
import com.steven.babyiyo.adapter.MyHeaderPageAdapter;
import com.steven.babyiyo.bean.Businessvp;
import com.steven.babyiyo.component.DepthPageTransformer;
import com.steven.babyiyo.component.PullToZoomScrollView;
import com.steven.babyiyo.component.ViewPagerScroller;
import com.steven.babyiyo.utlis.ScreenMetrics;
import com.steven.babyiyo.utlis.Tools;
import com.steven.babyiyo.utlis.UrlToList;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


public class MapActivity extends Activity {
	private MapView mapView;
	private AMap aMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
	}

	/**
	 * 对地图添加一个marker
	 */
	private void setUpMap() {
	//	aMap.addMarker(new MarkerOptions().position(SyncStateContract.Constants.FANGHENG)
		//		.title("方恒").snippet("方恒国际中心大楼A座"));
	}
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}




}
