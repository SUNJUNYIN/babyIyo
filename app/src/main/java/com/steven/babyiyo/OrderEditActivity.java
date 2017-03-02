package com.steven.babyiyo;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.steven.babyiyo.bean.MyUser;
import com.steven.babyiyo.component.EditTextWithDel;
import com.steven.babyiyo.component.PaperButton;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

@ContentView(R.layout.activity_orderedit)
public class OrderEditActivity extends Activity implements OnClickListener{
	@ViewInject(R.id.priceandnum)
	TextView priceandnum;
	@ViewInject(R.id.title_name)
	TextView title_name;
	@ViewInject(R.id.edit_price)
	TextView edit_price;
	@ViewInject(R.id.image_main)
	ImageView image_main;
	@ViewInject(R.id.bt_add)
	LinearLayout bt_add;
	@ViewInject(R.id.bt_sub)
	LinearLayout bt_sub;
	@ViewInject(R.id.pay_edit)
	LinearLayout pay_edit;
	@ViewInject(R.id.goodsnum)
	TextView goodsnum;
	private String[] split;
	private String subPrice;
	private String title;
	@ViewInject(R.id.ll_left)
	LinearLayout ll_left;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		initView();

	}


	private void initView() {
		ll_left.setOnClickListener(this);
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		String price = intent.getStringExtra("price");
		String imageurl = intent.getStringExtra("imageurl");
		title_name.setText(title);
		priceandnum.setText(price);
		//这里截取想要的价格
		split = price.split("/");
		subPrice = split[0].substring(0, split[0].length()-1);
		edit_price.setText(subPrice+"元");
		x.image().bind(image_main,imageurl);
		bt_add.setOnClickListener(this);
		bt_sub.setOnClickListener(this);
		pay_edit.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_left:
				finish();
				break;


			case R.id.bt_add:
				String nums = goodsnum.getText().toString();
				int num = Integer.parseInt(nums);
				num++;

				goodsnum.setText(num+"");
				edit_price.setText(Integer.parseInt(subPrice)*num+"元");
				break;
			case R.id.pay_edit:
				String goodnum = goodsnum.getText().toString();
				String price = edit_price.getText().toString();
				Intent intent = new Intent(this, PayActivity.class);
				intent.putExtra("goodnum",goodnum);
				intent.putExtra("price",price);
				intent.putExtra("title",title);
				startActivity(intent);
				break;
			case R.id.bt_sub:
				String numadd = goodsnum.getText().toString();
				int number = Integer.parseInt(numadd);
				number--;
				if (number < 1) {
					number = 1;

				}
				goodsnum.setText(number+"");
				edit_price.setText(Integer.parseInt(subPrice)*number+"元");
				break;




		}}


}
