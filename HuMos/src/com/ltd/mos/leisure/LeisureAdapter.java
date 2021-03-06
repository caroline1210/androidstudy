package com.ltd.mos.leisure;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.LeisureBean;
import com.ltd.mos.util.Logic;

public class LeisureAdapter extends BaseAdapter {
	ArrayList<LeisureBean> list = new ArrayList<LeisureBean>();
	Context context;
	ViewHolder viewHolder;
	ECallBack callBack;

	public LeisureAdapter(Context context, ArrayList<LeisureBean> list,
			ECallBack callBack) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.callBack = callBack;
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
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LinearLayout.inflate(context,
					R.layout.leisure_item, null);
			viewHolder.noteName = (TextView) convertView
					.findViewById(R.id.content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.noteName.setText(Logic.getString(list.get(position)
				.getNoteName()));
//		viewHolder.content.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callBack.OnCreate(list.get(position).getContent());
//			}
//		});
		return convertView;
	}

	class ViewHolder {
		TextView noteName;
	}

}
