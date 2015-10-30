package com.ltd.mos.base;

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

public class BaseDialogAdapter extends BaseAdapter {
	Context context;
	ViewHolder viewHolder;
	String []data={"从相册中选择","拍照","取消"};
	public BaseDialogAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
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
					R.layout.dialog_item, null);
			viewHolder.content = (TextView) convertView
					.findViewById(R.id.content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if(position==0){
			convertView.setBackgroundResource(R.drawable.rect_corner_top);
		}else if(position==data.length-1){
			convertView.setBackgroundResource(R.drawable.rect_corner_bottom);
		}else{
			convertView.setBackgroundResource(R.drawable.rect_corner_middle);
		}
		viewHolder.content.setText(Logic.getString(data[position]));
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
		TextView content;
	}

}
