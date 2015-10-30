package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.TailorBean;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomizeAdapter extends BaseAdapter {

	Context context;
	ArrayList<TailorBean> list;

	public CustomizeAdapter(Context c, ArrayList<TailorBean> list) {
		// TODO Auto-generated constructor stub
		this.context = c;
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
			convertView = View.inflate(context, R.layout.customize_item, null);
			holder.tv_title=(TextView) convertView.findViewById(R.id.tv_Title);
			holder.tv_time=(EditText) convertView.findViewById(R.id.tv_time_one);
			holder.tv_info=(TextView) convertView.findViewById(R.id.tv_info);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position == 0) {
			convertView.setBackgroundResource(R.drawable.rect_corner_top);
		} else if (position == (list.size() - 1)) {
			convertView.setBackgroundResource(R.drawable.rect_corner_bottom);
		} else {
			convertView.setBackgroundResource(R.drawable.rect_corner_middle);
		}
		TailorBean tailorBean = list.get(position);

		holder.tv_title.setText(tailorBean.getTitle());
		holder.tv_info.setHint(tailorBean.getContent());
		holder.tv_time.setText(tailorBean.getTime());
		holder.iv_paint.setBackgroundResource(tailorBean.getResource());

		return convertView;
	}

	class ViewHolder {

		TextView tv_title,tv_info,tv_time;
		ImageView iv_paint;
	}
}