package com.steven.babyiyo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steven.babyiyo.R;
import com.steven.babyiyo.bean.Pageonelv;

import org.xutils.x;

import java.util.List;

public class PageoneRecomAdapter extends BaseAdapter {
	List<Pageonelv> list;
	private Context context;
	private LayoutInflater inflater;



	public PageoneRecomAdapter(List<Pageonelv> list, Context context) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	
	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.pagerecom_item, null);
		LinearLayout lv_desc = (LinearLayout) view.findViewById(R.id.lv_desc);
		ImageView iv_page = (ImageView) view.findViewById(R.id.iv_page);
		x.image().bind(iv_page,list.get(position).getlvimage().getFileUrl(context));
		TextView title = (TextView) view.findViewById(R.id.title);
		title.setText(list.get(position).getLvtitle());
		TextView personnum = (TextView) view.findViewById(R.id.personnum);
		TextView price = (TextView) view.findViewById(R.id.price);
		if (TextUtils.isEmpty(list.get(position).getLvprice())){
			lv_desc.setVisibility(View.GONE);
		}
		price.setText(list.get(position).getLvprice());
		TextView address = (TextView) view.findViewById(R.id.address);

		return view;
	}

}
