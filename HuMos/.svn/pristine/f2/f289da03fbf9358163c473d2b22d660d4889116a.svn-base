package com.ltd.mos.personal;


import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.util.Logic;

public class WhiteWineAdapter extends BaseAdapter {
	private Context context;
	private ViewHolder viewHolder;
	ECallBack callBack;
	ECallBack checkCallBack;
	private ArrayList<WineInfo> list = new ArrayList<WineInfo>();

	public WhiteWineAdapter(Context context, ArrayList<WineInfo> list,
			ECallBack callBack, ECallBack checkCallBack) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.callBack = callBack;
		this.checkCallBack = checkCallBack;
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
			convertView = FrameLayout
					.inflate(context, R.layout.whitewine_item, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
			viewHolder.price = (TextView) convertView.findViewById(R.id.price);
			viewHolder.num = (TextView) convertView.findViewById(R.id.num);
			viewHolder.checkBox = (CheckBox) convertView
					.findViewById(R.id.checkBox);
			viewHolder.add = (Button) convertView.findViewById(R.id.add);
			viewHolder.reduce = (Button) convertView.findViewById(R.id.reduce);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.checkBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							checkCallBack.OnCreate(position);
						} else {
							checkCallBack.OnError(position);
						}
					}
				});
		WineInfo wineInfo = list.get(position);
		if (Logic.getString(wineInfo.getSelect()).equals("1")) {
			viewHolder.checkBox.setChecked(true);
		} else {
			viewHolder.checkBox.setChecked(false);
		}
		viewHolder.image.setBackgroundDrawable(context.getResources()
				.getDrawable(R.drawable.ad_01));
		viewHolder.price.setText(Logic.getString(wineInfo.getWinePrice()));
		viewHolder.num.setText("ÊýÁ¿£º12");
		viewHolder.desc.setText(Logic.getString(wineInfo.getWineDese()));
		viewHolder.add.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.OnCreate(position);
			}
		});
		viewHolder.reduce.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.OnError(position);
			}
		});
		return convertView;
	}

	class ViewHolder {
		private ImageView image;
		private TextView desc;
		private TextView price;
		private TextView num;
		private CheckBox checkBox;
		private Button add;
		private Button reduce;
	}
}
