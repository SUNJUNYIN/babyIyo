package com.steven.babyiyo.utlis;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class ScreenMetrics {
	public static void setHeight(Context context, View view, int i) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int heightPixels = outMetrics.heightPixels;
		LayoutParams params = view.getLayoutParams();
		params.height = heightPixels / i;
		params.width = params.MATCH_PARENT;
		view.setLayoutParams(params);

	}
	public static void setWith(Context context, View view, int i,int j ) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int widthPixels = outMetrics.widthPixels;
		LayoutParams params = view.getLayoutParams();
		params.width =  widthPixels / i*j;
		view.setLayoutParams(params);

	}
	
	public static void setHeight(Context context, View view, int i,int j ) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int heightPixels = outMetrics.heightPixels;
		LayoutParams params = view.getLayoutParams();
		params.height =  heightPixels / i*j;
		view.setLayoutParams(params);

	}

	public static int getHeight(Context context,int i ) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int heightPixels = outMetrics.heightPixels/i;
		
		return heightPixels;

	}
	public static int getHeights(Context context,int i ,int j) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int heightPixels = outMetrics.heightPixels/i*j;

		return heightPixels;

	}
	public static int getWidth(Context context,int i ) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int widthPixels = outMetrics.widthPixels/i;
		
		return widthPixels;

	}
	public static int getWidths(Context context,int i ,int j) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		int widthPixels = outMetrics.widthPixels/i*j;
		
		return widthPixels;

	}

	public static int dip2px(Context context, float dpValue) {  
	  final float scale = context.getResources().getDisplayMetrics().density;  
	  return (int) (dpValue * scale + 0.5f);  
	}  

	public static int px2dip(Context context, float pxValue) {  
	  final float scale = context.getResources().getDisplayMetrics().density;  
	  return (int) (pxValue / scale + 0.5f);  
	}   

}
