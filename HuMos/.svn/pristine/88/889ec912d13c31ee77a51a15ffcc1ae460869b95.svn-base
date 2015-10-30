package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.TailorBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {

	Context c;
	ArrayList<TailorBean> list;

	public PersonAdapter(Context c, ArrayList<TailorBean> list) {
		// TODO Auto-generated constructor stub
		this.c = c;
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(c, R.layout.person_order_item, null);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_person_content);
			holder.tv_count = (TextView) convertView
					.findViewById(R.id.tv_person_order_count);
			holder.iv_resource = (ImageView) convertView
					.findViewById(R.id.iv_person_resource);
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder) convertView.getTag();
		}
		if (list.get(position).getContent()=="ÑûÇëºÃÓÑ") {
			if (position==0) {
				convertView.setBackgroundResource(R.drawable.rect_corner_single);
			}
			
		} else {
			if (position == 0) {
				convertView.setBackgroundResource(R.drawable.rect_corner_top);
			} else if (position == (list.size() - 1)) {
				convertView.setBackgroundResource(R.drawable.rect_corner_bottom);
			} else {
				convertView.setBackgroundResource(R.drawable.rect_corner_middle);
			}
		}
		
		holder.tv_content.setText(list.get(position).getContent());
		holder.tv_count.setText(list.get(position).getTitle());
		holder.iv_resource.setBackgroundResource(list.get(position).getResource());
		
		return convertView;
	}

	class ViewHolder {
		TextView tv_content, tv_count;
		ImageView iv_resource;
	}
}
