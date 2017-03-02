package com.steven.babyiyo.bean;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Hotel extends BmobObject {
	private BmobFile myImage;


	public BmobFile getmyImage() {
		return myImage;
	}
	public void setmyImage(BmobFile myImage) {
		this.myImage = myImage;
	}
	
}
