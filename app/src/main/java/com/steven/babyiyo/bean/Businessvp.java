package com.steven.babyiyo.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Businessvp extends BmobObject {

	private BmobFile image;
	private BmobFile image1;
	private BmobFile image2;
	private BmobFile image3;
	private BmobFile image4;
	private BmobFile image5;
	private BmobFile image6;
	private BmobFile image7;
	private BmobFile menuimage;
	private String title;
	private String price;
	private String matters;

	public String getMatters() {
		return matters;
	}

	public void setMatters(String matters) {
		this.matters = matters;
	}

	private String brand;
	private String address;
	private String reason;
	private String menu;

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public BmobFile getimage() {
		return image;
	}

	public BmobFile getImage1() {
		return image1;
	}

	public void setImage1(BmobFile image1) {
		this.image1 = image1;
	}

	public BmobFile getImage2() {
		return image2;
	}

	public void setImage2(BmobFile image2) {
		this.image2 = image2;
	}

	public BmobFile getImage3() {
		return image3;
	}

	public void setImage3(BmobFile image3) {
		this.image3 = image3;
	}

	public BmobFile getImage4() {
		return image4;
	}

	public void setImage4(BmobFile image4) {
		this.image4 = image4;
	}

	public BmobFile getImage5() {
		return image5;
	}

	public void setImage5(BmobFile image5) {
		this.image5 = image5;
	}

	public BmobFile getImage6() {
		return image6;
	}

	public void setImage6(BmobFile image6) {
		this.image6 = image6;
	}

	public BmobFile getImage7() {
		return image7;
	}

	public void setImage7(BmobFile image7) {
		this.image7 = image7;
	}

	public BmobFile getMenuimage() {
		return menuimage;
	}

	public void setMenuimage(BmobFile menuimage) {
		this.menuimage = menuimage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setimage(BmobFile image) {
		image = image;

	}

}
