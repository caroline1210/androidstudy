package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.bean.FeedbackBean;

public class FeedbackAdapter extends BaseAdapter {

	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}

	Context context;
	ArrayList<FeedbackBean> list;
	private LayoutInflater mInflater;

	public FeedbackAdapter(Context c, ArrayList<FeedbackBean> list) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.list = list;
		mInflater = LayoutInflater.from(context);
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

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		FeedbackBean info = list.get(position);
		if (info.getComMeg()) {
			return IMsgViewType.IMVT_COM_MSG;
		} else {
			return IMsgViewType.IMVT_TO_MSG;
		}
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		FeedbackBean info = list.get(position);
		boolean isComMsg = info.getComMeg();
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			if (isComMsg) {
				convertView = mInflater.inflate(R.layout.feedback_msg_left,
						null);
				holder.tv_SendTime = (TextView) convertView
						.findViewById(R.id.tv_sendtime);
				holder.tv_Content = (TextView) convertView
						.findViewById(R.id.tv_chatcontent);
				holder.tv_UserName = (TextView) convertView
						.findViewById(R.id.tv_username);
			} else {
				convertView = mInflater.inflate(R.layout.feedback_msg_right,
						null);
				holder.tv_SendTime = (TextView) convertView
						.findViewById(R.id.tv_sendtime);
				holder.tv_Content = (TextView) convertView
						.findViewById(R.id.tv_chatcontent);
				holder.tv_UserName = (TextView) convertView
						.findViewById(R.id.tv_username);
			}
			holder.isComMsg = isComMsg;
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_SendTime.setText(info.getDate());
		holder.tv_Content.setText(info.getText());
		holder.tv_UserName.setText(info.getName());

		return convertView;
	}

	class ViewHolder {
		public TextView tv_SendTime;
		public TextView tv_UserName;
		public TextView tv_Content;
		public boolean isComMsg = true;
	}
}
