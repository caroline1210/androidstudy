package com.ltd.mos.sercenter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.platform.comapi.map.t;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.bean.Tasting;
import com.ltd.mos.util.Logic;

public class TastingAdapter extends BaseAdapter {
	Context context;
	ArrayList<Tasting> list = new ArrayList<Tasting>();

	public TastingAdapter(Context context, ArrayList<Tasting> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
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

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LinearLayout.inflate(context, R.layout.tast_item,
					null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image);
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.content);
			viewHolder.time = (TextView) convertView.findViewById(R.id.time);
			viewHolder.address = (TextView) convertView
					.findViewById(R.id.address);
			viewHolder.user = (TextView) convertView.findViewById(R.id.user);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Tasting tasting = list.get(position);
		viewHolder.title.setText(Logic.getString(tasting.getTitle()));
		viewHolder.time.setText(Logic.getString(tasting.getTime()));
		viewHolder.address.setText(Logic.getString(tasting.getAddress()));
		viewHolder.user.setText(Logic.getString(tasting.getUser()));

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView title;
		TextView time;
		TextView address;
		TextView user;
	}
}
