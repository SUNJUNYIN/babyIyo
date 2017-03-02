package com.steven.babyiyo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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


@ContentView(R.layout.activity_business)
public class BusinessActivity extends Activity {
	@ViewInject(R.id.main_scrollview)
	PullToZoomScrollView pullscroview;
	private View zoomView;
	private ArrayList<ImageView> viewpagerList;
	private LinearLayout pointGroup;
	private MyHeaderPageAdapter headAdapter;
	private ViewPager viewpager;
	private int lastPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		initHeader();
		initViewpager();
		setChangeListener();



	}

	@Override
	public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

		return super.onCreateView(parent, name, context, attrs);
	}
	public void initViewpager() {
		//=======这里是viewpager的图片
		BmobQuery<Businessvp> bmobQuery = new BmobQuery<Businessvp>();
		bmobQuery.findObjects(this,
				new FindListener<Businessvp>() {
					@Override
					public void onSuccess(final List<Businessvp> list) {
						for (int i = 0; i < list.size(); i++) {
							initVp(list);


						}
					}

					@Override
					public void onError(int arg0, String arg1) {
						Log.i("====图片地址错误174", arg1 + "arg0" + arg0);


					}
				});}

	private void initHeader() {
		View headView = LayoutInflater.from(this).inflate(R.layout.goods_header, null, false);
		viewpager =(ViewPager)headView.findViewById(R.id.viewpager);
		pointGroup = (LinearLayout)headView.findViewById(R.id.linear_point_mian);
		//View headView = LayoutInflater.from(this).inflate(R.layout.fgmy_header, null, false);
		zoomView = LayoutInflater.from(this).inflate(R.layout.fgmy_back, null, false);
		View contentView = LayoutInflater.from(this).inflate(R.layout.fgmy_content, null, false);
		ImageView iv_zoom = (ImageView)zoomView.findViewById(R.id.iv_zoom);
		pullscroview.setHeaderView(headView);
		pullscroview.setZoomView(zoomView);
		pullscroview.setScrollContentView(contentView);
		//pullscroview.setHeaderViewSize(ScreenMetrics.getWidth(this, 1), ScreenMetrics.getHeight(this, 3));
		LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, ScreenMetrics.getHeight(this, 3));
		pullscroview.setHeaderLayoutParams(localObject);

	}

	public void initViewPagerScroll(ViewPager viewPager) {
		try {
			Field mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			ViewPagerScroller scroller = new ViewPagerScroller(this);
			mScroller.set( viewPager, scroller);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//初始化上方viewpager图片切换
	private void initVp(List<Businessvp> list) {
		viewpagerList = new ArrayList<>();
		for (int i = 0; i <list.size(); i++) {
			//初始化图片资源
			zoomView = LayoutInflater.from(this).inflate(R.layout.fgmy_back, null, false);
			ImageView image = (ImageView)zoomView.findViewById(R.id.iv_zoom);
			image.setId(0x7f0c026+i);
			image.setTag(i);
			//商家轮播点击
			image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int j = (int) v.getTag();
				}
			});
			Log.i("===轮播图的地址",list.get(i).getimage().getFileUrl(this));
			Tools.saveImageUrl(this, image, list.get(i).getimage().getFileUrl(this));
			/*ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			image.setLayoutParams(layoutParams);
			image.setScaleType(ImageView.ScaleType.FIT_XY);*/
			UrlToList.urlDisplayImage(image,this);
			//image.setImageResource(imageList[i]);
			viewpagerList.add(image);
			// 添加指示点
			ImageView point = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.rightMargin = 20;
			point.setLayoutParams(params);
			point.setBackgroundResource(R.drawable.point_bg);
			if (i == 0) {
				point.setEnabled(true);
			} else {
				point.setEnabled(false);
			}
			pointGroup.addView(point);
		}
		headAdapter = new MyHeaderPageAdapter(viewpagerList);
		viewpager.setAdapter(headAdapter);

	}

	private void setChangeListener() {
		viewpager.setPageTransformer(true,new DepthPageTransformer());
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


			@Override
			public void onPageSelected(int position) {

				position = position%viewpagerList.size();



				//改变指示点的状态
				//把当前点enbale 为true
				pointGroup.getChildAt(position).setEnabled(true);
				//把上一个点设为false
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				lastPosition = position;

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
									   int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}


}
