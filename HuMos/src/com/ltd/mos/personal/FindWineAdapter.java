package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.WineInfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FindWineAdapter extends BaseAdapter {

	Context context;
	ArrayList<WineInfo> list;

	public FindWineAdapter(Context c, ArrayList<WineInfo> list) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.findwine_item, null);
			holder.tv_status = (TextView) convertView
					.findViewById(R.id.tv_findwine_status);
			holder.tv_wineevent = (TextView) convertView
					.findViewById(R.id.tv_findwine_winedese);
			holder.tv_time = (TextView) convertView
			.findViewById(R.id.tv_findwine_time);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_status.setText(list.get(position).getFindwineState());
		holder.tv_wineevent.setText(list.get(position).getWineevent());
		holder.tv_time.setText(list.get(position).getCustomTime());

		return convertView;
	}

	class ViewHolder {
		TextView tv_status, tv_wineevent,tv_time;
	}
}
