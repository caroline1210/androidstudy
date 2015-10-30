package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.ltd.mos.R;
import com.ltd.mos.bean.TailorBean;

public class SettingAdapter extends BaseAdapter {

	public static boolean msgState1 = false;
	public static boolean msgState2 = false;
	public static boolean msgState3 = false;
	Context context;
	ArrayList<TailorBean> list;

	public SettingAdapter(Context c, ArrayList<TailorBean> list) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.list = list;
	}

	private boolean b;

	public SettingAdapter(Context c, ArrayList<TailorBean> titleList, boolean b) {
		this.context = c;
		this.list = titleList;
		this.b = b;
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
		// boolean msgState = false;
		// boolean voiceState = false;
		// boolean nopaintState = false;
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.setting_item, null);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_setting_title);
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_setting_content);
			holder.iv_resource = (Button) convertView
					.findViewById(R.id.iv_setting_resource);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (list.get(position).getResource() == R.drawable.person_setting) {
			if (position == 0) {
				convertView.setBackgroundResource(R.drawable.rect_corner_top);
			} else if (position == (list.size() - 1)) {
				convertView
						.setBackgroundResource(R.drawable.rect_corner_bottom);
			} else {
				convertView
						.setBackgroundResource(R.drawable.rect_corner_middle);
			}
		} else {
			if (position == 0) {
				convertView
						.setBackgroundResource(R.drawable.rect_corner_top_default);
			} else if (position == (list.size() - 1)) {
				convertView
						.setBackgroundResource(R.drawable.rect_corner_bottom_default);
			} else {
				convertView
						.setBackgroundResource(R.drawable.rect_corner_middle_default);
			}

		}
		holder.tv_content.setText(list.get(position).getContent());
		holder.tv_title.setText(list.get(position).getTitle());
		holder.iv_resource.setBackgroundResource(list.get(position)
				.getResource());
		if (b) {
			if (position == 2)
				holder.iv_resource.setVisibility(View.GONE);
		}

		holder.iv_resource.setOnClickListener(new OnClickListener() {
			// �滻״̬ͼƬ
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (list.get(position).getResource() != R.drawable.person_setting) {
					switch (position) {
					case 0:
						if (msgState1) {
							msgState1 = false;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_close);
						} else {
							msgState1 = true;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_open);
						}
						break;
					case 1:
						if (msgState2) {
							msgState2 = false;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_close);
						} else {
							msgState2 = true;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_open);
						}
						break;
					case 2:
						if (msgState3) {
							msgState3 = false;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_close);
						} else {
							msgState3 = true;
							holder.iv_resource
									.setBackgroundResource(R.drawable.person_open);
						}
						break;
					default:
						break;
					}
				}
			}
		});

		return convertView;
	}

	class ViewHolder {
		TextView tv_content, tv_title;
		Button iv_resource;
	}
}
