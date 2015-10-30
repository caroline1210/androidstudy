package com.ltd.mos.sercenter;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.TailorBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * µµ∞∏  ≈‰∆˜
 * 
 * @author tongCao
 * @date 2013-9-11
 */
public class TailorAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<TailorBean> list;

	public TailorAdapter(Context context, ArrayList<TailorBean> list) {
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
			convertView = LinearLayout.inflate(context, R.layout.tailor_item,
					null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView
					.findViewById(R.id.textTitle);
			viewHolder.content = (EditText) convertView
					.findViewById(R.id.editContent);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (position == 0) {
			convertView.setBackgroundResource(R.drawable.rect_corner_top);
		} else if (position == (list.size() - 1)) {
			convertView.setBackgroundResource(R.drawable.rect_corner_bottom);
		} else {
			convertView.setBackgroundResource(R.drawable.rect_corner_middle);
		}
		viewHolder.content.setHint(list.get(position).getContentHint());
		viewHolder.content.setText(list.get(position).getContent());
		viewHolder.title.setText(list.get(position).getTitle());
		return convertView;
	}

	class ViewHolder {
		TextView title;
		EditText content;
	}
}
