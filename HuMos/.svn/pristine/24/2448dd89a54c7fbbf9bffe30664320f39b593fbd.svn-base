package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.DjqBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DaijinquanAdapter extends BaseAdapter{

	Context c;
	ArrayList<DjqBean> list;

	public DaijinquanAdapter(Context c,
			ArrayList<DjqBean> list) {
		// TODO Auto-generated constructor stub
		this.c=c;
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
			convertView = View.inflate(c, R.layout.djq_item, null);
			holder.tv_djqdese=(TextView) convertView.findViewById(R.id.tv_djqdese);
			holder.tv_general=(TextView) convertView.findViewById(R.id.tv_general);
			holder.tv_validaty=(TextView) convertView.findViewById(R.id.tv_validity);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tv_djqdese.setText(list.get(position).getDjqdese());
		holder.tv_general.setText(list.get(position).getGeneral());
		holder.tv_validaty.setText(list.get(position).getValidaty());
		
		return convertView;
	}

	class ViewHolder {
		TextView tv_djqdese,tv_general,tv_validaty;
	}
}