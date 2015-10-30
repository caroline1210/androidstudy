package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PushingAdapter extends BaseAdapter {

	Context c;
	ArrayList<OrderItemList> list;
	private ECallBack callBack;

	public PushingAdapter(Context c, ArrayList<OrderItemList> list,
			ECallBack callBack) {
		// TODO Auto-generated constructor stub
		this.c = c;
		this.callBack = callBack;
		this.list = list;
		AsyncImageLoader.initOption();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(c, R.layout.pushing_item, null);
			holder.iv_content = (ImageView) convertView
					.findViewById(R.id.iv_pushing_content);
			holder.tv_winedese = (TextView) convertView
					.findViewById(R.id.tv_winedese);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_pushing_price);
			holder.tv_num = (TextView) convertView
					.findViewById(R.id.tv_pushing_num);
			holder.cancel_order = (Button) convertView
					.findViewById(R.id.b_cancel_order);

			holder.tv_time = (TextView) convertView
					.findViewById(R.id.iv_pushing_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		OrderItemList orderItem=list.get(position);
		String url=Logic.getString(orderItem.getMediumImageUrl());
		AsyncImageLoader.displayImage(url, holder.iv_content);// Òì²½¼ÓÔØÍøÂçÍ¼Æ¬
//		holder.iv_content.setBackgroundDrawable(c.getResources().getDrawable(
//				R.drawable.ad));
		holder.tv_winedese.setText(Logic.getString(orderItem.getInternalName()));
		holder.tv_price.setText(Logic.getString(orderItem.getItemTotal())+"Ôª");
		holder.tv_num.setText(Logic.getString(orderItem.getQuantity())+"Æ¿");
		holder.tv_time.setText(Logic.getString(orderItem.getDate()));

		holder.cancel_order.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				callBack.OnCreate(position);
			}
		});

		return convertView;
	}

	class ViewHolder {
		ImageView iv_content;
		TextView tv_winedese, tv_price, tv_num, tv_time;
		Button cancel_order;
	}
}
