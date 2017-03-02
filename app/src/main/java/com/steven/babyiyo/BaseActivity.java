package com.steven.babyiyo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.steven.babyiyo.receiver.NetworkStateReceiver;

@SuppressLint({ "ResourceAsColor", "NewApi" })
public class BaseActivity extends FragmentActivity {
	private Context mContext = this;
	private NetworkStateReceiver networkStateReceiver;
	private long preTime;
	

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		networkStateReceiver = new NetworkStateReceiver();
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(networkStateReceiver, new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION));
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (networkStateReceiver != null) {
			unregisterReceiver(networkStateReceiver);
		}
	}

	// 双击返回键�?�?/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (System.currentTimeMillis() - this.preTime > 2000) {
				
				finish();
			} else {
				finish();
				//System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_UP) {
			this.preTime = System.currentTimeMillis();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	Toast mToast;

	public void showToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}
	/**
	 * 开启activity
	 */
	public static void launchActivity(Context context, Class<?> activity) {
		Intent intent = new Intent(context, activity);
		context.startActivity(intent);
	}

	
}
