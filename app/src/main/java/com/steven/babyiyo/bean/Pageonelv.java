package com.steven.babyiyo.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Pageonelv extends BmobObject {

	private BmobFile lvimage;
	private String lvprice;
	private String addres;
	private String lvtitle;

	public String getLvtitle() {
		return lvtitle;
	}

	public void setLvtitle(String lvtitle) {
		this.lvtitle = lvtitle;
	}

	public String getLvprice() {
		return lvprice;
	}

	public void setLvprice(String lvprice) {
		this.lvprice = lvprice;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public BmobFile getlvimage() {
		return lvimage;
	}

	public void setimage(BmobFile lvimage) {
		lvimage = lvimage;
	}

}
