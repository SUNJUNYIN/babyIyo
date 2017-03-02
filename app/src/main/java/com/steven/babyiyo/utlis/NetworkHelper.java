package com.steven.babyiyo.utlis;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {
	private static ConnectivityManager connManager = null;

	/**
	 * 网络是否已经连接
	 * 
	 * @return true, 可用�?false�?不可�?
	 */
	public static boolean isNetworkConnected(Context context) {
		if (isNetworkAvailable(context)) {
			int type = getConnectionType(context);
			if (type == 0 || type == 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 对网络连接是否可�?
	 * 
	 * @return true, 可用�?false�?不可�?
	 */
	public static boolean isNetworkAvailable(Context context) {
		if (context != null) {
			connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				return networkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 对wifi连接状�?进行判断
	 * 
	 * @return true, 可用�?false�?不可�?
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifiInfo = connManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifiInfo != null) {
				return wifiInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 对MOBILE网络连接状�?进行判断
	 * 
	 * @return true, 可用�?false�?不可�?
	 */
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mobileInfo = connManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mobileInfo != null) {
				return mobileInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 获取当前网络连接的类型信�?
	 * 
	 * @return one of TYPE_MOBILE, TYPE_WIFI, TYPE_WIMAX, TYPE_ETHERNET,
	 *         TYPE_BLUETOOTH, or other types defined by ConnectivityManager
	 *         int值分别为�?�?�?�?�?
	 */
	public static int getConnectionType(Context context) {
		if (context != null) {
			connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isAvailable()) {
				return networkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 提示设置网络连接
	 * 
	 */
	public static void alertSetNetwork(final Context context) {
		Builder builder = new Builder(context);
		builder.setTitle("提示：网络异常").setMessage("是否对网络进行设置？");

		builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = null;
				try {
					int sdkVersion = android.os.Build.VERSION.SDK_INT;
					if (sdkVersion > 10) {
						intent = new Intent(
								android.provider.Settings.ACTION_WIRELESS_SETTINGS);
					} else {
						intent = new Intent();
						ComponentName comp = new ComponentName(
								"com.android.settings",
								"com.android.settings.WirelessSettings");
						intent.setComponent(comp);
						intent.setAction("android.intent.action.VIEW");
					}
					context.startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.show();
	}

	public static void destroy() {
		if (connManager != null) {
			connManager = null;
		}
	}
}
