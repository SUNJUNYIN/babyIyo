package com.steven.babyiyo.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.steven.babyiyo.R;
import com.steven.babyiyo.bean.MyUser;
import com.steven.babyiyo.bean.Tabshouye;
import com.steven.babyiyo.utlis.Tools;
import com.steven.babyiyo.utlis.UrlToList;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@ContentView(R.layout.activity_scrolling)
public class FragmentHomepage extends BaseFragment  {
	@ViewInject(R.id.app_bar)
	AppBarLayout appBarLayout;
	@ViewInject(R.id.progressbar)
	ProgressBar progressBar;
	@ViewInject(R.id.toolbar)
	Toolbar toolbar;
	@ViewInject(R.id.home_content)
	LinearLayout home_content;
	@ViewInject(R.id.toolbar_layout)
	CollapsingToolbarLayout toolBarLayout;
	@ViewInject(R.id.fab)
	FloatingActionButton fab;
	@ViewInject(R.id.hello_user)
	TextView hello_user;
	@ViewInject(R.id.hotelone_ivone)
	ImageView hotelone_ivone;
	@ViewInject(R.id.hotelone_ivtwo)
	ImageView hotelone_ivtwo;
	@ViewInject(R.id.hotelone_ivthree)
	ImageView hotelone_ivthree;
	@ViewInject(R.id.hoteltwo_ivone)
	ImageView hoteltwo_ivone;
	@ViewInject(R.id.hoteltwo_ivtwo)
	ImageView hoteltwo_ivtwo;
	@ViewInject(R.id.hoteltwo_ivthree)
	ImageView hoteltwo_ivthree;
	@ViewInject(R.id.hotelthree_ivone)
	ImageView hotelthree_ivone;
	@ViewInject(R.id.hotelthree_ivtwo)
	ImageView hotelthree_ivtwo;
	@ViewInject(R.id.hotelthree_ivthree)
	ImageView hotelthree_ivthree;
	@ViewInject(R.id.home_scrollview)
	NestedScrollView home_scrollview;
	String phone;
	private Boolean isColose = false;
	private int flag = 0;
	private Float firstTouch;
	private Float secondTouch;


	private Handler handler =new Handler(){};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("====sa","home->onCreateView");
		return x.view().inject(this,inflater,container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		int i = Log.i("====sa", "home->onViewCreated");
		initContent();
		initView();
	}

/*


	@Override
	public void onResume() {
		super.onResume();
		Log.i("====sa","home->onResume");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("====sa","home->onDestroy");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.i("====sa","home->onStart");
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.i("====sa","home->onAttach");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.i("====sa","home->onDetach");
	}*/

	private void initView() {

		appBarLayout.setExpanded(false);
		progressBar.setVisibility(View.VISIBLE);
		home_content.setVisibility(View.INVISIBLE);
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				appBarLayout.setExpanded(true);
				Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.pop_up_in);
				home_content.setAnimation(animation);
				progressBar.setVisibility(View.GONE);
				home_content.setVisibility(View.VISIBLE);
			}
		},5000);
		//getActivity().setSupportActionBar(toolbar);
		toolBarLayout.setTitle("May 杭州");
		toolBarLayout.setExpandedTitleColor(getResources().getColor(R.color.bg_Black));
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
 //滑动自动拉上去
		home_scrollview.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						firstTouch = event.getY();
						Log.i("====第一次记录ACTION_DOWN",firstTouch+"");
						break;
					case MotionEvent.ACTION_UP:
						secondTouch = event.getY();
						if ((float)(secondTouch - firstTouch) > 0 && event.getY() < (float) 150) {
							Log.i("====第一次记录ACTION_UP",secondTouch+"");

							appBarLayout.setExpanded(false);
							flag = 0;
						}
						break;
				}

				return false;
			}
		});


	}
	public void initContent() {
		/*Slide slide = new Slide(Gravity.BOTTOM);
		slide.addTarget(R.id.text_detail);
		slide.addTarget(R.id.text_close);
		slide.addTarget(R.id.tec);
		slide.addTarget(R.id.view_separator);
		getWindow().setEnterTransition(slide);*/
		//=========用户名的名称获取
		//UrlToList.urlDisplayImage( "http://bmob-cdn-3171.b0.upaiyun.com/2016/07/20/03f4839a403b61f880fbdbc42cf73cff.jpg",hotelone_ivone,getActivity());

		Log.i("====当前的线程",Thread.currentThread()+"");
		SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", 0);
		phone = userinfo.getString("username", null);
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.addWhereEqualTo("mobilePhoneNumber", phone);
		query.findObjects(getActivity(), new FindListener<MyUser>() {
			@Override
			public void onSuccess(List<MyUser> object) {
				MyUser myUser = object.get(0);
				String username = myUser.getUsername();
				hello_user.setText(username+",你好");
			}

			@Override
			public void onError(int code, String msg) {
				// TODO Auto-generated method stub
			}
		});
		//==================第一家月子中心的信息
		BmobQuery<Tabshouye> bmobQuery = new BmobQuery<Tabshouye>();
		bmobQuery.findObjects(getActivity(),
				new FindListener<Tabshouye>() {

					@Override
					public void onSuccess( final List<Tabshouye> list) {
						Tools.saveImageUrl(getActivity(),hotelone_ivone,list.get(0).getHimage().getFileUrl(getActivity()));
						Tools.saveImageUrl(getActivity(),hotelone_ivtwo,list.get(1).getHimage().getFileUrl(getActivity()));
						Tools.saveImageUrl(getActivity(),hotelone_ivthree,list.get(2).getHimage().getFileUrl(getActivity()));
						Tools.saveImageUrl(getActivity(),hoteltwo_ivone,list.get(3).getHimage().getFileUrl(getActivity()));
						Tools.saveImageUrl(getActivity(),hoteltwo_ivtwo,list.get(4).getHimage().getFileUrl(getActivity()));
						Tools.saveImageUrl(getActivity(),hoteltwo_ivthree,list.get(5).getHimage().getFileUrl(getActivity()));
						//x.image().bind(hotelone_ivone,"http://bmob-cdn-3171.b0.upaiyun.com/2016/07/20/03f4839a403b61f880fbdbc42cf73cff.jpg");
						Log.i("====当前的线程",Thread.currentThread()+"");
					    //diaplayImage();

						handler.post(new Runnable() {
							@Override
							public void run() {

									displayImage();
							}
						});
					}
					@Override
					public void onError(int arg0, String arg1) {
						Log.i("====图片地址错误174",arg1+"arg0"+arg0);


					}
				});
/*		bmobQuery.getObject(getActivity(), "L1PjAAAB", new GetListener<Hotel>() {
			@Override
			public void onSuccess(Hotel object) {
				// TODO Auto-generated method stub
			//	toast("查询成功");

				Toast.makeText(getActivity(),object.getMyImage().getFileUrl(getActivity()),Toast.LENGTH_LONG).show();
			}

			@Override
			public void onFailure(int code, String msg) {
				Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
			}
		});*/
	   displayImage();

			}
	private void displayImage() {
			UrlToList.urlDisplayImage(hotelone_ivone, getActivity());
			UrlToList.urlDisplayImage(hotelone_ivtwo, getActivity());
			UrlToList.urlDisplayImage(hotelone_ivthree, getActivity());
		UrlToList.urlDisplayImage(hoteltwo_ivone, getActivity());
		UrlToList.urlDisplayImage(hoteltwo_ivtwo, getActivity());
		UrlToList.urlDisplayImage(hoteltwo_ivthree, getActivity());

	}
}
