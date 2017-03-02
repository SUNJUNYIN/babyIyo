package com.steven.babyiyo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.steven.babyiyo.adapter.GuidePagerAdapter;
import com.steven.babyiyo.bean.MyUser;
import com.steven.babyiyo.component.EditTextWithDel;
import com.steven.babyiyo.component.PaperButton;
import com.steven.babyiyo.utlis.ScreenMetrics;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

@ContentView(R.layout.activity_username)
public class UserNameActivity extends Activity {
	@ViewInject(R.id.openbt)
	PaperButton openbt;
	@ViewInject(R.id.user)
	EditTextWithDel username;
	String phone;

	private GoogleApiClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		initView();

	}


	private void initView() {
		SharedPreferences userinfo = getSharedPreferences("userinfo", 0);
		phone = userinfo.getString("username", null);
		openbt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final String name = username.getText().toString();
				BmobQuery<MyUser> query = new BmobQuery<MyUser>();
				query.addWhereEqualTo("mobilePhoneNumber", phone);
				query.findObjects(getApplicationContext(), new FindListener<MyUser>() {
					@Override
					public void onSuccess(List<MyUser> object) {
						MyUser myUser = object.get(0);
						myUser.setUsername(name);
						myUser.update(getApplicationContext(), new UpdateListener() {
							@Override
							public void onSuccess() {

							}

							@Override
							public void onFailure(int i, String s) {

							}
						});


					}

					@Override
					public void onError(int code, String msg) {
						// TODO Auto-generated method stub

					}
				});


				startActivity(new Intent(getApplicationContext(), HomeActivity.class));

			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}
}
