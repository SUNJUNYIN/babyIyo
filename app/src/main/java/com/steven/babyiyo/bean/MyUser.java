package com.steven.babyiyo.bean;

import java.util.List;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
	
	private static final long serialVersionUID = 1L;
	private Integer age;
	private Integer num;
	private Boolean sex;





	private List<String> hobby;		// ��Ӧ�����Array���ͣ�String���͵ļ���

	
      //���й�����Ա
	
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
}
