package com.steven.babyiyo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_pay)
public class PayActivity extends Activity implements OnClickListener{
	@ViewInject(R.id.rela_weixin)
	RelativeLayout rela_weixin;
	@ViewInject(R.id.rela_zhifubao)
	RelativeLayout rela_zhifubao;
	@ViewInject(R.id.right_weixin)
	ImageView right_weixin;
	@ViewInject(R.id.right_zhifubao)
	ImageView right_zhifubao;
	@ViewInject(R.id.payprice)
	TextView payprice;
	@ViewInject(R.id.paynum)
	TextView paynum;
	@ViewInject(R.id.payedit_price)
	TextView payedit_price;
	@ViewInject(R.id.pay_title)
	TextView pay_title;
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
		String goodnum = intent.getStringExtra("goodnum");
		String title = intent.getStringExtra("title");
		String price = intent.getStringExtra("price");
		paynum.setText(goodnum);
		payprice.setText(price);
		payedit_price.setText(price);
		pay_title.setText(title);
		rela_weixin.setOnClickListener(this);
		rela_zhifubao.setOnClickListener(this);




	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rela_zhifubao:
				right_weixin.setVisibility(View.INVISIBLE);
				right_zhifubao.setVisibility(View.VISIBLE);
				break;

			case R.id.rela_weixin:
				right_weixin.setVisibility(View.VISIBLE);
				right_zhifubao.setVisibility(View.INVISIBLE);
				break;
			case R.id.ll_left:
                finish();
				break;

		}}


}
