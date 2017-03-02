package com.steven.babyiyo.utlis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static void saveUserInfo(Context context,String name,String username,String password){
		SharedPreferences sharedPreferences =context.getSharedPreferences(name, 0);
		sharedPreferences.edit().putString("username",username).commit();
		sharedPreferences.edit().putString("password",password).commit();

	}
	//抖动动画CycleTimes动画重复的次数
	public static Animation shakeAnimation(int CycleTimes) {
		Animation translateAnimation = new TranslateAnimation(0, 6, 0, 6);
		translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
		translateAnimation.setDuration(1000);
		return translateAnimation;
	}
	public static void launchActivity(Context context, Class<?> activity) {
		Intent intent = new Intent(context, activity);
		context.startActivity(intent);
	}
	public  static void  saveImageUrl(Context context, ImageView key, String value){
		SharedPreferences imageurl = context.getSharedPreferences("imageurl", 0);
		imageurl.edit().putString(key.getId()+"",value).commit();

	}
	public static String  findImageUrl(Context context,ImageView key){
		SharedPreferences imageurl = context.getSharedPreferences("imageurl", 0);
		String string = imageurl.getString(key.getId()+"", null);
		return string;


	}

}
