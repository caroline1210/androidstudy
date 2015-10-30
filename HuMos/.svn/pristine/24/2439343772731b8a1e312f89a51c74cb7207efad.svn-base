package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoryOrderAdapter extends BaseAdapter{

	Context context;
	ArrayList<OrderItemList> list;
	public HistoryOrderAdapter(Context c,
			ArrayList<OrderItemList> list) {
		// TODO Auto-generated constructor stub
		this.context=c;
		this.list=list;
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
			convertView = View.inflate(context, R.layout.history_order_item, null);
			
			holder.tv_winedese=(TextView) convertView.findViewById(R.id.tv_history_winedese);
			holder.tv_price=(TextView) convertView.findViewById(R.id.tv_history_price);
			holder.tv_num=(TextView) convertView.findViewById(R.id.tv_history_num);
			holder.tv_time=(TextView) convertView.findViewById(R.id.tv_history_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		OrderItemList orderItem=list.get(position);
		
		holder.tv_winedese.setText(Logic.getString(orderItem.getInternalName()));
		holder.tv_price.setText(Logic.getString(orderItem.getItemTotal()));
		holder.tv_num.setText(Logic.getString(orderItem.getQuantity()));
		holder.tv_time.setText(Logic.getString(orderItem.getDate()));
		
		return convertView;
	}

	class ViewHolder {
		
		TextView tv_winedese,tv_price,tv_num,tv_time;
	}
}
