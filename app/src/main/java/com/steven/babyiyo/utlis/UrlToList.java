package com.steven.babyiyo.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;

import com.steven.babyiyo.R;
import com.steven.babyiyo.bean.Businessvp;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;

public class UrlToList {
	private static String urlstring;
	private static String imageCachePath;
	private  static boolean exists;
	// ==========url集合imagelist集合，viewpager使用
	public static List<ImageView> urlToImageList(
			List<Map<String, String>> list, final Context context) {
		ArrayList<ImageView> imagelist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			// 动态加载imageview
			ImageView imageView = new ImageView(context);
			imageView.setTag(i);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			imageView.setLayoutParams(layoutParams);
			imageView.setScaleType(ScaleType.FIT_XY);
			String discCachePath = context.getExternalCacheDir()
					.getAbsolutePath();
			ImageOptions build = new ImageOptions.Builder().
					setFadeIn(true).setConfig(Config.RGB_565)
					.setForceLoadingDrawable(true)
					.setLoadingDrawableId(R.drawable.homeback)
					.setImageScaleType(ScaleType.FIT_XY)
					.setUseMemCache(true)
					.build();
			x.image().bind(imageView, list.get(i).get("url"), build);
			imagelist.add(imageView);
		}
		return imagelist;

	}

	public static String  findImageUrl(Context context,ImageView key){
		SharedPreferences imageurl = context.getSharedPreferences("imageurl", 0);
		String string = imageurl.getString(key.getId()+"", null);
		return string;


	}
	// ==============根据url给控件设置图片
	public static void urlDisplayImage( ImageView image,Context context) {
		String imageUrl = findImageUrl(context, image);
		if (imageUrl != null) {

			ImageOptions build = new ImageOptions.Builder().
					setFadeIn(true).setConfig(Config.RGB_565)
					.setForceLoadingDrawable(true)
					.setLoadingDrawableId(R.drawable.homeback)
					.setImageScaleType(ScaleType.FIT_XY)
					.setUseMemCache(true)
					.build();
			x.image().bind(image, imageUrl, build);
		}
	}
		// ==============根据url给控件设置图片,加载成drawable再设置图片，应用于pullviewpager
	public static void urlDisplayImages( final ImageView image,Context context) {
		String imageUrl = findImageUrl(context, image);
		Log.i("==draw地址",imageUrl);
		if (imageUrl!=null) {

			ImageOptions build = new ImageOptions.Builder().
					setFadeIn(true).setConfig(Config.RGB_565)
					.setForceLoadingDrawable(true)
					.setLoadingDrawableId(R.drawable.homeback)
					.setImageScaleType(ScaleType.FIT_XY)
					.setUseMemCache(true)
					.build();
			x.image().loadDrawable(imageUrl, build, new Callback.CacheCallback<Drawable>() {
				@Override
				public boolean onCache(Drawable result) {
					Log.i("==onCache下载成功","xasa");
					image.setImageDrawable(result);
					return false;
				}

				@Override
				public void onSuccess(Drawable result) {
					Log.i("==下载成功","xasa");
					image.setImageDrawable(result);
				}



				@Override
				public void onError(Throwable ex, boolean isOnCallback) {
					Log.i("==下载成功","xasa"+ex.toString());
				}

				@Override
				public void onCancelled(CancelledException cex) {
					Log.i("==oonCancelled下载成功","xasa");

				}

				@Override
				public void onFinished() {
					Log.i("==onFinished","xasa");

				}
			});

		}
	/*	if (url.length()>0){
			image.setTag(url);
			//Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
			x.image().bind(image,url,build);
		}else{

			String tag = (String)image.getTag();
			Log.i("===图片的tag",tag+"");
			//Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
			x.image().bind(image,tag,build);

		}*/

/*

		String cacheBasePath = SDCardHelper
				.getSDCardPrivateCacheDir(context);
		String replace = url.replace(".", "");
		urlstring = replace.substring(url.length() - 20, replace.length());
		imageCachePath = cacheBasePath + File.separator + urlstring;
		if (image.getTag() != null) {
			exists = true;
			System.out.println("====图片tag" + image.getTag().toString());
		} else {

			exists = new File(imageCachePath).exists();
		}


		if (exists) {
			//	bitmap = SDCardHelper
			//		.loadBitmapFromSDCard(imageCachePath);
			System.out.println("==有本地加载" + imageCachePath);

			x.image().bind(image, (String) image.getTag(), build);
		} else {
			x.image().bind(image, url, build, new Callback.CommonCallback<Drawable>() {

				@Override
				public void onSuccess(Drawable arg0) {
					BitmapDrawable bd = (BitmapDrawable) arg0;
					Bitmap bitmap = bd.getBitmap();
					boolean saveBitmapToSDCardPrivateCacheDir = SDCardHelper.saveBitmapToSDCardPrivateCacheDir(bitmap, urlstring, context);
					System.out.println("===有没有保存成功网络加载" + saveBitmapToSDCardPrivateCacheDir+"家地址"+imageCachePath);
					image.setTag(imageCachePath);

				}

				@Override
				public void onFinished() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onError(Throwable arg0, boolean arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCancelled(CancelledException arg0) {
					// TODO Auto-generated method stub

				}
			});
*/

/*		String discCachePath = context.getExternalCacheDir().getAbsolutePath();
		BitmapUtils bitmapUtils = new BitmapUtils(context, discCachePath)
				.configMemoryCacheEnabled(true)
				.configDefaultBitmapMaxSize(400, 400)
				.configDefaultBitmapConfig(Config.RGB_565)
				.configDefaultLoadingImage(R.color.white)
				.configDefaultLoadFailedImage(R.color.white)
				.configThreadPoolSize(5);
		bitmapUtils.display(image, url);*/

		}
	// ===================商品详情recyclerview图片使用,这里面list里面存list是因为鞋子的颜色不同，一款颜色存在一个list里面
	public static ArrayList<List<String>> bmobobjToStringList(List<Businessvp> list,
															  Context context) {
		ArrayList<List<String>> arrayList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> arrayList2 = new ArrayList<>();
			Businessvp object = list.get(i);
			BmobFile image = object.getimage();
			BmobFile image1 = object.getImage1();
			BmobFile image2 = object.getImage2();
			BmobFile image3 = object.getImage3();
			BmobFile image4 = object.getImage4();
			BmobFile image5= object.getImage5();
			BmobFile image6 = object.getImage6();
			BmobFile image7 = object.getImage7();
			if (image!=null){
				String fileUrl = image.getFileUrl(context);
				arrayList2.add(fileUrl);}
			if (image1!=null){
				String fileUrl1 = image1.getFileUrl(context);
				arrayList2.add(fileUrl1);}
			if (image2!=null){
				String fileUrl2 = image2.getFileUrl(context);
				arrayList2.add(fileUrl2);}
			if (image3!=null){
				String fileUrl3 = image3.getFileUrl(context);
				arrayList2.add(fileUrl3);}
			if (image4!=null){
				String fileUrl4 = image4.getFileUrl(context);
				arrayList2.add(fileUrl4);}
			if (image5!=null){
				String fileUrl5 = image5.getFileUrl(context);
				arrayList2.add(fileUrl5);}
			if (image6!=null){
				String fileUrl6 = image6.getFileUrl(context);
				arrayList2.add(fileUrl6);}
			if (image7!=null){
				String fileUrl7 = image7.getFileUrl(context);
				arrayList2.add(fileUrl7);}



			arrayList.add(arrayList2);

		}
		return arrayList;

	}



}

