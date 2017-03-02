package com.steven.babyiyo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.steven.babyiyo.R;

public class WelcomeActivity extends BaseActivity {
	private ImageView image;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:

				SharedPreferences sharedPreferences = getSharedPreferences("config",
						MODE_PRIVATE);
				boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
				if (isFirst) {
					startActivity(new Intent(getApplicationContext(), GuideActivity.class));
					sharedPreferences.edit().putBoolean("isFirst", false).commit();
				} else {
					startActivity(new Intent(getApplicationContext(), LoginActivity.class));
					//startActivity(new Intent(getApplicationContext(), HomeActivity.class));
				}
				finish();
				overridePendingTransition(R.anim.anim_activity_in,
						R.anim.anim_activity_out);
				break;

			}
		};
	};
	private ImageView welcome_logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		
	}


	private void initView() {
		image = (ImageView) findViewById(R.id.welcome_Image);
		image.setBackgroundResource(R.drawable.logo);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.animset);
		welcome_logo = (ImageView) findViewById(R.id.welcome_Image);
		welcome_logo.setBackgroundResource(R.drawable.ic_launcher);
		welcome_logo.setAnimation(animation);
		nextActivity();
	}
	private void nextActivity() {

		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(0);
			}
		}.start();
		
	}
}
