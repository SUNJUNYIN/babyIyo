package com.steven.babyiyo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.steven.babyiyo.adapter.MyHeaderPageAdapter;
import com.steven.babyiyo.bean.Businessvp;
import com.steven.babyiyo.bean.Pageonelv;
import com.steven.babyiyo.component.PullToZoomBase;
import com.steven.babyiyo.component.PullToZoomScrollView;
import com.steven.babyiyo.utlis.ScreenMetrics;
import com.steven.babyiyo.utlis.Tools;
import com.steven.babyiyo.utlis.UrlToList;

import org.xutils.view.annotation.Event;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;


public class ActivityGoods extends BaseActivity implements View.OnClickListener{

	private ArrayList<ImageView> viewpagerList;
	private ViewPager pager_header;
	private LinearLayout pointGroup;
	private int lastPosition;
	private int positions;
	private MyHeaderPageAdapter adapter2;
	private Context context;
	private String objectId;
	private TextView businestitle;
	private TextView price;
	private TextView brandname;
	private TextView address;
	private TextView proprice;
	private ImageView head_back;
	private LinearLayout buynow;
	private Businessvp businessvp;
	private String imageurl;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;
	private LinearLayout service;
	private LinearLayout tomap;
	private LinearLayout callphone;
	private ImageView iv_back;
	private boolean isChange;
	private ImageView iv_share;
	private ImageView iv_love;
	private LinearLayout text_linear;
	private ImageView iv_menu;
	private LinearLayout menu_linear;
	private LinearLayout tell_linear;
	private LinearLayout linea_back;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shigoods);

		context = this;
		objectId = getIntent().getStringExtra("objectId");
		imageurl = getIntent().getStringExtra("imageurl");
		initView();
		initViewpager();
		//setChangeListener();

	}

	private void initViewpager() {
		viewpagerList = new ArrayList<ImageView>();
		pager_header = (ViewPager) findViewById(R.id.viewpager);

		pointGroup = (LinearLayout) findViewById(R.id.linear_point_mian);
		//=======这里是viewpager的图片
		BmobQuery<Businessvp> bmobQuery = new BmobQuery<Businessvp>();
		Pageonelv pageonelv = new Pageonelv(); //
		// 跳转activity得到的i值，代表了goods表中第几行的商品
		pageonelv.setObjectId(objectId);
		//看来是定位到商品的关联下一级，目前展示的是商品详情的图片
		bmobQuery.addWhereRelatedTo("point", new BmobPointer(pageonelv));
		bmobQuery.findObjects(this,
				new FindListener<Businessvp>() {
					@Override
					public void onSuccess(final List<Businessvp> list) {
						for (int i = 0; i < list.size(); i++) {
							ArrayList<List<String>> lists = UrlToList.bmobobjToStringList(list, context);
							List<String> urllist = lists.get(0);

							for (int j = 0; j < urllist.size(); j++) {
								View zoomView = LayoutInflater.from(
										getApplicationContext()).inflate(
										R.layout.goods_zoom_view, null,
										false);
								ImageView iv_zoom = (ImageView) zoomView
										.findViewById(R.id.iv_zoom);
								iv_zoom.setId(0x7f0c026 + j);
								iv_zoom.setTag(j);
								Tools.saveImageUrl(context, iv_zoom, urllist.get(j));
								UrlToList.urlDisplayImages(iv_zoom, context);
								viewpagerList.add(iv_zoom);

								//====初始化点
								ImageView point = new ImageView(
										getApplicationContext());
								LayoutParams params = new LayoutParams(
										LayoutParams.WRAP_CONTENT,
										LayoutParams.WRAP_CONTENT);

								params.rightMargin = 20;
								point.setLayoutParams(params);

								point.setBackgroundResource(R.drawable.point_bg);
								if (j == 0) {
									point.setEnabled(true);
								} else {
									point.setEnabled(false);
								}
								pointGroup.addView(point);
							}


						}
						adapter2 = new MyHeaderPageAdapter(
								viewpagerList);
						pager_header.setAdapter(adapter2);
						setChangeListener();
						//viewpager下面的价格图片等信息
						businessvp = list.get(0);
						businestitle.setText(businessvp.getTitle());
						price.setText(businessvp.getPrice());
						address.setText(businessvp.getAddress());
						brandname.setText(businessvp.getBrand());
						String reson  = businessvp.getReason();
						String menu  = businessvp.getMenu();
						String matters  = businessvp.getMatters();
						subString(reson,text_linear);
						subString(menu,menu_linear);
						subString(matters,tell_linear);
						Tools.saveImageUrl(context,iv_menu,businessvp.getMenuimage().getFileUrl(context));
						UrlToList.urlDisplayImage(iv_menu,context);


					}

					@Override
					public void onError(int arg0, String arg1) {
						Log.i("====图片地址错误174", arg1 + "arg0" + arg0);
					}
				});


		// ��̬���ÿ��
		// ScreenMetrics.setHeight(this, pager_header, 4);

	}
	public void subString (String string,LinearLayout layout){
		boolean contains = string.contains("/");
		if(contains){
		String [] a= string.split("/");

			for (int i = 0; i < a.length; i++) {
				TextView textView = new TextView(this);
				textView.setText(a[i]);
				textView.setGravity(Gravity.CENTER);
				textView.setPadding(20, 10, 20, 10);
				textView.setTextSize(13);
				layout.addView(textView);

			}

			}



	}


	private void setChangeListener() {
		pager_header.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				positions = position % viewpagerList.size();


				pointGroup.getChildAt(positions).setEnabled(true);
				pointGroup.getChildAt(lastPosition).setEnabled(false);
				lastPosition = positions;

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




	private void initView() {
		linea_back =(LinearLayout) findViewById(R.id.linea_back);
		linea_back.setOnClickListener(this);
		service = (LinearLayout) findViewById(R.id.service);
		service.setOnClickListener(this );
		buynow = (LinearLayout) findViewById(R.id.buynow);
		buynow.setOnClickListener(this );
		head_back = (ImageView) findViewById(R.id.head_back);
		head_back.setVisibility(View.INVISIBLE);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_share = (ImageView) findViewById(R.id.iv_share);
		iv_love = (ImageView) findViewById(R.id.iv_love);
		head_back.setAlpha(0);
		PullToZoomScrollView scrollView = (PullToZoomScrollView) findViewById(R.id.slid_scrollview);
		scrollView.setOnScrollViewChangedListener(new PullToZoomScrollView.OnScrollViewChangedListener() {
			@Override
			public void onInternalScrollChanged(int left, int top, int oldLeft, int oldTop) {
				head_back.setVisibility(View.VISIBLE);
				float v = Float.parseFloat("" + top);
				float num = v / 200;
				Log.i("===goods的变化", num + "");
				head_back.setAlpha(num);
				if (v>60){
					isChange = false;
				}else{
					isChange = true;
				}
				if (isChange){
					iv_back.setImageResource(R.drawable.backwhite);
					iv_share.setImageResource(R.drawable.sharewhite);
					iv_love.setImageResource(R.drawable.xinwhite);

				}else {
					iv_back.setImageResource(R.drawable.backblack);
					iv_share.setImageResource(R.drawable.shareblack);
					iv_love.setImageResource(R.drawable.xinblack);

				}

			}
		});
		View headView = LayoutInflater.from(this).inflate(
				R.layout.goods_header, null, false);
		final View fgmy_contenttwo = LayoutInflater.from(this).inflate(
				R.layout.goods_contenttwo, null, false);
		View zoomView = LayoutInflater.from(this).inflate(
				R.layout.goods_zoom_view, null, false);
		ImageView iv_zoom = (ImageView) zoomView.findViewById(R.id.iv_zoom);
		// iv_zoom.setScaleType(ScaleType.CENTER);
		ViewPager pager = (ViewPager) headView.findViewById(R.id.viewpager);
		View contentView = LayoutInflater.from(this).inflate(
				R.layout.goods_content, null, false);
		text_linear = (LinearLayout) fgmy_contenttwo.findViewById(R.id.text_linear);
		menu_linear = (LinearLayout) fgmy_contenttwo.findViewById(R.id.menu_linear);
		tell_linear = (LinearLayout) fgmy_contenttwo.findViewById(R.id.tell_linear);
		iv_menu = (ImageView) fgmy_contenttwo.findViewById(R.id.iv_menu);

		businestitle = (TextView) contentView.findViewById(R.id.protitle);
		tomap = (LinearLayout) contentView.findViewById(R.id.tomap);
		tomap.setOnClickListener(this);
		callphone = (LinearLayout) contentView.findViewById(R.id.callphone);
		callphone.setOnClickListener(this);
		price = (TextView) contentView.findViewById(R.id.businessprice);
		proprice = (TextView) contentView.findViewById(R.id.proprice);
		address = (TextView) contentView.findViewById(R.id.businessaddress);
		brandname = (TextView) contentView.findViewById(R.id.brandname);
		final LinearLayout addcontentwo = (LinearLayout) contentView
				.findViewById(R.id.addcontentwo);
		Animation animation = AnimationUtils.loadAnimation(this,
				R.anim.goodspop_down_out);

		contentView.startAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationEnd(Animation animation) {

				addcontentwo.addView(fgmy_contenttwo);
			}
		});

		scrollView.setHeaderView(headView);
		scrollView.setZoomView(zoomView);
		scrollView.setScrollContentView(contentView);
		scrollView.setZoomEnabled(true);
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		int mScreenHeight = localDisplayMetrics.heightPixels;
		int mScreenWidth = localDisplayMetrics.widthPixels;
		LayoutParams localObject = new LayoutParams(
				mScreenWidth, ScreenMetrics.getHeights(context, 12, 5));
		scrollView.setHeaderLayoutParams(localObject);
	}

	@Override
	public void onStart() {
		super.onStart();


		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"ActivityGoods Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://com.steven.babyiyo/http/host/path")
		);
		AppIndex.AppIndexApi.start(client, viewAction);
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		Action viewAction = Action.newAction(
				Action.TYPE_VIEW, // TODO: choose an action type.
				"ActivityGoods Page", // TODO: Define a title for the content shown.
				// TODO: If you have web page content that matches this app activity's content,
				// make sure this auto-generated web page URL is correct.
				// Otherwise, set the URL to null.
				Uri.parse("http://host/path"),
				// TODO: Make sure this auto-generated app URL is correct.
				Uri.parse("android-app://com.steven.babyiyo/http/host/path")
		);
		AppIndex.AppIndexApi.end(client, viewAction);
		client.disconnect();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buynow:
				Intent intent = new Intent(this, OrderEditActivity.class);
				intent.putExtra("title", businessvp.getTitle());
				intent.putExtra("price", businessvp.getPrice());
				intent.putExtra("imageurl", imageurl);
				startActivity(intent);

				break;

			case R.id.linea_back      :
				finish();

				break;
			case R.id.tomap      :
				Intent intent2 = new Intent(this, MapActivity.class);
				startActivity(intent2);

				break;
			case R.id.callphone      :
				Intent intentcall = new Intent(Intent.ACTION_CALL,Uri.parse("tel:400-0571-373"));
				context.startActivity(intentcall);

				break;
		}

	}
}

