package com.steven.babyiyo.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Tabshouye extends BmobObject {

	private BmobFile Himage;
	private String imagename;

	public BmobFile getHimage() {
		return Himage;
	}

	public void setHimage(BmobFile himage) {
		Himage = himage;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
}
