package com.steven.babyiyo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.steven.babyiyo.utlis.NetworkHelper;

public class NetworkStateReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
			if (!(NetworkHelper.isNetworkConnected(context))) {
				Toast.makeText(context, "无网络连接", Toast.LENGTH_LONG).show();
			}
		}
	}

}
