package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.MessageBean;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {

	ArrayList<MessageBean> list = new ArrayList<MessageBean>();
	Context context;

	public MessageAdapter(Context c, ArrayList<MessageBean> list) {
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
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.message_item, null);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_message_title);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_message_content);
			holder.tv_time = (TextView) convertView
					.findViewById(R.id.tv_message_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_title
				.setText(Logic.getString(list.get(position).getmTitle()));
		holder.tv_content.setText(Logic.getString(list.get(position)
				.getmContent()));
		holder.tv_time.setText(Logic.getString(list.get(position).getmTime()));
		return convertView;
	}

	class ViewHolder {
		private TextView tv_title, tv_content, tv_time;
	}
}
