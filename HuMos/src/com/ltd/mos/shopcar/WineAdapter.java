package com.ltd.mos.shopcar;

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
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.util.Logic;

public class WineAdapter extends BaseAdapter {
	private Context context;
	private ViewHolder viewHolder;
	ECallBack callBack;
	ECallBack checkCallBack;
	private ArrayList<ShopCarBean> list = new ArrayList<ShopCarBean>();

	public WineAdapter(Context context, ArrayList<ShopCarBean> list,
			ECallBack callBack, ECallBack checkCallBack) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.callBack = callBack;
		this.checkCallBack = checkCallBack;
		AsyncImageLoader.initOption();
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
					.inflate(context, R.layout.wine_item, null);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
			viewHolder.price = (TextView) convertView.findViewById(R.id.price);
			viewHolder.num = (TextView) convertView.findViewById(R.id.num);
			viewHolder.checkBox = (Button) convertView
					.findViewById(R.id.checkBox);
			viewHolder.add = (Button) convertView.findViewById(R.id.add);
			viewHolder.reduce = (Button) convertView.findViewById(R.id.reduce);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (Logic.getString(list.get(position).getSelect()).equals("1")) {
			viewHolder.checkBox
					.setBackgroundResource(R.drawable.shop_check_pressed);
		} else {
			viewHolder.checkBox
					.setBackgroundResource(R.drawable.shop_check_default);
		}
		viewHolder.checkBox.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!Logic.getString(list.get(position).getSelect())
						.equals("1")) {
					checkCallBack.OnCreate(position);
				} else {
					checkCallBack.OnError(position);
				}

			}
		});

		viewHolder.price.setText("��"
				+ Logic.getString(list.get(position).getUnitPrice()));
		viewHolder.num.setText(Logic
				.getString(list.get(position).getQuantity()));
		viewHolder.desc.setText(Logic.getString(list.get(position)
				.getInternalName()));

		AsyncImageLoader.displayImage(
				Logic.getString(list.get(position).getMediumImageUrl()),
				viewHolder.image);

		viewHolder.add.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.OnCreate(position);
			}
		});
		try {
			if (Integer.parseInt(Logic.getString(list.get(position)
					.getQuantity())) <= 0) {
				viewHolder.reduce.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.reduce_unenable));
			} else {
				viewHolder.reduce.setBackgroundDrawable(context.getResources()
						.getDrawable(R.drawable.reduce));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		private Button checkBox;
		private Button add;
		private Button reduce;
	}
}
