package com.steven.babyiyo;

import android.app.Application;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.xutils.x;

import cn.bmob.v3.Bmob;

public class MyApp extends Application {
	public static String CITY;
	public static String APPID = "4d0b207b9731b474694cbfdc2bf5fbd4";
	@Override
	public void onCreate() {
		super.onCreate();
	initXutlis();
		initBmob();
		initLocation();
	}

	private void initBmob() {
		Bmob.initialize(this, APPID);
	}

	private void initXutlis() {
			x.Ext.init(this);
		}
	private void initLocation(){
         Log.i("===来到了定位","sadasdsadsadsadsa");
		AMapLocationClient mlocationClient = new AMapLocationClient(getApplicationContext());
		AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(new AMapLocationListener() {
				@Override
				public void onLocationChanged(AMapLocation amapLocation) {

					Log.i("===定位信息","地址："+amapLocation.getAddress()+"省"+amapLocation.getProvince()+"城市"+amapLocation.getCity()+
							"地区"+amapLocation.getDistrict()+"街道"+amapLocation.getStreet());
					if (amapLocation != null) {
						if (amapLocation.getErrorCode() == 0) {
							CITY = amapLocation.getCity();//城市信息
							Log.i("===定位信息","地址："+amapLocation.getAddress()+"省"+amapLocation.getProvince()+"城市"+amapLocation.getCity()+
							"地区"+amapLocation.getDistrict()+"街道"+amapLocation.getStreet());

//可在其中解析amapLocation获取相应内容。
						}else {
							//定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
							Log.e("AmapError","location Error, ErrCode:"
									+ amapLocation.getErrorCode() + ", errInfo:"
									+ amapLocation.getErrorInfo());
						}
					}
				}
			});

			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
		   // mLocationOption.setOnceLocation(true);
		    mLocationOption.isNeedAddress();
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			mlocationClient.startLocation();




	}



}
